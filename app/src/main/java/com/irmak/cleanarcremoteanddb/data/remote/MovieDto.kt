package com.irmak.cleanarcremoteanddb.data.remote


data class MovieDto(
    val page:Int? = null,
    val results: List<MovieRespons>?=null,
    val total_pages:Int?=null,
    val total_results:Int? = null
)

data class MovieRespons(
    val id: Int? = null,
    val poster_path: String? = null ,
    val backdrop_path: String? = null ,
    val adult: Boolean?= null,
    val overview: String?= null,
    val release_date: String? = null,
    val genre_ids: List<Int>? = null,
    val original_title: String? = null,
    val original_language: String? = null,
    val title: String? = null,
    val vote_count: Int? = null,
    val video: Boolean? = null,
)
