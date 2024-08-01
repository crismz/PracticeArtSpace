package com.example.artspace.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

@Composable
fun HomeScreen(
    images: Array<Int>,
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    // Index
    var i = 0

    // Width of screen
    val width = Resources.getSystem().displayMetrics.widthPixels
    val widthPerImage = width/6

    // Bitmaps
    val bitmapImages = mutableListOf<Bitmap>()
    for (j in images.indices) {
        bitmapImages.add(resizeImage(images[j], LocalContext.current, widthPerImage))
    }

    Column (
        modifier = Modifier
        .verticalScroll(
        state = rememberScrollState(),
        enabled = true
    )
    ) {
        while(i < images.size) {
            var j = 0
            Row (
                modifier = Modifier
            ) {
                j = 0
                while (i+j < images.size && (j+1) % 7 != 0) {
                    val index = i + j
                    ImageButton(
                        bitmapImage = bitmapImages[index],
                        onImageClick = { onNextButtonClicked(index) },
                        Modifier
                    )
                    j++
                }
            }
            i += j
        }
    }
}

@Composable
fun ImageButton(
    bitmapImage: Bitmap,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button (
        onClick = onImageClick,
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
    ) {
        Image(
            bitmap = bitmapImage.asImageBitmap(),
            contentDescription = null
        )
    }
}

fun resizeImage(
    imageID: Int,
    context: Context,
    widthPerImage: Int
): Bitmap {
    val bitmap = BitmapFactory.decodeResource(
        context.resources,
        imageID
    )
    val resized = Bitmap.createScaledBitmap(bitmap, widthPerImage, widthPerImage, true)
    return resized
}

@Preview
@Composable
fun PreviewHomeScreen() {
    ArtSpaceTheme {
        HomeScreen(
            arrayOf<Int>(),
            onNextButtonClicked = {}
        )
    }
}