package com.irmak.cleanarcremoteanddb.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.irmak.cleanarcremoteanddb.data.local.MovieDatabase
import com.irmak.cleanarcremoteanddb.data.local.MovieEntity
import com.irmak.cleanarcremoteanddb.data.remote.MovieApi
import com.irmak.cleanarcremoteanddb.data.remote.MovieRemoteMediator
import com.irmak.cleanarcremoteanddb.data.remote.MovieRespons
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movies.db"
        )

            .build()
    }
//    fallbackToDestructiveMigration()// bunu versiyon hatası alırsan database i sıfırlamak için kullanabilirim .build() den önce yaz

    //    @Provides
//    @Singleton
//    fun provideMovieApi(): MovieApi {
//        return Retrofit.Builder()
//            .baseUrl(MovieApi.BASE_URL)
//            .addConverterFactory(KotlinJsonAdapterFactory())
//            .build()
//            .create()
//    }
@Provides
@Singleton
fun provideMovieApi(): MovieApi {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Bu satırı ekledik
        .build()

    return Retrofit.Builder()
        .baseUrl(MovieApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // Bu satırı değiştirdik
        .build()
        .create(MovieApi::class.java)
}

    @Provides
    @Singleton
    fun provideMoviePager(movieDb: MovieDatabase, movieApi: MovieApi): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(
                movieDb = movieDb,
                movieApi = movieApi
            ),
            pagingSourceFactory = {
                movieDb.dao.pagingSource()
            }
        )
    }
}