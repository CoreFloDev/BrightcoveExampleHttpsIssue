package com.example.brightcoveexample;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.brightcove.player.appcompat.BrightcovePlayerActivity;
import com.brightcove.player.edge.Catalog;
import com.brightcove.player.edge.VideoListener;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.model.Video;
import com.google.android.material.snackbar.Snackbar;

public class TimesBrightcovePlayerActivity extends BrightcovePlayerActivity {

    private static String KEY_ACCOUNT_ID = "KEY_ACCOUNT_ID";
    private static String KEY_POLICY_KEY = "KEY_POLICY_KEY";
    private static String KEY_VIDEO_ID = "KEY_VIDEO_ID";

    public static Bundle startingBundle(String accountId, String policyKey, String videoId) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ACCOUNT_ID, accountId);
        bundle.putString(KEY_POLICY_KEY, policyKey);
        bundle.putString(KEY_VIDEO_ID, videoId);
        return bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String accountId = getIntent().getStringExtra(KEY_ACCOUNT_ID);
        String policyKey = getIntent().getStringExtra(KEY_POLICY_KEY);
        String videoId = getIntent().getStringExtra(KEY_VIDEO_ID);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_brightcove_player);

        EventEmitter eventEmitter = baseVideoView.getEventEmitter();
        eventEmitter.on(EventType.ERROR, new EventListener() {
            @Override
            public void processEvent(Event event) {
                final Snackbar snackbar = Snackbar.make(
                        findViewById(R.id.brightcove_video_view),
                        R.string.video_error,
                        Snackbar.LENGTH_INDEFINITE
                );
                snackbar.setAction(R.string.video_error_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });

        Catalog catalog = new Catalog(eventEmitter, accountId, policyKey);
        catalog.findVideoByID(videoId,
                new VideoListener() {
                    @Override
                    public void onVideo(Video video) {
                        baseVideoView.add(video);
                        baseVideoView.start();
                    }
                }
        );
    }
}
