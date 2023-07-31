package android.genzinema.View.Fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.Handler.FavoriteMovieHander;
import android.genzinema.Controller.Handler.MovieHandler;
import android.genzinema.Model.Movie;
import android.genzinema.View.Activity.WatchMovie;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetailMovie#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FragmentDetailMovie extends Fragment {

    boolean btnEpStateIsCollect = true;
    String email = "",UrlTrailer, UrlMovie;
    int movieID = 0, idGenre, idStyle;
    Movie movie = new Movie();
    SQLiteDatabase db;
    MovieHandler movieHandler;

    TextView tvTenMV,tvNamMV,tvDetailMV,tvActorMV,tvAuthorMV;
    ProgressBar pb;
    Button btnEp,btnSimilar, btnPlayVideo, btnAddList;
    ImageButton btnVolumeOn, btnVolumeMute;


    SimpleExoPlayer exoPlayer;
    Handler handler;
    PlayerView playerView;
    FavoriteMovieHander favoriteMovieHander;
    ScrollView scrollView;
    Animation fadeInAnimate,fadeOutAnimation;


    int colorRed = Color.parseColor("#FF0000"); // Màu đỏ
    int colorWhite = Color.parseColor("#FFFFFF");




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDetailMovie() {
        // Required empty public constructor
    }
    public FragmentDetailMovie(String Email, int MovieID) {
        // Required empty public constructor
        email = Email;
        movieID = MovieID;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailMovie.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetailMovie newInstance(String param1, String param2) {
        FragmentDetailMovie fragment = new FragmentDetailMovie();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1); 
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private void addControl(View view){
        scrollView = view.findViewById(R.id.scrollViewDetailMovie);
        pb = view.findViewById(R.id.pbDetailMV);
        btnEp = view.findViewById(R.id.btnEps);
        btnSimilar = view.findViewById(R.id.btnSimilarStyle);
        tvActorMV = view.findViewById(R.id.tvActorMV);
        tvDetailMV = view.findViewById(R.id.tvDetailMV);
        tvAuthorMV = view.findViewById(R.id.tvAuthorMV);
        tvNamMV = view.findViewById(R.id.tvNamMV);
        tvTenMV = view.findViewById(R.id.tvTenMV);
        playerView = view.findViewById(R.id.player);
        btnVolumeOn = view.findViewById(R.id.btnVolumeOn);
        btnVolumeMute = view.findViewById(R.id.btnVolumeOff);
        btnPlayVideo = view.findViewById(R.id.btnPlayVideo_Detail);
        btnAddList = view.findViewById(R.id.btnAddDanhSach);
    }
    private void checkFMV(){
        if(favoriteMovieHander.existInFMV(email, movieID))
            btnAddList.setTextColor(colorRed);
        else
            btnAddList.setTextColor(colorWhite);
    }

    private void addEvents(){
        btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WatchMovie.class);
                intent.putExtra("email",email);
                intent.putExtra("vidUrl", UrlMovie);
                intent.putExtra("idGenreMV", idGenre);
                intent.putExtra("idStyleMV",idStyle);
                intent.putExtra("idMV", movieID);
                startActivity(intent);
            }
        });
        btnVolumeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.setVolume(0);
            }
        });
        btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),favoriteMovieHander.AddOrDelete(email, movieID),Toast.LENGTH_SHORT).show();
                checkFMV();
            }
        });
        btnVolumeMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.setVolume(1);
            }
        });
        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onVolumeChanged(float volume) {
                Player.Listener.super.onVolumeChanged(volume);
                if (volume == 0.0f){
                    btnVolumeMute.setVisibility(View.VISIBLE);
                    btnVolumeOn.setVisibility(View.GONE);
                } else {
                    btnVolumeMute.setVisibility(View.GONE);
                    btnVolumeOn.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                Player.Listener.super.onPlaybackStateChanged(playbackState);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        favoriteMovieHander = new FavoriteMovieHander(getContext(),FavoriteMovieHander.DB_NAME,null,1);
        movieHandler = new MovieHandler(getContext(),MovieHandler.DB_NAME,null,1);
        favoriteMovieHander.onCreate(db);
        fadeInAnimate = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        HandleBundle(view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exoPlayerCreate();
                playerView.setControllerShowTimeoutMs(3000);
                playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
                    @Override
                    public void onVisibilityChange(int visibility) {
                        if (visibility == View.VISIBLE) {
                            playerView.startAnimation(fadeInAnimate);
                        } else {
                            playerView.startAnimation(fadeOutAnimation);
                        }
                    }
                });
                addEvents();
            }
        }, 200);
        return view;
    }


    private void applyFadeInAnimationToChildren(ViewGroup viewGroup, Animation animation) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            childView.startAnimation(animation);

            // If the child is another ViewGroup (e.g., LinearLayout), apply the animation to its children recursively
            if (childView instanceof ViewGroup) {
                applyFadeInAnimationToChildren((ViewGroup) childView, animation);
            }
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }


    @Override
    public void onPause() {
        super.onPause();
        exoPlayer.setPlayWhenReady(false);
        exoPlayer.getPlaybackState();
    }

    private void HandleBundle(View view){
        addControl(view);
        movie = movieHandler.getMovieByID(movieID);
        applyFadeInAnimationToChildren(scrollView, fadeInAnimate);
        favoriteMovieHander = new FavoriteMovieHander(getContext(),FavoriteMovieHander.DB_NAME,null,1);
        checkFMV();
        tvTenMV.setText(movie.getNameMovie());
        tvNamMV.setText(movie.getYearProduce());
        tvActorMV.setText("Diễn viên: "+movie.getActors());
        tvAuthorMV.setText("Đạo diễn: "+movie.getAuthors());
        tvDetailMV.setText(movie.getDetail());
        UrlTrailer = movie.getUrlTrailer();
        UrlMovie = movie.getUrlVideo();

        Bundle results = new Bundle();
        results.putInt("idMV", movieID);
        results.putString("email",email);
        results.putInt("idGenreMV", idGenre);
        results.putInt("idStyleMV", idStyle);

        if(idStyle==1) {
            btnSimilar.setTextColor(colorWhite);
            btnEp.setTextColor(colorRed);
            if (btnEpStateIsCollect) {
                btnEpStateIsCollect = false;
                getParentFragmentManager().setFragmentResult("collectsMV", results);
                loadFragment(new FragmentCollect(idGenre, idStyle));
            }
            btnEp.setText("Phim lẻ");
            btnEp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnSimilar.setTextColor(colorWhite);
                    btnEp.setTextColor(colorRed);
                    if(btnEpStateIsCollect){
                        btnEpStateIsCollect = false;
                        loadFragment(new FragmentCollect(idGenre,idStyle));
                    }
                }

            });
        } else {
            btnSimilar.setTextColor(colorWhite);
            btnEp.setTextColor(colorRed);
            if (btnEpStateIsCollect) {
                btnEpStateIsCollect = false;
                getParentFragmentManager().setFragmentResult("keyEpsMV", results);
                loadFragment(new FragmentEps(movieID,email));
            }
            btnEp.setText("Các tập");
            btnEp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnEp.setTextColor(colorRed);
                    btnSimilar.setTextColor(colorWhite);
                    if (btnEpStateIsCollect) {
                        btnEpStateIsCollect = false;
                        loadFragment(new FragmentEps(movieID,email));
                    }
                }

            });
        }
        fadeInAnimate = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

        exoPlayerCreate();
        addEvents();

        playerView.setControllerShowTimeoutMs(3000);
        playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
            @Override
            public void onVisibilityChange(int visibility) {
                Log.d("state", String.valueOf(visibility));
                if (visibility == View.VISIBLE) {
                    playerView.startAnimation(fadeInAnimate);
                } else {
                    playerView.startAnimation(fadeOutAnimation);
                }
            }
        });
        btnSimilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEp.setTextColor(colorWhite);
                btnSimilar.setTextColor(colorRed);
                if(!btnEpStateIsCollect) {
                    btnEpStateIsCollect = true;
                    loadFragment(new FragmentSimilarStyle(idGenre));
                }
            }
        });
        exoPlayerCreate();

    }
    private void exoPlayerCreate(){
        handler = new Handler(Looper.getMainLooper());
        // Create a DefaultRenderersFactory to be used by the ExoPlayer
        RenderersFactory renderersFactory = new DefaultRenderersFactory(getContext());
        // Create a DefaultTrackSelector to be used by the ExoPlayer
        TrackSelector trackSelector = new DefaultTrackSelector(getContext());
        // Create the ExoPlayer instance
        exoPlayer = new SimpleExoPlayer.Builder(getContext())
                .setTrackSelector(trackSelector)
                .build();
        // Create a DefaultHttpDataSource.Factory to provide the media data
        exoPlayer.setAudioAttributes(AudioAttributes.DEFAULT, true);
        String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory()
                .setUserAgent(userAgent)
                .setAllowCrossProtocolRedirects(true);

        String videoUrlStr = "https://drive.google.com/uc?id=" + UrlTrailer;
        Log.d("URLFULL", ""+videoUrlStr);
        Uri videoUrl = Uri.parse(videoUrlStr);


        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoUrl));

        playerView.setPlayer(exoPlayer);
        playerView.setKeepScreenOn(true);
        exoPlayer.setMediaSource(mediaSource);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}