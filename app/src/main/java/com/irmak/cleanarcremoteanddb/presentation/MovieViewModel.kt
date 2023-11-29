package com.irmak.cleanarcremoteanddb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.irmak.cleanarcremoteanddb.data.local.MovieEntity
import com.irmak.cleanarcremoteanddb.data.mappers.toMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    pager: Pager<Int, MovieEntity>
) : ViewModel() {

    val moviePagingFlow = pager.flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
        .cachedIn(viewModelScope)

}

