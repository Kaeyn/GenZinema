package android.genzinema.View;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.genzinema.Controller.FavoriteMovieHander;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.FavoriteMovie;
import android.genzinema.Model.Movie;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
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
    private boolean btnEpStateIsCollect = true;

    TextView tvTenMV,tvNamMV,tvDetailMV,tvActorMV,tvAuthorMV;
    ProgressBar pb;
    Button btnEp,btnSimilar, btnPlayVideo, btnAddList;
    ImageButton btnVolumeOn, btnVolumeMute;
    MovieHandler movieHandler;
    SimpleExoPlayer exoPlayer;
    Handler handler;
    PlayerView playerView;
    FavoriteMovieHander favoriteMovieHander;

    private String urlMovie = "";


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
//        Toast.makeText(getContext(),"FragmentDetailMV ",Toast.LENGTH_SHORT).show();

        String keySearchTo = "keyMain";
        String keyHometo = "keyDetailMV";

        if(getContext() instanceof MainHome){
            HandldBundle(keyHometo);
        }
        else {
            HandldBundle(keySearchTo);
        }

    }

    private void addControl(View view){
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

    private void addEvents(){

        btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WatchMovie.class);
                intent.putExtra("vidUrl", urlMovie);
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
                favoriteMovieHander = new FavoriteMovieHander(getContext(),FavoriteMovieHander.DB_NAME,null,1);
                favoriteMovieHander.loadData();
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
        addControl(view);
        exoPlayerCreate();
        addEvents();

        return view;
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.addToBackStack(null);
        ft.commit();
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

        String videoId = "1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW";
        String videoUrlStr = "https://drive.google.com/uc?export=download&id=" + videoId;
        Uri videoUrl = Uri.parse(videoUrlStr);

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoUrl));

        playerView.setPlayer(exoPlayer);
        playerView.setKeepScreenOn(true);
        exoPlayer.setMediaSource(mediaSource);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
    }
    @Override
    public void onPause() {
        super.onPause();
        exoPlayer.setPlayWhenReady(false);
        exoPlayer.getPlaybackState();
    }

    public void HandldBundle(String key){
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResultListener(key, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int idMV = result.getInt("idMV");
                int idGenre = result.getInt("idGenreMV");
                int idStyle = result.getInt("idStyleMV");
//                Toast.makeText(getContext(),"idMV "+idMV,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(),"idGenreMV "+idGenre,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(),"idStyleMV "+idStyle,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(),"DetailMovie idMV: "+idMV,Toast.LENGTH_SHORT).show();
                String textColorHexCodeRed = "#FF0909";
                String textColorHexCodeWhite = "#FFFFFF";
                int colorRed = Color.parseColor(textColorHexCodeRed);
                int colorWhite = Color.parseColor(textColorHexCodeWhite);

                movieHandler = new MovieHandler(getContext(),MovieHandler.DB_NAME,null,1);
                Movie movie = movieHandler.GetMovieByID(idMV);
                tvTenMV.setText(movie.getNameMovie());
                tvNamMV.setText(movie.getYearProduce());
                tvActorMV.setText("Diễn viên: "+movie.getActors());
                tvAuthorMV.setText("Đạo diễn: "+movie.getAuthors());
                tvDetailMV.setText(movie.getDetail());

                if(idStyle==1) {
                    btnSimilar.setTextColor(colorWhite);
                    btnEp.setTextColor(colorRed);
                    if (btnEpStateIsCollect) {
                        btnEpStateIsCollect = false;
                        Bundle results = new Bundle();
                        results.putInt("idMV", idMV);
                        results.putInt("idGenreMV", idGenre);
                        results.putInt("idStyleMV", idStyle);
                        getParentFragmentManager().setFragmentResult("collectsMV", results);
                        loadFragment(new FragmentCollect());
                    }
                } else {
                    btnSimilar.setTextColor(colorWhite);
                    btnEp.setTextColor(colorRed);
                    if (btnEpStateIsCollect) {
                        btnEpStateIsCollect = false;
                        Bundle results = new Bundle();
                        results.putInt("idMV", idMV);
                        results.putInt("idGenreMV", idGenre);
                        results.putInt("idStyleMV", idStyle);
                        getParentFragmentManager().setFragmentResult("keyEpsMV", results);
                        loadFragment(new FragmentEps());
                    }
                }




                if(idStyle==1){
                    btnEp.setText("Bộ sưu tập");
                    btnEp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnSimilar.setTextColor(colorWhite);
                            btnEp.setTextColor(colorRed);
                            if(btnEpStateIsCollect){
                                btnEpStateIsCollect = false;
                                Bundle results = new Bundle();
                                results.putInt("idMV", idMV);
                                results.putInt("idGenreMV", idGenre);
                                results.putInt("idStyleMV", idStyle);
                                getParentFragmentManager().setFragmentResult("collectsMV", results);
                                loadFragment(new FragmentCollect());
                            }
                        }

                    });
                } else {
                    btnEp.setText("Các tập");

                    btnEp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnEp.setTextColor(colorRed);
                            btnSimilar.setTextColor(colorWhite);
                            if (btnEpStateIsCollect) {
                                btnEpStateIsCollect = false;
                                Bundle results = new Bundle();
                                results.putInt("idMV", idMV);
                                results.putInt("idGenreMV", idGenre);
                                results.putInt("idStyleMV", idStyle);
                                getParentFragmentManager().setFragmentResult("keyEpsMV", results);
                                loadFragment(new FragmentEps());
                            }
                        }

                    });
                }
                btnSimilar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnEp.setTextColor(colorWhite);
                        btnSimilar.setTextColor(colorRed);
                        if(!btnEpStateIsCollect) {
                            btnEpStateIsCollect = true;
                            Bundle results = new Bundle();
                            results.putInt("idMV", idMV);
                            results.putInt("idGenreMV", idGenre);
                            getParentFragmentManager().setFragmentResult("similarMV", results);
                            loadFragment(new FragmentSimilarStyle());
                        }
                    }
                });

            }

        });
    }

}