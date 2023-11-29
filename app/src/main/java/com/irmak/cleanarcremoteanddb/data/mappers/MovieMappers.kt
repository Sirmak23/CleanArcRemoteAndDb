package com.irmak.cleanarcremoteanddb.data.mappers

import com.irmak.cleanarcremoteanddb.data.local.MovieEntity
import com.irmak.cleanarcremoteanddb.data.remote.MovieDto
import com.irmak.cleanarcremoteanddb.domain.model.Movie

//fun MovieDto.toMovieEntities():MovieEntity {
//    return results?.map { movieResponse ->
//        MovieEntity(
//            id = movieResponse.id,
//            title = movieResponse.title,
//            overview = movieResponse.overview,
//            posterPath = movieResponse.poster_path
//        )
//    } ?: emptyList()
//}
//
//fun MovieDto.toMovieEntity(): MovieEntity? {
//    val firstMovieResponse = results?.firstOrNull()
//
//    return if (firstMovieResponse != null) {
//        MovieEntity(
//            id = firstMovieResponse.id,
//            title = firstMovieResponse.title,
//            overview = firstMovieResponse.overview,
//            posterPath = firstMovieResponse.poster_path
//        )
//    } else {
//        null
//    }
//}
fun MovieDto.toMovieEntityList(): List<MovieEntity> {
    return results?.mapNotNull { movieResponse ->
        MovieEntity(
            id = movieResponse.id,
            title = movieResponse.title,
            overview = movieResponse.overview,
            posterPath = movieResponse.poster_path
        )
    } ?: emptyList()
}


fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        poster_path = posterPath
    )
}