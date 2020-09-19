package com.example.kotlinvideoplayer.data.repository

import android.app.Application
import android.provider.MediaStore
import android.util.Log

class MovieRepository {

    fun getMovieList(application: Application, onResponse :onResponse) {
        application.contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,null,null,null)
            ?.use {cursor ->
                val movieList =ArrayList<MovieItem>()
                while (cursor.moveToNext()){
                    var title=cursor.getString(cursor.getColumnIndex((MediaStore.Video.Media.TITLE)))
                    var videoUrl=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                    movieList.add(MovieItem(title,videoUrl))
                    onResponse.onSuccess(movieList)
                }

                Log.d("TAGm","nodate")
            };

    }


    interface onResponse{
        fun onSuccess(data : List<MovieItem>)
    }
}