package android.genzinema.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.genzinema.R;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.device.DeviceInfo;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;

import java.util.List;

public class WatchMovie extends AppCompatActivity {
    SimpleExoPlayer exoPlayer;
    ImageView bt_fullscreen;
    boolean isFullScreen=false;
    boolean isLock = false;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movie);
        handler = new Handler(Looper.getMainLooper());
        PlayerView playerView = findViewById(R.id.player);
        ProgressBar progressBar = findViewById(R.id.progress_bar);

//        exoPlayer =  new SimpleExoPlayer.Builder(this)
//                .setSeekBackIncrementMs(10000)
//                .setSeekForwardIncrementMs(10000).build();
//
//        playerView.setPlayer(exoPlayer);


        // Create a DefaultRenderersFactory to be used by the ExoPlayer
        RenderersFactory renderersFactory = new DefaultRenderersFactory(this);

        // Create a DefaultTrackSelector to be used by the ExoPlayer
        TrackSelector trackSelector = new DefaultTrackSelector(this);

        // Create the ExoPlayer instance
        exoPlayer = new SimpleExoPlayer.Builder(this)
                .setTrackSelector(trackSelector)
                .build();

        // Create a DefaultHttpDataSource.Factory to provide the media data
        exoPlayer.setAudioAttributes(AudioAttributes.DEFAULT, true);

        String userAgent = Util.getUserAgent(this, getString(R.string.app_name));
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory()
                .setUserAgent(userAgent)
                .setAllowCrossProtocolRedirects(true);

//        String videoId = "1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW";
//        String videoUrlStr = "https://drive.google.com/uc?export=download&id=" + videoId;
//        Uri videoUrl = Uri.parse(videoUrlStr);
        Uri videoUrl = Uri.parse("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoUrl));
        exoPlayer.setMediaSource(mediaSource);
        exoPlayer.prepare();
        exoPlayer.play();

        playerView.setPlayer(exoPlayer);

        exoPlayer.addListener(new Player.Listener() {

            @Override
            public void onPlaybackStateChanged(int playbackState) {
                Player.Listener.super.onPlaybackStateChanged(playbackState);
                if (playbackState == Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);

                }

//                if (!exoPlayer.getPlayWhenReady()){
//                    handler.removeCallbacks(updateProgressAction);
//                }
//                else {
//                    onProgress();
//                }
            }
        });
    }
//    private Runnable updateProgressAction = () -> onProgress();
//
//    private void onProgress(){
//        ExoPlayer player = exoPlayer;
//        long position = player == null? 0 : player.getCurrentPosition();
//        handler.removeCallbacks(updateProgressAction);
//        int playbackState = player ==null? Player.STATE_IDLE : player.getPlaybackState();
//        if(playbackState != Player.STATE_IDLE && playbackState!= Player.STATE_ENDED)
//        {
//            long delayMs ;
//            if(player.getPlayWhenReady() && playbackState == Player.STATE_READY)
//            {
//                delayMs  = 1000 - position % 1000;
//                if(delayMs < 200)
//                {
//                    delayMs+=1000;
//                }
//            }
//            else{
//                delayMs = 1000;
//            }
//
//            handler.postDelayed(updateProgressAction,delayMs);
//        }
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release();
        }
    }
}