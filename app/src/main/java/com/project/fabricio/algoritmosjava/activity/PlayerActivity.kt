package com.project.fabricio.algoritmosjava.activity

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.project.fabricio.algoritmosjava.R
import com.project.fabricio.algoritmosjava.helper.YouTubeConfig

class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var youTubePlayerView: YouTubePlayerView? = null
    private var idVideo: String? = null

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, foiRestaurado: Boolean) {

        youTubePlayer.setFullscreen(true)
        youTubePlayer.setShowFullscreenButton(false)
        youTubePlayer.loadVideo(idVideo)

    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        youTubePlayerView = findViewById(R.id.playerVideo)

        //bundle recebe o conteúdo que foi passado pela Intent
        val bundle = intent.extras

        if (bundle != null) {
            idVideo = bundle.getString("idVideo")
            youTubePlayerView!!.initialize(YouTubeConfig.CHAVE_YOUTUBE_API, this)
        }


    }
}
