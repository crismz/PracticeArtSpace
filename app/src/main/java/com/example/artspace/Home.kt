package com.example.artspace

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

/*
TODO: Screens, so One screen is for see the arts
TODO: ScrollView for Home
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceGallery() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                )
            )
        },
        containerColor = Color.LightGray
    ) { innerPadding ->
        // Index
        var i = 0

        // Width of screen
        val width = Resources.getSystem().displayMetrics.widthPixels
        val widthPerImage = width/6

        // Images and Bitmaps
        val images = arrayOf(
            R.drawable.girlwithapearlearring, R.drawable.lanascitadiviniere, R.drawable.lasmeninas,
            R.drawable.monalisa, R.drawable.thegreatwaveoffkanagawa, R.drawable.thekiss,
            R.drawable.thepersistenceofmemory, R.drawable.thesonofman, R.drawable.thestarrynight,)
        val bitmapImages = mutableListOf<Bitmap>()
        for (j in images.indices) {
            bitmapImages.add(resizeImage(images[j], LocalContext.current, widthPerImage))
        }

        // Info of arts
        val artNames: Array<String> = stringArrayResource(R.array.art_names)
        val artistNames: Array<String> = stringArrayResource(R.array.artist_names)
        val years: Array<String> = stringArrayResource(R.array.years)



        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            while(i < images.size) {
                var j = 0
                Row (
                    modifier = Modifier
                ) {
                    j = 0
                    while (i+j < images.size && (j+1) % 7 != 0) {
                        ImageButton(
                            bitmapImage = bitmapImages[i+j],
                            {},
                            Modifier
                        )
                        j++
                    }
                }
                i += j
            }
        }
    }
}

@Composable
fun ImageButton(
    bitmapImage: Bitmap,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
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

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceGallery()
    }
}