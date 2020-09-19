package com.example.kotlinvideoplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinvideoplayer.data.repository.MovieItem
import com.example.kotlinvideoplayer.databinding.ActivityMainBinding
import com.example.kotlinvideoplayer.ui.adapter.RecyclerviewAdapter
import com.example.kotlinvideoplayer.ui.viewmodel.MovieListViewModel


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupPermission()
    }
    private fun setupPermission(){
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.d("MAIN","ACTIVITY")
            requestPermission()
        }else{
            fetchData()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE
        )
    }

    private fun fetchData() {

        val movieListViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(MovieListViewModel::class.java)
        movieListViewModel.fetch()

        val recyclerView = findViewById(R.id.my_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieListViewModel.getMovieList().observe(this, Observer<List<MovieItem>> { movieList ->
            recyclerView.adapter = RecyclerviewAdapter(movieList)
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "PERMISSION IS DENIED", Toast.LENGTH_LONG).show()
                } else {
                    fetchData()
                }
            }
        }
    }
}