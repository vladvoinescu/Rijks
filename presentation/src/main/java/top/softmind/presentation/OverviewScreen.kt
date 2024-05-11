package top.softmind.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress
import top.softmind.domain.module.ArtCollection

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            OverviewState.InitialLoading -> CircularProgressIndicator()
            is OverviewState.Failure -> Text(text = "Technical error: ${(state as OverviewState.Failure).error}")
            is OverviewState.SubsequentialLoading,
            is OverviewState.Success -> {
                if (state is OverviewState.Success) {
                    ArtCollectionList((state as OverviewState.Success).list)
                }
                if (state is OverviewState.SubsequentialLoading) {
                    ArtCollectionList(
                        list = (state as OverviewState.SubsequentialLoading).list,
                        isLoading = true
                    )
                }
            }
        }
    }
}

@Composable
internal fun ArtCollectionList(list: List<ArtCollection>, isLoading: Boolean = false) {
    LazyColumn {
        items(
            items = list,
            key = { collection ->
                collection.id.value
            },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.size(80.dp),
                    model = it.webImageUrl.value,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = it.title)
            }
            HorizontalDivider(
                color = Color.Black,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OverviewScreenPreview() {
    ArtCollectionList(
        listOf(
            ArtCollection(
                id = ArtCollectionId("1"),
                principalOrFirstMaker = "Van Gogh",
                title = "Self Portrait",
                webImageUrl = UrlAddress("")
            ),
            ArtCollection(
                id = ArtCollectionId("2"),
                principalOrFirstMaker = "Van Gogh",
                title = "The Starry Night",
                webImageUrl = UrlAddress("")
            )
        )
    )
}