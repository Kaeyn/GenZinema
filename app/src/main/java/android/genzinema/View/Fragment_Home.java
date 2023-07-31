package android.genzinema.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.CustomAdapterRecyFilm;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Controller.RecyclerItemTouchListener;
import android.genzinema.Model.Movie;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment implements CustomAdapterRecyFilm.OnItemClickListener{
String email;
    AppBarLayout appBarLayout;
    NestedScrollView nestedScrollView;
    Button btnMovie, btnPhim, btnGenres, btnCloseGenres, btnAnime, btnHanhDong, btnHaiHuoc, btnKinhDi, btnTinhCam, btnPhat, btnDanhSach;

    TextView tvTrangChu;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean isScrolling = false;

    private GradientDrawable originalBackgroundDrawable;

    private int scrollY = 0;
    private int threshold = 20;

    private int recommendedMovieId = 0;
    Dialog dialog;
    LinearLayout recommendedBackground;

    View tiltleKinhDi, tiltleAnime, tiltleHanhDong,tiltleHaiHuoc,tiltleTinhCam;

    MovieHandler movieHandler;;
    SQLiteDatabase db;
    ArrayList<String> type_of_filmArrayList = new ArrayList<>();

    RecyclerView recyclerViewPhimThinhHanh, recyclerViewPhimAnime, recyclerViewPhimHanhDong, recyclerViewPhimKinhDi,recyclerViewPhimTinhCam,recyclerViewPhimHaiHuoc;
    ImageView imgFilm;

    FrameLayout frameLayout;
    // array list phim thinh hanh
//    ArrayList<Movie> arrayListPhimThinhHanh = new ArrayList<>();

    // array list phim anime
    ArrayList<Movie> arrayListPhimAnime = new ArrayList<>();

    // array list phim hanh dong
    ArrayList<Movie> arrayListPhimHanhDong = new ArrayList<>();

    // array list phim kinh di
    ArrayList<Movie> arrayListPhimKinhDi = new ArrayList<>();
    // array list phim hai huoc
    ArrayList<Movie> arrayListPhimHaiHuoc = new ArrayList<>();
    // array list phim tinh cam
    ArrayList<Movie> arrayListPhimTinhCam = new ArrayList<>();

    CustomAdapterRecyFilm adapterRecyFilmHanhDong;
    CustomAdapterRecyFilm adapterRecyFilmKinhDi;
    CustomAdapterRecyFilm adapterRecyFilmTinhCam;

    CustomAdapterRecyFilm adapterRecyFilmHaiHuoc;
    CustomAdapterRecyFilm adapterRecyFilmAnime;

    DividerItemDecoration ItemDecoKinhDi,ItemDecoAnime,ItemDecoHaiHuoc,ItemDecoHanhDong,ItemDecoTinhCam;

    RecyclerItemTouchListener touchListenerAnime,touchListenerHanhDong,touchListenerHaiHuoc,touchListenerKinhDi,touchListenerTinhCam;







    Animation fadeInAnimation;

    public Fragment_Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Content.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Home newInstance(String param1, String param2) {
        Fragment_Home fragment = new Fragment_Home();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment__home, container, false);
        movieHandler = new MovieHandler(getContext(),MovieHandler.DB_NAME,null,1);
        View view = getLayoutInflater().inflate(R.layout.display_genres, null);


        addRootViewControls(rootView);
        addViewControls(view);

        fadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_home);
        applyFadeInAnimationToChildren(nestedScrollView, fadeInAnimation);



        Movie recommendedMovie = movieHandler.GetRecommendedMovie();
        recommendedMovieId = recommendedMovie.getIdMV();
        recommendedBackground.setBackgroundResource(recommendedMovie.getIdThumbnails());

        // Initialize your ArrayList and populate it with data
        type_of_filmArrayList = new ArrayList<>();
        type_of_filmArrayList.add("Thể loại");
        type_of_filmArrayList.add("Hành động");
        type_of_filmArrayList.add("Kinh dị");
        type_of_filmArrayList.add("Hài hước");
        type_of_filmArrayList.add("Animme");


        dialog = new Dialog(getActivity(), R.style.CustomDialog);
        dialog.setContentView(view);

        originalBackgroundDrawable = (GradientDrawable) btnAnime.getBackground();

        loadArrayListData();
        addEvents();


        return rootView;
    }

    private void addRecycleViewEvents() {

        recyclerViewPhimAnime.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        touchListenerAnime = createOnItemTouchListenerEvent(recyclerViewPhimAnime, adapterRecyFilmAnime);
        recyclerViewPhimAnime.addOnItemTouchListener(touchListenerAnime);


        recyclerViewPhimHanhDong.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        touchListenerHanhDong = createOnItemTouchListenerEvent(recyclerViewPhimAnime, adapterRecyFilmHanhDong);
        recyclerViewPhimHanhDong.addOnItemTouchListener(touchListenerHanhDong);

        recyclerViewPhimKinhDi.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        touchListenerKinhDi = createOnItemTouchListenerEvent(recyclerViewPhimAnime, adapterRecyFilmKinhDi);
        recyclerViewPhimKinhDi.addOnItemTouchListener(touchListenerKinhDi);

        recyclerViewPhimHaiHuoc.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        touchListenerHaiHuoc = createOnItemTouchListenerEvent(recyclerViewPhimAnime, adapterRecyFilmHaiHuoc);
        recyclerViewPhimHaiHuoc.addOnItemTouchListener(touchListenerHaiHuoc);

        recyclerViewPhimTinhCam.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        touchListenerTinhCam = createOnItemTouchListenerEvent(recyclerViewPhimAnime, adapterRecyFilmTinhCam);
        recyclerViewPhimTinhCam.addOnItemTouchListener(touchListenerTinhCam);
    }

    private void addRecycleViewByGenres() {
        // Display list film of "Anime"
        ItemDecoAnime = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recyclerViewPhimAnime.addItemDecoration(ItemDecoAnime);
        RecyclerView.LayoutManager layoutManagerAnime;
        layoutManagerAnime = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimAnime.setLayoutManager(layoutManagerAnime);
        recyclerViewPhimAnime.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmAnime = new CustomAdapterRecyFilm(arrayListPhimAnime);
        recyclerViewPhimAnime.setAdapter(adapterRecyFilmAnime);


        // Display list film of "Hanh dong"
        ItemDecoHanhDong = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recyclerViewPhimHanhDong.addItemDecoration(ItemDecoHanhDong);
        RecyclerView.LayoutManager layoutManagerHanhDong;
        layoutManagerHanhDong = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimHanhDong.setLayoutManager(layoutManagerHanhDong);
        recyclerViewPhimHanhDong.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmHanhDong = new CustomAdapterRecyFilm(arrayListPhimHanhDong);
        recyclerViewPhimHanhDong.setAdapter(adapterRecyFilmHanhDong);


        // Display list film of "Kinh di"
        ItemDecoKinhDi = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recyclerViewPhimKinhDi.addItemDecoration(ItemDecoKinhDi);
        RecyclerView.LayoutManager layoutManagerKinhDi;
        layoutManagerKinhDi = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimKinhDi.setLayoutManager(layoutManagerKinhDi);
        recyclerViewPhimKinhDi.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmKinhDi = new CustomAdapterRecyFilm(arrayListPhimKinhDi);
        recyclerViewPhimKinhDi.setAdapter(adapterRecyFilmKinhDi);

        // Display list film of "Hai huoc"
        ItemDecoHaiHuoc = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recyclerViewPhimHaiHuoc.addItemDecoration(ItemDecoHaiHuoc);
        RecyclerView.LayoutManager layoutManagerHaiHuoc;
        layoutManagerHaiHuoc = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimHaiHuoc.setLayoutManager(layoutManagerHaiHuoc);
        recyclerViewPhimHaiHuoc.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmHaiHuoc = new CustomAdapterRecyFilm(arrayListPhimHaiHuoc);
        recyclerViewPhimHaiHuoc.setAdapter(adapterRecyFilmHaiHuoc);

        // Display list film of "Tinh cam"
        ItemDecoTinhCam = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        recyclerViewPhimTinhCam.addItemDecoration(ItemDecoTinhCam);
        RecyclerView.LayoutManager layoutManagerTinhCam;
        layoutManagerTinhCam = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimTinhCam.setLayoutManager(layoutManagerTinhCam);
        recyclerViewPhimTinhCam.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmTinhCam = new CustomAdapterRecyFilm(arrayListPhimTinhCam);
        Log.d("arr", "addRecycleViewByGenres: "+arrayListPhimTinhCam.size());
        recyclerViewPhimTinhCam.setAdapter(adapterRecyFilmTinhCam);

    }

    private void loadArrayListData() {
        // init data for "phim hanh dong"
        arrayListPhimHanhDong = movieHandler.getMoviesByGenre(1);

        // init data for "phim kinh di"
        arrayListPhimKinhDi = movieHandler.getMoviesByGenre(2);

        // init data for "phim tinh cam"
        arrayListPhimTinhCam = movieHandler.getMoviesByGenre(3);

        // init data for "phim hai huoc"
        arrayListPhimHaiHuoc = movieHandler.getMoviesByGenre(4);

        // init data for "phim anime"
        arrayListPhimAnime = movieHandler.getMoviesByGenre(5);
        addRecycleViewByGenres();
        addRecycleViewEvents();
    }

    private void addViewControls(View view){
        btnCloseGenres = view.findViewById(R.id.btnCloseGenres);
        btnAnime = view.findViewById(R.id.btnAnime);
        btnHanhDong = view.findViewById(R.id.btnHanhDong);
        btnHaiHuoc = view.findViewById(R.id.btnHaiHuoc);
        btnKinhDi = view.findViewById(R.id.btnKinhDi);
        btnTinhCam = view.findViewById(R.id.btnTinhCam);
        tvTrangChu = view.findViewById(R.id.tvTrangChu);
    }

    private void addRootViewControls(View rootView){
        frameLayout = rootView.findViewById(R.id.framelayout_content);
        btnMovie = rootView.findViewById(R.id.btnMovie);
        btnPhim = rootView.findViewById(R.id.btnPhim);
        recyclerViewPhimAnime = rootView.findViewById(R.id.recyViewPhimAnime);
        recyclerViewPhimHanhDong = rootView.findViewById(R.id.recyViewPhimHanhDong);
        recyclerViewPhimKinhDi = rootView.findViewById(R.id.recyViewPhimKinhDi);
        recyclerViewPhimHaiHuoc = rootView.findViewById(R.id.recyViewPhimHaiHuoc);
        recyclerViewPhimTinhCam = rootView.findViewById(R.id.recyViewPhimTinhCam);

        imgFilm = rootView.findViewById(R.id.imgHomeFilm);
        btnGenres = rootView.findViewById(R.id.btnGenres);
        appBarLayout = rootView.findViewById(R.id.appBarLayout);
        nestedScrollView = rootView.findViewById(R.id.nestedScrollHome);
        recommendedBackground = rootView.findViewById(R.id.homeRecommendBackground);
        btnPhat = rootView.findViewById(R.id.btnPhat);
        btnDanhSach = rootView.findViewById(R.id.btnDanhSach);
        tiltleKinhDi = rootView.findViewById(R.id.tiltleKinhDi);
        tiltleAnime = rootView.findViewById(R.id.tiltleAnime);
        tiltleHanhDong = rootView.findViewById(R.id.tiltleHanhDong);
        tiltleTinhCam = rootView.findViewById(R.id.tiltleTinhCam);
        tiltleHaiHuoc = rootView.findViewById(R.id.tiltleHaiHuoc);
    }

    private void addEvents(){
        String textColorHexCodeRed = "#FF0909";
        String textColorHexCodeWhite = "#FFFFFF";
        int colorRed = Color.parseColor(textColorHexCodeRed);
        int colorWhite = Color.parseColor(textColorHexCodeWhite);


        btnPhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("idMV", recommendedMovieId);
                getParentFragmentManager().setFragmentResult("keyDetailMV", bundle);
                loadFragment(new FragmentDetailMovie());
            }
        });


        btnPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshRecyclerView();
                btnPhim.setTextColor(colorRed);
                btnMovie.setTextColor(colorWhite);
                arrayListPhimHanhDong = movieHandler.getMoviesByGenreType(1,2);
                arrayListPhimKinhDi = movieHandler.getMoviesByGenreType(2,2);
                arrayListPhimTinhCam = movieHandler.getMoviesByGenreType(3,2);
                arrayListPhimHaiHuoc = movieHandler.getMoviesByGenreType(4,2);
                arrayListPhimAnime = movieHandler.getMoviesByGenreType(5,2);
                addRecycleViewByGenres();
                Log.d("test", "onClick: "+arrayListPhimTinhCam.size());
                addRecycleViewEvents();

            }
        });

        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshRecyclerView();
                btnMovie.setTextColor(colorRed);
                btnPhim.setTextColor(colorWhite);
                arrayListPhimHanhDong = movieHandler.getMoviesByGenreType(1,1);
                arrayListPhimKinhDi = movieHandler.getMoviesByGenreType(2,1);
                arrayListPhimTinhCam = movieHandler.getMoviesByGenreType(3,1);
                arrayListPhimHaiHuoc = movieHandler.getMoviesByGenreType(4,1);
                arrayListPhimAnime = movieHandler.getMoviesByGenreType(5,1);
                addRecycleViewByGenres();
                Log.d("test", "onClick: "+arrayListPhimTinhCam.size());
                addRecycleViewEvents();
            }
        });


        tvTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnCloseGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        btnGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        btnAnime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Change the background color of btnAnime when long-pressed
                btnAnime.setBackgroundColor(getResources().getColor(R.color.white_black, null));
                return true; // Return true to indicate that the long click event is consumed
            }
        });

        btnAnime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Reset the background color to its original state when long click is released
                    btnAnime.setBackground(originalBackgroundDrawable);
                    dialog.dismiss();
                    scrollToItemId(tiltleAnime);
                }
                return false;
            }
        });

        btnHaiHuoc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Change the background color of btnAnime when long-pressed
                btnHaiHuoc.setBackgroundColor(getResources().getColor(R.color.white_black, null));
                return true; // Return true to indicate that the long click event is consumed
            }
        });

        btnHaiHuoc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Reset the background color to its original state when long click is released
                    btnHaiHuoc.setBackground(originalBackgroundDrawable);
                    dialog.dismiss();
                    scrollToItemId(tiltleHaiHuoc);
                }
                return false;
            }
        });

        btnHanhDong.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Change the background color of btnAnime when long-pressed
                btnHanhDong.setBackgroundColor(getResources().getColor(R.color.white_black, null));
                return true; // Return true to indicate that the long click event is consumed
            }
        });

        btnHanhDong.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Reset the background color to its original state when long click is released
                    btnHanhDong.setBackground(originalBackgroundDrawable);
                    dialog.dismiss();
                    scrollToItemId(tiltleHanhDong);
                }
                return false;
            }
        });

        btnKinhDi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Change the background color of btnAnime when long-pressed
                btnKinhDi.setBackgroundColor(getResources().getColor(R.color.white_black, null));
                return true; // Return true to indicate that the long click event is consumed
            }
        });

        btnKinhDi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Reset the background color to its original state when long click is released
                    btnKinhDi.setBackground(originalBackgroundDrawable);
                    dialog.dismiss();
                    scrollToItemId(tiltleKinhDi);
                }
                return false;
            }
        });

        btnTinhCam.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Change the background color of btnAnime when long-pressed
                btnTinhCam.setBackgroundColor(getResources().getColor(R.color.white_black, null));
                return true; // Return true to indicate that the long click event is consumed
            }
        });

        btnTinhCam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Reset the background color to its original state when long click is released
                    btnTinhCam.setBackground(originalBackgroundDrawable);
                    dialog.dismiss();
                    scrollToItemId(tiltleTinhCam);
                }
                return false;
            }
        });



        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int y, int oldScrollX, int oldScrollY) {
                if (scrollY - y > threshold) {
                    // Scrolling up
                    ViewCompat.animate(appBarLayout).translationY(0).setDuration(200).start();
                } else if (y - scrollY > threshold) {
                    // Scrolling down
                    ViewCompat.animate(appBarLayout).translationY(-appBarLayout.getHeight()).setDuration(200).start();
                }
                scrollY = y;
            }
        });
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
        ft.replace(R.id.frameFragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onItemClick(int position) {

    }

    private RecyclerItemTouchListener createOnItemTouchListenerEvent(RecyclerView recyclerView, CustomAdapterRecyFilm customAdapterRecyFilm){
        RecyclerItemTouchListener itemTouchListener = new RecyclerItemTouchListener(getActivity(), recyclerView, new RecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FragmentManager fm = getParentFragmentManager();
                fm.setFragmentResultListener("emailMainToFHome", getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        email = result.getString("email");
                    }
                });
                Movie movie = customAdapterRecyFilm.GetItem(position);
                Log.d("custom", "addRecycleViewByGenres: "+customAdapterRecyFilm.getItemCount());

                Bundle results = new Bundle();
                results.putInt("idMV", movie.getIdMV());
                results.putString("email", email);
                results.putInt("idGenreMV", movie.getIdGenre());
                results.putInt("idStyleMV", movie.getIdType());
                getParentFragmentManager().setFragmentResult("keyDetailMV", results);
                loadFragment(new FragmentDetailMovie());
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        return itemTouchListener;
    }

    private void scrollToItemId(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        // Calculate the y-coordinate of the "title action" view relative to the NestedScrollView
        int titleY = location[1] - nestedScrollView.getTop() - 250;
        // Smooth scroll to the "title action" view
        nestedScrollView.smoothScrollTo(0, titleY, 1000);
    }

    public void updateAppName(String newAppName) {
        // Get the application info to access the label attribute
        try {
            // Update the title in the ActionBar/Toolbar
            requireActivity().setTitle(newAppName);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshRecyclerView(){
        recyclerViewPhimAnime.removeItemDecoration(ItemDecoAnime);
        recyclerViewPhimHanhDong.removeItemDecoration(ItemDecoHanhDong);
        recyclerViewPhimHaiHuoc.removeItemDecoration(ItemDecoHaiHuoc);
        recyclerViewPhimTinhCam.removeItemDecoration(ItemDecoTinhCam);
        recyclerViewPhimKinhDi.removeItemDecoration(ItemDecoKinhDi);
        recyclerViewPhimAnime.removeOnItemTouchListener(touchListenerAnime);
        recyclerViewPhimHanhDong.removeOnItemTouchListener(touchListenerHanhDong);
        recyclerViewPhimHaiHuoc.removeOnItemTouchListener(touchListenerHaiHuoc);
        recyclerViewPhimTinhCam.removeOnItemTouchListener(touchListenerTinhCam);
        recyclerViewPhimKinhDi.removeOnItemTouchListener(touchListenerKinhDi);

    }


}