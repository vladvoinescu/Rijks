package top.softmind.presentation.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import top.softmind.domain.module.ArtCollection

internal class OverviewViewModel(
    private val artCollectionSource: ArtCollectionSource,
): ViewModel() {

    val artCollections: Flow<PagingData<ArtCollection>> = Pager(PagingConfig(pageSize = 30)) {
        artCollectionSource
    }.flow.cachedIn(viewModelScope)
}