package top.softmind.data.model

internal data class ArtCollectionsResponseDto(
    val artObjects: List<ArtCollectionDto>,
)

internal data class ArtCollectionDto(
    val id: String,
    val principalOrFirstMaker: String,
    val title: String,
    val webImage: WebImage,
)

internal data class WebImage(val url: String)
