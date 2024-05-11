package top.softmind.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import top.softmind.data.model.ArtCollectionDetailsResponseDto
import top.softmind.data.model.ArtCollectionsResponseDto

internal interface ArtCollectionRemoteApi {

    @GET("collection?key=0fiuZFh4&ps=20")
    suspend fun getArtCollections(
        @Query("p") page: Int
    ): ArtCollectionsResponseDto

    @GET("collection/{collectionId}?key=0fiuZFh4")
    suspend fun getArtCollectionsDetails(
        @Path("collectionId") collectionId: String
    ): ArtCollectionDetailsResponseDto
}