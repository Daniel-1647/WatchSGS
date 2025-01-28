package com.daniel.watchsgs.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniel.watchsgs.data.model.Title
import com.daniel.watchsgs.navigation.Screens

@Composable
fun WatchList(watchList: List<Title>, listState: LazyListState, isLoading: Boolean, navController: NavController) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isLoading) {
            items(20) {
                ShimmerWatchTile()
            }
        } else {
            items(watchList) { item ->
                WatchTile(title = item.title, year = item.year, onClick = {
                    navController.navigate(Screens.Details.passVideoId(item.id))
                })
            }
        }
    }
}