package top.softmind.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import top.softmind.data.model.ArtCollectionDetailsDto
import top.softmind.data.model.ArtCollectionDto

internal interface ArtCollectionRemoteApi {

    @GET("collection")
    suspend fun getArtCollections(
        @Query("p") page: Int
    ): List<ArtCollectionDto>

    @GET("collection/{collectionId}")
    suspend fun getArtCollectionsDetails(
        @Path("collectionId") collectionId: String
    ): List<ArtCollectionDetailsDto>
}