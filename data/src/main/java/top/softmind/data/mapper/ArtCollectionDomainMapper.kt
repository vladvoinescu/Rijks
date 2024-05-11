package top.softmind.data.mapper

import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress
import top.softmind.data.model.ArtCollectionsResponseDto
import top.softmind.domain.module.ArtCollection

internal class ArtCollectionDomainMapper {
    operator fun invoke(response: ArtCollectionsResponseDto) = response.artObjects.map {
        ArtCollection(
            id = ArtCollectionId(it.objectNumber),
            principalOrFirstMaker = it.principalOrFirstMaker,
            title = it.title,
            webImageUrl = UrlAddress(it.webImage.url)
        )
    }
}