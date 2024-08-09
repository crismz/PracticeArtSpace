package com.example.artspace.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.artspace.R
import data.Art
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OneImageScreen(
    imageSelected: Int,
    onCancelButtonClicked: () -> Unit = {},
    arts: List<Art> = emptyList()
) {
    var index by remember { mutableIntStateOf(imageSelected) }
    val size: Int = arts.size

    val pageCount = size * 800

    val pagerState = rememberPagerState(
        pageCount = { pageCount },
        initialPage = (pageCount/2) + index
    )
    val scope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        index = page % size
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedButton(
                onClick = onCancelButtonClicked,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.DarkGray),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .weight(0.5f)
            ) {
                Text(stringResource(R.string.back))
            }
            Image(
                painter = painterResource(arts[index].image),
                contentDescription = arts[index].name,
                modifier = Modifier.weight(4f)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = arts[index].name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = arts[index].year,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Author: ${arts[index].author}",
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.weight(0.5f)
            ) {
                Spacer(
                    modifier = Modifier.weight(0.5f)
                )
                Button(
                    onClick = { scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }},
                    modifier = Modifier.weight(2f)
                ) {
                    Text("Previous")
                }
                Spacer(
                    modifier = Modifier.weight(0.5f)
                )
                Button(
                    onClick = { scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }},
                    modifier = Modifier.weight(2f)
                ) {
                    Text("Next")
                }
                Spacer(
                    modifier = Modifier.weight(0.5f)
                )
            }

        }
    }

}

