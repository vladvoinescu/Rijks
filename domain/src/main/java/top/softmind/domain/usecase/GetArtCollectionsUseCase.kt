package top.softmind.domain.usecase

import top.softmind.domain.repository.ArtCollectionRepository

class GetArtCollectionsUseCase internal constructor(
    private val repository: ArtCollectionRepository
){
    suspend operator fun invoke(page: Int) = repository.getArtCollections(page)
}