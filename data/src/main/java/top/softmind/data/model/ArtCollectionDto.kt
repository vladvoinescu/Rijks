package top.softmind.data.model

import top.softmind.common.ArtCollectionId

internal data class ArtCollectionDto(
    val id: ArtCollectionId,
    val principalOrFirstMaker: String,
    val title: String,
    val webImage: WebImage,
)

internal data class WebImage(val url: String)
