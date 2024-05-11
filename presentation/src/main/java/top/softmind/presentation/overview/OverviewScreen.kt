package top.softmind.presentation.overview

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.koinViewModel
import top.softmind.common.ArtCollectionId
import top.softmind.common.UrlAddress
import top.softmind.domain.module.ArtCollection
import top.softmind.presentation.AppScreen
import top.softmind.presentation.common.StatefulImage

@Composable
internal fun OverviewScreen(
    navController: NavHostController,
    viewModel: OverviewViewModel = koinViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ArtCollectionList(artCollection = viewModel.artCollections) { artCollectionId ->
            navController.navigate("${AppScreen.DETAILS.name}/${artCollectionId.value}")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ArtCollectionList(
    artCollection: Flow<PagingData<ArtCollection>>,
    onItemClick: (ArtCollectionId) -> Unit,
) {
    val artCollectionItems: LazyPagingItems<ArtCollection> = artCollection.collectAsLazyPagingItems()

    LazyColumn {
        val itemCount = artCollectionItems.itemCount
        var lastItem: ArtCollection? = null

        for (index in 0 until itemCount) {

            val currentItem = artCollectionItems.peek(index)

            if (currentItem != null && lastItem?.principalOrFirstMaker != currentItem.principalOrFirstMaker) {
                stickyHeader {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(horizontal = 16.dp),
                    ) {
                        Text(text = currentItem.principalOrFirstMaker, color = Color.White)
                    }
                }
            }

            item {
                artCollectionItems[index]?.let { item ->
                    ArtCollectionItem(item) { id ->
                        onItemClick(id)
                    }
                }
            }

            lastItem = currentItem
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
            StatefulImage(
                modifier = Modifier.size(80.dp),
                webImageUrl = item.webImageUrl.value,
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
    ArtCollectionList(flowOf(PagingData.from(listOf(
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
    )))) {}
}