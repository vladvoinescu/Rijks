package top.softmind.presentation.overview

import androidx.paging.PagingSource
import androidx.paging.PagingState
import top.softmind.domain.module.ArtCollection
import top.softmind.domain.usecase.GetArtCollectionsUseCase

class ArtCollectionSource(
    private val getArtCollections: GetArtCollectionsUseCase
): PagingSource<String, ArtCollection>() {
    override fun getRefreshKey(state: PagingState<String, ArtCollection>): String {
        return state.anchorPosition.toString()
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ArtCollection> {
        return try {
            val nextPage = (params.key ?: "0").toInt()
            val list = getArtCollections(nextPage)
            LoadResult.Page(
                data = list.values.flatten(),
                prevKey = null,
                nextKey = (nextPage + 1).toString()
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}