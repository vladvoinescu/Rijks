package top.softmind.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import top.softmind.common.ArtCollectionId
import top.softmind.domain.module.ArtCollectionDetails
import top.softmind.domain.usecase.GetArtCollectionDetailsUseCase

internal class DetailsViewModel(
    private val id: String,
    private val getArtCollectionDetailsUseCase: GetArtCollectionDetailsUseCase,
): ViewModel() {

    private val _viewState: MutableStateFlow<DetailsState> = MutableStateFlow(DetailsState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val result = getArtCollectionDetailsUseCase(ArtCollectionId(id))
                _viewState.value = DetailsState.Success(result)
            } catch(e: Exception) {
                _viewState.value = DetailsState.Failure(e.localizedMessage ?: "UNKNOWN")
            }
        }
    }
}

internal sealed interface DetailsState {
    data object Loading: DetailsState
    data class Success(val details: ArtCollectionDetails): DetailsState
    data class Failure(val error: String): DetailsState
}