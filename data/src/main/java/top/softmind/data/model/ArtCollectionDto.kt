package top.softmind.data.model

internal data class ArtCollectionsResponseDto(
    val artObjects: List<ArtCollectionDto>,
)

internal data class ArtCollectionDto(
    val objectNumber: String,
    val principalOrFirstMaker: String,
    val title: String,
    val webImage: WebImage,
)

internal data class WebImage(val url: String)
