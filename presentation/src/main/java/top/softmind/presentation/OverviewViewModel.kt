package top.softmind.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import top.softmind.domain.module.ArtCollection
import top.softmind.domain.usecase.GetArtCollectionsUseCase

class OverviewViewModel(
    private val getArtCollectionsUseCase: GetArtCollectionsUseCase,
): ViewModel() {

    private val _viewState: MutableStateFlow<OverviewState> = MutableStateFlow(OverviewState.InitialLoading)
    val viewState = _viewState.asStateFlow()

    init {
        onLoad()
    }

    fun onLoad(page: Int = 0) {
        viewModelScope.launch {
            try {
                val result = getArtCollectionsUseCase(page)
                _viewState.value = OverviewState.Success(result)
            } catch(e: Exception) {
                _viewState.value = OverviewState.Failure(e.localizedMessage ?: "UNKNOWN")
            }
        }
    }
}

sealed interface OverviewState {
    data object InitialLoading: OverviewState
    data class SubsequentialLoading(val map: Map<String, List<ArtCollection>>): OverviewState
    data class Success(val map: Map<String, List<ArtCollection>>): OverviewState
    data class Failure(val error: String): OverviewState
}