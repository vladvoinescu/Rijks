package top.softmind.domain.usecase

import top.softmind.common.ArtCollectionId
import top.softmind.domain.repository.ArtCollectionRepository

class GetArtCollectionDetailsUseCase internal constructor(
    private val repository: ArtCollectionRepository
){
    suspend operator fun invoke(id: ArtCollectionId) = repository.getArtCollectionDetails(id.value)
}