package com.example.kotlinvideoplayer.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinvideoplayer.data.repository.MovieItem
import com.example.kotlinvideoplayer.data.repository.MovieRepository

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    var repository :MovieRepository= MovieRepository()
    var liveData = MutableLiveData<List<MovieItem>>()
    private lateinit var movieMutableLiveData: MutableLiveData<List<String>>
    fun fetch() {
        repository.getMovieList(application =getApplication(), onResponse = object : MovieRepository.onResponse {
            override fun onSuccess(data: List<MovieItem>) {
                liveData.postValue(data)
            }
        })
    }

    fun getMovieList() : LiveData<List<MovieItem>>{
        return liveData
    }
}




