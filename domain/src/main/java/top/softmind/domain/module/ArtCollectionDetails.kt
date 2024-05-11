package top.softmind.domain.module

import top.softmind.common.UrlAddress

data class ArtCollectionDetails(
    val labelMakerLine: String,
    val description: String,
    val webImageUrl: UrlAddress,
)
