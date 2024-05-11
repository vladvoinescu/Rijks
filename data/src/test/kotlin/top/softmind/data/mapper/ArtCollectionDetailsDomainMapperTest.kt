package top.softmind.data.mapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import top.softmind.common.UrlAddress
import top.softmind.data.model.ArtCollectionDetailsDto
import top.softmind.data.model.ArtCollectionDetailsResponseDto
import top.softmind.data.model.LabelDto
import top.softmind.data.model.WebImage
import top.softmind.domain.module.ArtCollectionDetails

class ArtCollectionDetailsDomainMapperTest {

    private val mapper = ArtCollectionDetailsDomainMapper()

    @Test
    fun `GIVEN ArtCollectionDetailsResponseDto WHEN invoke THEN returns proper ArtCollectionDetails`() {
        assertEquals(mapped, mapper(response))
    }
}

private val response = ArtCollectionDetailsResponseDto(
    ArtCollectionDetailsDto(
        label = LabelDto(makerLine = "mockMarkerLine"),
        description = "mockDescription",
        webImage = WebImage(url = "mockWebImage")
    )
)

private val mapped = ArtCollectionDetails(
    labelMakerLine = "mockMarkerLine",
    description = "mockDescription",
    webImageUrl = UrlAddress("mockWebImage")
)