package top.softmind.domain.repository

import top.softmind.common.ArtCollectionId
import top.softmind.domain.module.ArtCollection
import top.softmind.domain.module.ArtCollectionDetails

interface ArtCollectionRepository {
    suspend fun getArtCollections(page: Int = 0): List<ArtCollection>
    suspend fun getArtCollectionDetails(id: ArtCollectionId): List<ArtCollectionDetails>
}