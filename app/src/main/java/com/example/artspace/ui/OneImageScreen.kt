package com.example.artspace.ui

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.artspace.R

@Composable
fun OneImageScreen(
    imageSelected: Int,
    onCancelButtonClicked: () -> Unit = {},
) {
    OutlinedButton(
        onClick = onCancelButtonClicked
    ) {
        Text(stringResource(R.string.back))
    }
    Text("$imageSelected")
}