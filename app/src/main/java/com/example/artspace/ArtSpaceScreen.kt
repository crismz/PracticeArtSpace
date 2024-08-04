package com.example.artspace

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artspace.ui.HomeScreen
import com.example.artspace.ui.OneImageScreen
import data.Art

enum class ArtSpaceScreen {
    Home,
    OneImage
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceGallery(
    navController: NavHostController = rememberNavController()
) {
    var imageSelected = 0

    // Images
    val images = arrayOf(
        R.drawable.girlwithapearlearring, R.drawable.lanascitadiviniere, R.drawable.lasmeninas,
        R.drawable.monalisa, R.drawable.thegreatwaveoffkanagawa, R.drawable.thekiss,
        R.drawable.thepersistenceofmemory, R.drawable.thesonofman, R.drawable.thestarrynight,)

    // Info of arts
    val artNames: Array<String> = stringArrayResource(R.array.art_names)
    val artistNames: Array<String> = stringArrayResource(R.array.artist_names)
    val years: Array<String> = stringArrayResource(R.array.years)

    // List of Arts
    val arts = mutableListOf<Art>()
    for (i in images.indices) {
        arts.add(Art(images[i], artistNames[i], artNames[i], years[i]))
    }

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
        containerColor = Color.LightGray,
        modifier = Modifier
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ArtSpaceScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ArtSpaceScreen.Home.name) {
                HomeScreen(
                    images = images,
                    onNextButtonClicked = {
                        imageSelected = it
                        navController.navigate(ArtSpaceScreen.OneImage.name)
                    }
                )
            }
            composable(route = ArtSpaceScreen.OneImage.name) {
                OneImageScreen(
                    imageSelected,
                    onCancelButtonClicked = {navController.popBackStack()},
                    arts
                )
            }
        }
    }
}
