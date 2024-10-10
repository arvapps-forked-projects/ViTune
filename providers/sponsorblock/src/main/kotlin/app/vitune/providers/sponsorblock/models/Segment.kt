package app.vitune.providers.sponsorblock.models

import app.vitune.providers.utils.SerializableUUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.seconds

@Serializable
data class Segment(
    internal val segment: List<Double>,
    val uuid: SerializableUUID? = null,
    val category: Category,
    @SerialName("actionType")
    val action: Action,
    val description: String
) {
    val start get() = segment.first().seconds
    val end get() = segment[1].seconds
}