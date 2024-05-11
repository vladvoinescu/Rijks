package top.softmind.data.repository

import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress
import top.softmind.data.api.ArtCollectionRemoteApi
import top.softmind.domain.module.ArtCollection
import top.softmind.domain.module.ArtCollectionDetails
import top.softmind.domain.repository.ArtCollectionRepository

internal class RemoteArtCollectionRepository(
    private val api: ArtCollectionRemoteApi
) : ArtCollectionRepository {
    override suspend fun getArtCollections(page: Int) = api.getArtCollections(page).artObjects.map {
        ArtCollection(
            id = ArtCollectionId(it.objectNumber),
            principalOrFirstMaker = it.principalOrFirstMaker,
            title = it.title,
            webImageUrl = UrlAddress(it.webImage.url)
        )
    }

    override suspend fun getArtCollectionDetails(id: ArtCollectionId): ArtCollectionDetails {
        val result = api.getArtCollectionsDetails(id.value)
        return ArtCollectionDetails(
            labelMakerLine = result.artObject.label.makerLine,
            description = result.artObject.description,
            webImageUrl = UrlAddress(result.artObject.webImage.url)
        )
    }
}