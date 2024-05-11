package top.softmind.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress
import top.softmind.domain.module.ArtCollection
import top.softmind.domain.repository.ArtCollectionRepository

private const val PAGE = 0
private const val MAKER_A = "A"
private const val MAKER_B = "B"

class GetArtCollectionsUseCaseTest {

    private val repository = mockk< ArtCollectionRepository>()
    private lateinit var useCase: GetArtCollectionsUseCase

    @BeforeEach
    fun setup() {
        useCase = GetArtCollectionsUseCase(repository)
    }

    @AfterEach
    fun tearDown() {
        confirmVerified(repository)
        unmockkAll()
    }

    @Test
    fun `GIVEN pageIndex WHEN invoke THEN returns grouped collections`() = runTest {
        coEvery { repository.getArtCollections(PAGE) } returns listOf(
            ArtCollection(
                ArtCollectionId(""),
                principalOrFirstMaker = MAKER_A,
                title = "",
                webImageUrl = UrlAddress("")
            ),
            ArtCollection(
                ArtCollectionId(""),
                principalOrFirstMaker = MAKER_B,
                title = "",
                webImageUrl = UrlAddress("")
            ),
        )

        val result = useCase(PAGE)

        assertEquals(1, result[MAKER_A]?.size)
        assertEquals(1, result[MAKER_B]?.size)

        coVerify {
            repository.getArtCollections(PAGE)
        }
    }
}