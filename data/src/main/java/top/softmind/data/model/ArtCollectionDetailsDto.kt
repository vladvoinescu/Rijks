package top.softmind.data.model

internal data class ArtCollectionDetailsResponseDto(
    val artObject: ArtCollectionDetailsDto,
)

internal data class ArtCollectionDetailsDto(
    val label: LabelDto,
    val description: String,
    val webImage: WebImage,
)

internal data class LabelDto(
    val makerLine: String,
)
