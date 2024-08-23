package app.vitune.providers.sponsorblock.models

import app.vitune.providers.utils.SerializableUUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Segment(
    internal val segment: List<Float>,
    val uuid: SerializableUUID? = null,
    val category: Category,
    @SerialName("actionType")
    val action: Action,
    val description: String
) {
    val start get() = segment.first()
    val end get() = segment[1]
}