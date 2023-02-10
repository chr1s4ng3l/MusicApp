package com.example.musicapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import com.example.musicapp.databinding.FragmentPlayBinding
import com.example.musicapp.viewModel.MusicViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource


class PlayFragment : Fragment() {


    private val binding by lazy {
        FragmentPlayBinding.inflate(layoutInflater)
    }

    private val musicViewModel: MusicViewModel by activityViewModels()

    private var uriTrack = ""
    private var exoPlayer: ExoPlayer? = null
    private var playBackPosition = 0L
    private var currentWindow = 0
    private var playWhenReady = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        uriTrack = musicViewModel.urlTrack
        preparePlayer()

        // Inflate the layout for this fragment
        return binding.root
    }


    //ExoPlayer settings
    private fun preparePlayer() {

        try {
            exoPlayer = context?.let { ExoPlayer.Builder(it).build() }
            exoPlayer?.playWhenReady = true
            binding.playerView.player = exoPlayer
            val mediaItem =
                MediaItem.fromUri(uriTrack)
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.seekTo(playBackPosition)
            exoPlayer?.playWhenReady = playWhenReady
            exoPlayer?.prepare()
        } catch (e: java.lang.Exception) {
            e.message
        }

    }


    private fun releaseExoPlayer() {
        exoPlayer?.let {
            playBackPosition = it.currentPosition
            playWhenReady = it.playWhenReady
            it.release()
            exoPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        releaseExoPlayer()
    }

    override fun onPause() {
        super.onPause()
        releaseExoPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseExoPlayer()
    }

    companion object {
        const val URL =
            "https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd"
    }


}