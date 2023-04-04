package com.batueksi.moviesapp.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.batueksi.moviesapp.databinding.VideoRowPlaceholderBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class YoutubePlay(private val videoKey: String) : AppCompatDialogFragment() {

    private lateinit var binding: VideoRowPlaceholderBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = VideoRowPlaceholderBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        lifecycle.addObserver(binding.video)
        binding.video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                super.onError(youTubePlayer, error)
                Toast.makeText(context, "Some error occurred!", Toast.LENGTH_SHORT).show()
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(videoKey, 0f)
            }
        })
        return builder.create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        binding.video.release()
    }
}