package com.daniel.watchsgs.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.daniel.watchsgs.ui.components.DetailsTopAppBar
import com.daniel.watchsgs.ui.viewmodels.DetailsViewModel

@Composable
fun DetailsScreen(
    navController: NavHostController,
    titleId: Int,
    detailsViewModel: DetailsViewModel
) {
    val isLoading = detailsViewModel.isLoading.collectAsState()
    val errorMessage by detailsViewModel.errorMessage.collectAsState()
    val titleDetails = detailsViewModel.titleDetails.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Long
            )
        }
    }

    LaunchedEffect(Unit) {
        detailsViewModel.getTitleDetails(titleId)
    }

    Scaffold(
        topBar = {
            DetailsTopAppBar(
                onClickAction = {
                    navController.popBackStack()
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (isLoading.value){
                DetailsWithShimmer()
            } else {
                titleDetails.value?.let { Details(it) }
            }
        }
    }
}

