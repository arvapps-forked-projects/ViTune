package app.vitune.providers.innertube.requests

import app.vitune.providers.innertube.Innertube
import app.vitune.providers.innertube.models.Context
import app.vitune.providers.innertube.models.PlayerResponse
import app.vitune.providers.innertube.models.bodies.PlayerBody
import app.vitune.providers.utils.runCatchingCancellable
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

suspend fun Innertube.player(
    body: PlayerBody,
    pipedHost: String = "pipedapi.adminforge.de"
): Result<PlayerResponse>? = runCatchingCancellable {
    val response = client.post(PLAYER) {
        setBody(body)
    }.body<PlayerResponse>()

    if (response.playabilityStatus?.status == "OK") return@runCatchingCancellable response
    val safePlayerResponse = client.post(PLAYER) {
        setBody(
            body.copy(
                context = Context.DefaultAgeRestrictionBypass.copy(
                    thirdParty = Context.ThirdParty(
                        embedUrl = "https://www.youtube.com/watch?v=${body.videoId}"
                    )
                )
            )
        )
    }.body<PlayerResponse>()

    if (safePlayerResponse.playabilityStatus?.status != "OK") return@runCatchingCancellable response

    val audioStreams = client.get("https://$pipedHost/streams/${body.videoId}") {
        contentType(ContentType.Application.Json)
    }.body<PipedResponse>().audioStreams

    safePlayerResponse.copy(
        streamingData = safePlayerResponse.streamingData?.copy(
            adaptiveFormats = safePlayerResponse.streamingData.adaptiveFormats?.map { adaptiveFormat ->
                adaptiveFormat.copy(
                    url = audioStreams.find { it.bitrate == adaptiveFormat.bitrate }?.url
                )
            }
        )
    )
}?.recoverCatching {
    if (body.context.client.clientName == "WEB_REMIX") throw it

    Innertube.player(
        body = body.copy(
            context = Context.DefaultWeb
        ),
        pipedHost = pipedHost
    )?.getOrThrow() ?: return@player null
}

@Serializable
data class AudioStream(
    val url: String,
    val bitrate: Long
)

@Serializable
data class PipedResponse(
    val audioStreams: List<AudioStream>
)
