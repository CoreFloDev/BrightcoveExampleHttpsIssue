package com.example.brightcoveexample

import android.os.Bundle
import android.view.WindowManager
import com.brightcove.player.appcompat.BrightcovePlayerActivity
import com.brightcove.player.edge.Catalog
import com.brightcove.player.edge.VideoListener
import com.brightcove.player.event.EventType
import com.brightcove.player.model.Video
import com.google.android.material.snackbar.Snackbar

class TimesBrightcovePlayerActivity : BrightcovePlayerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val accountId = intent.getStringExtra(KEY_ACCOUNT_ID)
        val policyKey = intent.getStringExtra(KEY_POLICY_KEY)
        val videoId = intent.getStringExtra(KEY_VIDEO_ID)
        val title = intent.getStringExtra(KEY_TITLE) ?: "defaultTitle"

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setTitle(title)

        setContentView(R.layout.activity_brightcove_player)

        val eventEmitter = baseVideoView.eventEmitter
        eventEmitter.on(EventType.ERROR) {
            val snackbar = Snackbar.make(
                findViewById(R.id.brightcove_video_view),
                R.string.video_error,
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar.setAction(R.string.video_error_dismiss) { snackbar.dismiss() }
            snackbar.show()
        }

        val catalog = Catalog(eventEmitter, accountId, policyKey)
        catalog.findVideoByID(videoId, object : VideoListener() {
            override fun onVideo(video: Video) {
                baseVideoView.add(video)
                baseVideoView.start()
            }
        })
    }

    companion object {

        private const val KEY_ACCOUNT_ID = "KEY_ACCOUNT_ID"
        private const val KEY_POLICY_KEY = "KEY_POLICY_KEY"
        private const val KEY_VIDEO_ID = "KEY_VIDEO_ID"
        private const val KEY_TITLE = "KEY_TITLE"

        @JvmOverloads
        @JvmStatic
        fun startingBundle(accountId: String, policyKey: String, videoId: String, title: String? = null) =
            Bundle().apply {
                putString(KEY_ACCOUNT_ID, accountId)
                putString(KEY_POLICY_KEY, policyKey)
                putString(KEY_VIDEO_ID, videoId)
                putString(KEY_TITLE, title)
            }
    }
}
