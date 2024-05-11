package top.softmind.data.mapper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress
import top.softmind.data.model.ArtCollectionDto
import top.softmind.data.model.ArtCollectionsResponseDto
import top.softmind.data.model.WebImage
import top.softmind.domain.module.ArtCollection

class ArtCollectionDomainMapperTest {

    private val mapper = ArtCollectionDomainMapper()

    @Test
    fun `GIVEN ArtCollectionDetailsResponseDto WHEN invoke THEN returns proper ArtCollectionDetails`() {
        Assertions.assertEquals(mapped, mapper(response))
    }
}

private val response = ArtCollectionsResponseDto(
    listOf(
        ArtCollectionDto(
            objectNumber = "mockId",
            principalOrFirstMaker = "mockMaker",
            title = "mockTitle",
            webImage = WebImage("mockImage"),
        )
    )
)

private val mapped = listOf(
    ArtCollection(
        id = ArtCollectionId("mockId"),
        principalOrFirstMaker = "mockMaker",
        title = "mockTitle",
        webImageUrl = UrlAddress("mockImage")
    )
)