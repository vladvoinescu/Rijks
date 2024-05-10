package top.softmind.data.model

internal data class ArtCollectionDetailsDto(
    val label: LabelDto,
    val description: String,
)

internal data class LabelDto(
    val markerLine: String,
)
