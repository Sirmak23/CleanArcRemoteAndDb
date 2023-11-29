package com.irmak.cleanarcremoteanddb.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.irmak.cleanarcremoteanddb.domain.model.Movie

@Composable
fun MovieScreen(
    movies: LazyPagingItems<Movie>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = movies.loadState) {
        if(movies.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (movies.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(movies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(movies) { movie ->
                    if(movie != null) {
                        MovieItem(
                            movie = movie,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    if(movies.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
//
//@Composable
//fun MovieScreen(
//    movies: LazyPagingItems<Movie>
//) {
//    val context = LocalContext.current
//    LaunchedEffect(key1 = movies.loadState) {
//        if(movies.loadState.refresh is LoadState.Error) {
//            Toast.makeText(
//                context,
//                "Error: " + (movies.loadState.refresh as LoadState.Error).error.message,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        if(movies.loadState.refresh is LoadState.Loading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        } else {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                items(movies) { movie ->
//                    movie?.results?.let { results ->
//                        for (result in results) {
//
//                        }
//                    }
//                }
//                item {
//                    if(movies.loadState.append is LoadState.Loading) {
//                        CircularProgressIndicator()
//                    }
//                }
//            }
//        }
//    }
//}
