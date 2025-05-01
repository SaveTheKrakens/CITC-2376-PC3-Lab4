package com.savethekrakens.lab4_userdirectory.ui.screens

import android.R
import android.graphics.Paint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savethekrakens.lab4_userdirectory.ui.theme.Lab4_UserDirectoryTheme

@Composable
fun HomeScreen(
    usersUiState: UserUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp)
){
    when(usersUiState){
        is UserUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is UserUiState.Success -> ResultsScreen(
            usersUiState.users, modifier = Modifier.fillMaxWidth()
        )
        is UserUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Text(
        text = "Loading...",
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier){
    Text(
        text = "Error!",
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun ResultsScreen(results: String, modifier: Modifier = Modifier){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Text(text = results)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview(){
    Lab4_UserDirectoryTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview(){
    Lab4_UserDirectoryTheme {
        ErrorScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview(){
    Lab4_UserDirectoryTheme {
        ResultsScreen("placeholder")
    }
}