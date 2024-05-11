package top.softmind.data.repository

import top.softmind.data.api.ArtCollectionRemoteApi
import top.softmind.data.mapper.ArtCollectionDetailsDomainMapper
import top.softmind.data.mapper.ArtCollectionDomainMapper
import top.softmind.domain.repository.ArtCollectionRepository

internal class RemoteArtCollectionRepository(
    private val api: ArtCollectionRemoteApi,
    private val mapper: ArtCollectionDomainMapper,
    private val detailsMapper: ArtCollectionDetailsDomainMapper,
) : ArtCollectionRepository {
    override suspend fun getArtCollections(page: Int) = mapper(api.getArtCollections(page))

    override suspend fun getArtCollectionDetails(id: String) = detailsMapper(api.getArtCollectionsDetails(id))
}