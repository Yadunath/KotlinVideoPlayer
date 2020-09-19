package com.example.kotlinvideoplayer.player

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinvideoplayer.R
import com.example.kotlinvideoplayer.databinding.ActivityMainBinding
import com.example.kotlinvideoplayer.databinding.VideoViewBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class VideoActivity : AppCompatActivity(){
    private lateinit var playerView: PlayerView
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var context: Context
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: VideoViewBinding =
            DataBindingUtil.setContentView(this, R.layout.video_view)
        playerView = findViewById(R.id.player_view)
        initialisePlayer()
    }

    override fun onStop() {
        simpleExoPlayer.stop()
        simpleExoPlayer.release()
        super.onStop()
    }

    private fun initialisePlayer(){
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this)
        var url=intent.getStringExtra("PUT_EXTRA")
        mediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"))
        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory).createMediaSource(
            Uri.parse(url))
        simpleExoPlayer.prepare(mediaSource, false, false)
        simpleExoPlayer.playWhenReady = true
        playerView.setShutterBackgroundColor(Color.TRANSPARENT)
        playerView.player = simpleExoPlayer
        playerView.requestFocus()
    }




}