package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.genzinema.Controller.EpHandler;
import android.genzinema.Controller.FavoriteMovieHander;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.genzinema.R;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;

public class Activity_Watch_Movie extends AppCompatActivity {
    SimpleExoPlayer exoPlayer;
    Button btn_back, btn_addToCollection, btn_movieDetail;
    FavoriteMovieHander favoriteMovieHander;
    Handler handler;
    PlayerView playerView;
    String email = "";
    int idMV = 0;
    ProgressBar progressBar;
    Drawable drawable;
    PorterDuff.Mode mode;

    TextView title;
    MovieHandler movieHandler;
    int colorRed = Color.parseColor("#FF0000"); // Màu đỏ
    int colorWhite = Color.parseColor("#FFFFFF");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_watch_movie);
        addControls();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        idMV = intent.getIntExtra("idMV",0);
        movieHandler = new MovieHandler(getApplicationContext(), EpHandler.DB_NAME,null,1);
        Movie movieTitle = movieHandler.GetMovieByID(idMV);
        title.setText(movieTitle.getNameMovie());
        Log.d("SDSDDSSDS", String.valueOf(email));
        checkFMV();

        String vidUrl = intent.getStringExtra("vidUrl");
        Log.d("vidUrlafterclickinwatch", ""+vidUrl);
        exoPlayerCreate(vidUrl);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        addEvents();
    }
    private void addControls(){
        playerView = findViewById(R.id.player);
        progressBar = findViewById(R.id.progress_bar);
        btn_back = findViewById(R.id.btn_back);
        btn_movieDetail = findViewById(R.id.btn_toMovieDetail);
        btn_addToCollection = findViewById(R.id.btn_addToCollections);
        title = findViewById(R.id.titleWatchMovie);
        favoriteMovieHander = new FavoriteMovieHander(getApplicationContext(),FavoriteMovieHander.DB_NAME,null,1);
    }
    private void checkFMV()
    {
        drawable = btn_addToCollection.getCompoundDrawables()[0];
        mode= PorterDuff.Mode.SRC_ATOP; // Chế độ tô màu (hoặc bạn có thể sử dụng một hằng số khác từ PorterDuff.Mode)
        if(favoriteMovieHander.existInFMV(email,idMV) == true)
        {
            drawable.setColorFilter(colorRed, mode);
            btn_addToCollection.setTextColor(colorRed);
            btn_addToCollection.setCompoundDrawables(drawable, null, null, null);
        }
        else
        {
            drawable.setColorFilter(colorWhite, mode);
            btn_addToCollection.setTextColor(colorWhite);
            btn_addToCollection.setCompoundDrawables(drawable, null, null, null);
        }
    }
    private void addEvents(){
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        playerView.setControllerShowTimeoutMs(3000);
        playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
            @Override
            public void onVisibilityChange(int visibility) {
                Log.d("state", String.valueOf(visibility));
                if (visibility == View.VISIBLE) {
                    playerView.startAnimation(fadeInAnimation);
                } else {
                    playerView.startAnimation(fadeOutAnimation);
                }
            }
        });

        exoPlayer.addListener(new Player.Listener() {

            @Override
            public void onPlaybackStateChanged(int playbackState) {
                Player.Listener.super.onPlaybackStateChanged(playbackState);
                if (playbackState == Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                }
            }

        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentReturn = getIntent();
//                email = intentReturn.getStringExtra("email");
//                idMV = intentReturn.getIntExtra("idMV",0);
//                Intent intent = new Intent(Activity_Watch_Movie.this, Activity_Detail_Movie.class);
//                Log.d("aa", "onClick: "+idMV);
//                movieHandler = new MovieHandler(getApplicationContext(),FavoriteMovieHander.DB_NAME,null,1);
//                Movie movie = movieHandler.GetMovieByID(idMV);
//                intent.putExtra("email",email);
//                intent.putExtra("idMV", idMV);
//                intent.putExtra("idGenreMV",movie.getIdGenre());
//                intent.putExtra("idStyleMV",movie.getIdType());
//                startActivity(intent);
                finish();
            }
        });
        btn_addToCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getApplicationContext(),favoriteMovieHander.AddOrDelete(email, idMV),Toast.LENGTH_SHORT).show();
               checkFMV();
            }
        });
        btn_movieDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Activity_Watch_Movie.this, Activity_Detail_Movie.class);
//                intent.putExtra("email",email);
//                intent.putExtra("idMV",idMV);
//                startActivity(intent);
                finish();
            }
        });

    }
    private void exoPlayerCreate(String vidUrl){
        handler = new Handler(Looper.getMainLooper());
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

        String videoUrlStr = "https://drive.google.com/uc?id=" + vidUrl;
        Uri videoUrl = Uri.parse(videoUrlStr);
//        Log.d("vidUrlFULLafterclickinwatch", ""+videoUrl);

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoUrl));

        playerView.setPlayer(exoPlayer);
        playerView.setKeepScreenOn(true);
        exoPlayer.setMediaSource(mediaSource);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.setPlayWhenReady(false);
        exoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.getPlaybackState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release();
        }
    }
}