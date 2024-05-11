package top.softmind.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
internal fun StatefulImage(
    modifier: Modifier,
    webImageUrl: String
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = webImageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    ) {
        val state = painter.state
        when (state) {
            AsyncImagePainter.State.Empty -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            )

            is AsyncImagePainter.State.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is AsyncImagePainter.State.Error -> Image(imageVector = Icons.Default.Warning, contentDescription = null)
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
        }
    }
}