package com.daniel.watchsgs.ui.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.daniel.watchsgs.ui.components.HomeTopAppBar
import com.daniel.watchsgs.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val moviesListState = rememberLazyListState()
    val tvshowsListState = rememberLazyListState()

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        HomeTabs.entries.size
    })
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }

    val movies = mainViewModel.movies.collectAsState()
    val tvshows = mainViewModel.tvShows.collectAsState()

    val isLoading = mainViewModel.isLoading.collectAsState()

    val errorMessage by mainViewModel.errorMessage.collectAsState()
    val mActivity = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        if (movies.value.isEmpty() || tvshows.value.isEmpty()) {
            mainViewModel.getMoviesAndTvSeries()
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Long
            )
        }
    }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onClickAction = {
                     mActivity.finish()
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeTabs.entries.forEachIndexed { index, currentTab ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.outline,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = { Text(text = currentTab.title) },
                        icon = {
                            Icon(
                                imageVector = if (selectedTabIndex.value == index)
                                    currentTab.selectedIcon else currentTab.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                val currentTab = HomeTabs.entries[page]
                when (currentTab) {
                    HomeTabs.Movies -> {
                        WatchList(
                            watchList = movies.value,
                            listState = moviesListState,
                            isLoading = isLoading.value,
                            navController = navController
                        )
                    }

                    HomeTabs.TvShows -> {
                        WatchList(
                            watchList = tvshows.value,
                            listState = tvshowsListState,
                            isLoading = isLoading.value,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

enum class HomeTabs(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String
) {
    Movies(
        selectedIcon = Icons.Filled.Movie,
        unselectedIcon = Icons.Outlined.Movie,
        title = "Movies"
    ),
    TvShows(
        selectedIcon = Icons.Filled.LiveTv,
        unselectedIcon = Icons.Outlined.LiveTv,
        title = "TV Shows"
    )
}