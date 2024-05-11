package top.softmind.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersHolder
import top.softmind.domain.module.ArtCollectionDetails

@Composable
internal fun DetailsSreen(
    id: String,
    viewModel: DetailsViewModel = koinViewModel(parameters = { ParametersHolder(mutableListOf(id)) })
) {
    val state by viewModel.viewState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is DetailsState.Failure -> Text(text = "Technical error: ${(state as DetailsState.Failure).error}")
            DetailsState.Loading -> CircularProgressIndicator()
            is DetailsState.Success -> DetailsSuccess((state as DetailsState.Success).details)
        }
    }
}

@Composable
private fun DetailsSuccess(details: ArtCollectionDetails) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            model = details.webImageUrl.value,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = details.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = details.labelMakerLine)
    }
}