package com.irmak.cleanarcremoteanddb.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.irmak.cleanarcremoteanddb.domain.model.Movie
import com.irmak.cleanarcremoteanddb.ui.ComposePaging3CachingTheme

//@Composable
//fun MovieList(
//    movieList: List<Movie>,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier
//    ) {
//        for (movie in movieList) {
//            MovieItem(
//                movie = movie,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp)
//            )
//        }
//    }
//}
//
//@Composable
//fun MovieItem(
//    movie: Movie,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier,
//        elevation = 4.dp
//    ) {
//        // MovieItem içeriği burada...
//    }
//}
//
//@Composable
//fun MovieItemPreview() {
//    ComposePaging3CachingTheme {
//        val movieList = List(5) {
//            Movie(
//                id = it + 1,
//                title = "Movie ${it + 1}",
//                original_language = "Language",
//                overview = "Overview",
//                release_date = "Release Date"
//            )
//        }
//        MovieList(movieList = movieList, modifier = Modifier.fillMaxWidth())
//    }
//}

@Composable
fun MovieItem(
    movie: Movie?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2${movie?.poster_path}",
                contentDescription = movie?.title,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text =  movie?.title.toString(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie?.original_language.toString(),
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie?.overview.toString(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "çıkış tarihi ${movie?.release_date}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 8.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    ComposePaging3CachingTheme {
        MovieItem(
            movie = Movie(
                id = 1,
                title = "Movie",
                original_language = "Language",
                overview = "Overview",
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}