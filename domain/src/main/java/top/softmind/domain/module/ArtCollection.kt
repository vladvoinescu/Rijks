package top.softmind.domain.module

import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress

data class ArtCollection(
    val id: ArtCollectionId,
    val principalOrFirstMaker: String,
    val title: String,
    val webImageUrl: UrlAddress,
)
