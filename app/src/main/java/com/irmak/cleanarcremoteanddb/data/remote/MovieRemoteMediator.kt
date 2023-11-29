package com.irmak.cleanarcremoteanddb.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.irmak.cleanarcremoteanddb.data.local.MovieDatabase
import com.irmak.cleanarcremoteanddb.data.local.MovieEntity
import com.irmak.cleanarcremoteanddb.data.mappers.toMovieEntityList
import com.irmak.cleanarcremoteanddb.di.pageNoo
import retrofit2.HttpException
import java.io.IOException

//@OptIn(ExperimentalPagingApi::class)
//class MovieRemoteMediator(
//    private val movieDb:MovieDatabase,
//    private val movieApi: MovieApi
//):RemoteMediator<Int,MovieEntity>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, MovieEntity>
//    ): MediatorResult {
//
//        val movieDto = movieApi.getPopularList(pageNo = 1)
//        val movieList: List<MovieRespons>? = movieDto.results
//
//        return try {
//            val loadKey = when(loadType) {
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND -> return MediatorResult.Success(
//                    endOfPaginationReached = true
//                )
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if(lastItem == null) {
//                        1
//                    } else {
//                        + 1
//                    }
//                }
//            }
//
////            val movies = movieApi.getPopularList(
////                pageNo = loadKey,
////            )
//            val movieDto = movieApi.getPopularList(pageNo = loadKey)
//            val movieList: List<MovieRespons>? = movieDto.results
//
//            movieDb.withTransaction {
//                if(loadType == LoadType.REFRESH) {
//                    movieDb.dao.clearAll()
//                }
//                val movieEntities = movies.map { it.toMovieEntity() }
//                movieDb.dao.upsertAll(movieEntities as List<MovieEntity>)
//            }
//
//            MediatorResult.Success(
//                endOfPaginationReached = movies.isEmpty()
//            )
//        } catch(e: IOException) {
//            MediatorResult.Error(e)
//        } catch(e: HttpException) {
//            MediatorResult.Error(e)
//        }
//    }
//}
//
//@Suppress("IMPLICIT_CAST_TO_ANY")
//@OptIn(ExperimentalPagingApi::class)
//class MovieRemoteMediator(
//    private val movieDb: MovieDatabase,
//    private val movieApi: MovieApi
//) : RemoteMediator<Int, MovieEntity>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, MovieEntity>
//    ): MediatorResult {
//        val movieDto = movieApi.getPopularList()
//        val movieList: List<MovieRespons>? = movieDto.results
//
//        return try {
//            val loadKey = when (loadType) {
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND ->  return MediatorResult.Success(
//                    endOfPaginationReached = true
//                )
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if (lastItem == null) {
//                        1
//                    } else {
//
//                    }
//                }
//            }
//
//            val movies = loadKey?.let { movieApi.getPopularList() }
//
//            movieDb.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    movieDb.dao.clearAll()
//                }
//                val movieEntities = movieDto.toMovieEntityList()
//                movieDb.withTransaction {
//                    if (loadType == LoadType.REFRESH) {
//                        movieDb.dao.clearAll()
//                    }
//                    movieDb.dao.upsertAll(movieEntities)
//
//                }
//                pageNoo++
//
//            }
//
//            MediatorResult.Success(
//                endOfPaginationReached = movies?.results.isNullOrEmpty()
//            )
//        } catch (e: IOException) {
//            MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            MediatorResult.Error(e)
//        }
//    }
//}

@Suppress("IMPLICIT_CAST_TO_ANY")
@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieDb: MovieDatabase,
    private val movieApi: MovieApi
) : RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            when (loadType) {
                LoadType.REFRESH -> pageNoo = 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        pageNoo = 1
                    } else {
                        pageNoo += 1
                    }
                }
            }

            Log.e("pagenoo", "$pageNoo")
            val movieDto = movieApi.getPopularList(pageNo = pageNoo)
            val movies = movieDto.results

            movieDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDb.dao.clearAll()
                }
                val movieEntities = movieDto.toMovieEntityList()
                movieDb.dao.upsertAll(movieEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = movies!!.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
