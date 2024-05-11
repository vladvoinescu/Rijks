package top.softmind.data.mapper

import top.softmind.common.UrlAddress
import top.softmind.data.model.ArtCollectionDetailsResponseDto
import top.softmind.domain.module.ArtCollectionDetails

internal class ArtCollectionDetailsDomainMapper {
    operator fun invoke(response: ArtCollectionDetailsResponseDto) = ArtCollectionDetails(
        labelMakerLine = response.artObject.label.makerLine,
        description = response.artObject.description,
        webImageUrl = UrlAddress(response.artObject.webImage.url)
    )
}