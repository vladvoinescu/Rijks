package top.softmind.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress
import top.softmind.domain.module.ArtCollection

@Composable
internal fun OverviewScreen(
    navController: NavHostController,
    viewModel: OverviewViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            OverviewState.InitialLoading -> CircularProgressIndicator()
            is OverviewState.Failure -> Text(text = "Technical error: ${(state as OverviewState.Failure).error}")
            is OverviewState.SubsequentialLoading,
            is OverviewState.Success -> {
                if (state is OverviewState.Success) {
                    ArtCollectionList((state as OverviewState.Success).map) { artCollectionId ->
                        navController.navigate("${AppScreen.DETAILS.name}/${artCollectionId.value}")
                    }
                }
                if (state is OverviewState.SubsequentialLoading) {
                    ArtCollectionList(
                        map = (state as OverviewState.SubsequentialLoading).map,
                        isLoading = true
                    ) { artCollectionId ->
                        navController.navigate("${AppScreen.DETAILS.name}/${artCollectionId.value}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ArtCollectionList(
    map: Map<String, List<ArtCollection>>,
    isLoading: Boolean = false,
    onItemClick: (ArtCollectionId) -> Unit,
) {
    LazyColumn {
        map.keys.forEach { artist ->
            map[artist]?.let { artistCollection ->

                stickyHeader {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(horizontal = 16.dp),
                    ) {
                        Text(text = artist, color = Color.White)
                    }
                }

                itemsIndexed(
                    items = artistCollection,
                    key = { _, collection ->
                        collection.id.value
                    },
                ) { index, item ->
                    ArtCollectionItem(
                        item = item,
                        isLastInCategory = index == artistCollection.lastIndex,
                        onItemClick = onItemClick
                    )
                }
            }
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

@Composable
private fun ArtCollectionItem(
    item: ArtCollection,
    isLastInCategory: Boolean = false,
    onItemClick: (ArtCollectionId) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                onItemClick(item.id)
            },
        ) {
            AsyncImage(
                modifier = Modifier.size(80.dp),
                model = item.webImageUrl.value,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = item.title)
        }
        if (isLastInCategory) {
            Spacer(modifier = Modifier.size(8.dp))
        } else {
            Spacer(modifier = Modifier.size(4.dp))
            HorizontalDivider(
                color = Color.Black,
                thickness = 0.5.dp,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OverviewScreenPreview() {
    ArtCollectionList(
        mapOf(
            "Van Gogh" to listOf(
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
    ) {}
}