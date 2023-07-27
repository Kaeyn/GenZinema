package android.genzinema.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.CustomAdapterRecyFilm;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Controller.RecyclerItemTouchListener;
import android.genzinema.Model.Movie;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.AdapterView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    View tiltleKinhDi, tiltleAnime, tiltleHanhDong;

    MovieHandler movieHandler;;
    SQLiteDatabase db;
    ArrayList<String> type_of_filmArrayList = new ArrayList<>();

    RecyclerView recyclerViewPhimThinhHanh, recyclerViewPhimAnime, recyclerViewPhimHanhDong, recyclerViewPhimKinhDi;
    ImageView imgFilm;

    FrameLayout frameLayout;
    // array list phim thinh hanh
    ArrayList<Movie> arrayListPhimThinhHanh = new ArrayList<>();

    // array list phim anime
    ArrayList<Movie> arrayListPhimAnime = new ArrayList<>();

    // array list phim hanh dong
    ArrayList<Movie> arrayListPhimHanhDong = new ArrayList<>();

    // array list phim kinh di
    ArrayList<Movie> arrayListPhimKinhDi = new ArrayList<>();

    CustomAdapterRecyFilm adapterRecyFilm;
    CustomAdapterRecyFilm adapterRecyFilmKinhDi;
    CustomAdapterRecyFilm adapterRecyFilmAnime;
    CustomAdapterRecyFilm adapterRecyFilmHanhDong;


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
        type_of_filmArrayList.add("Animme");
        type_of_filmArrayList.add("Hành động");
        type_of_filmArrayList.add("Kinh dị");


        dialog = new Dialog(getActivity(), R.style.CustomDialog);
        dialog.setContentView(view);

        originalBackgroundDrawable = (GradientDrawable) btnAnime.getBackground();

        loadArrayListData();
        addEvents();
        addRecycleViewByGenres();
        addRecycleViewEvents();

        return rootView;
    }

    private void addRecycleViewEvents() {
        recyclerViewPhimThinhHanh.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        recyclerViewPhimThinhHanh.addOnItemTouchListener(createOnItemTouchListenerEvent(recyclerViewPhimThinhHanh, adapterRecyFilm));

        recyclerViewPhimAnime.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        recyclerViewPhimAnime.addOnItemTouchListener(createOnItemTouchListenerEvent(recyclerViewPhimAnime, adapterRecyFilmAnime));

        recyclerViewPhimHanhDong.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        recyclerViewPhimHanhDong.addOnItemTouchListener(createOnItemTouchListenerEvent(recyclerViewPhimHanhDong, adapterRecyFilmHanhDong));

        recyclerViewPhimKinhDi.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        recyclerViewPhimKinhDi.addOnItemTouchListener(createOnItemTouchListenerEvent(recyclerViewPhimKinhDi, adapterRecyFilmKinhDi));
    }

    private void addRecycleViewByGenres() {
        // Display list film of "Hien dang thinh hanh"
        recyclerViewPhimThinhHanh.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimThinhHanh.setLayoutManager(layoutManager);
        recyclerViewPhimThinhHanh.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilm = new CustomAdapterRecyFilm(arrayListPhimThinhHanh);
        recyclerViewPhimThinhHanh.setAdapter(adapterRecyFilm);




        // Display list film of "Anime"
        recyclerViewPhimAnime.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManagerAnime;
        layoutManagerAnime = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimAnime.setLayoutManager(layoutManagerAnime);
        recyclerViewPhimAnime.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmAnime = new CustomAdapterRecyFilm(arrayListPhimAnime);
        recyclerViewPhimAnime.setAdapter(adapterRecyFilmAnime);


        // Display list film of "Hanh dong"
        recyclerViewPhimHanhDong.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManagerHanhDong;
        layoutManagerHanhDong = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimHanhDong.setLayoutManager(layoutManagerHanhDong);
        recyclerViewPhimHanhDong.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmHanhDong = new CustomAdapterRecyFilm(arrayListPhimHanhDong);
        recyclerViewPhimHanhDong.setAdapter(adapterRecyFilmHanhDong);


        // Display list film of "Kinh di"
        recyclerViewPhimKinhDi.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManagerKinhDi;
        layoutManagerKinhDi = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimKinhDi.setLayoutManager(layoutManagerKinhDi);
        recyclerViewPhimKinhDi.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmKinhDi = new CustomAdapterRecyFilm(arrayListPhimKinhDi);
        recyclerViewPhimKinhDi.setAdapter(adapterRecyFilmKinhDi);
        adapterRecyFilmKinhDi.setOnItemClickListener(this);
    }

    private void loadArrayListData() {

        arrayListPhimThinhHanh = movieHandler.getMoviesByGenre(1);

        // init data for "phim anime"
        arrayListPhimAnime = movieHandler.getMoviesByGenre(2);

        // init data for "phim hanh dong"
        arrayListPhimHanhDong = movieHandler.getMoviesByGenre(3);

        // init data for "phim kinh di"
        arrayListPhimKinhDi = movieHandler.getMoviesByGenre(4);
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
        recyclerViewPhimThinhHanh = rootView.findViewById(R.id.recyViewPhimThinhHanh);
        recyclerViewPhimAnime = rootView.findViewById(R.id.recyViewPhimAnime);
        recyclerViewPhimHanhDong = rootView.findViewById(R.id.recyViewPhimHanhDong);
        recyclerViewPhimKinhDi = rootView.findViewById(R.id.recyViewPhimKinhDi);
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
    }

    private void addEvents(){

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
                arrayListPhimKinhDi = movieHandler.getMoviesByMovie(2,1);
                arrayListPhimAnime = movieHandler.getMoviesByMovie(5,1);
                arrayListPhimHanhDong = movieHandler.getMoviesByMovie(1,1);
                addRecycleViewByGenres();
            }
        });

        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Update the button's background color

                // Get the background drawable of the button
                btnMovie.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.button_background_clicked));

//                arrayListPhimThinhHanh = movieHandler.getMoviesByMovie(1,2);
                arrayListPhimKinhDi = movieHandler.getMoviesByMovie(2,2);
                arrayListPhimAnime = movieHandler.getMoviesByMovie(5,2);
                arrayListPhimHanhDong = movieHandler.getMoviesByMovie(1,2);
                addRecycleViewByGenres();

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
                }
                return false;
            }
        });







        // Apply the adapter to the spinner

        // init data for "phim thinh hanh"
        arrayListPhimThinhHanh = movieHandler.getMoviesByGenre(1);

        // init data for "phim kinh di"
        arrayListPhimKinhDi = movieHandler.getMoviesByGenre(2);

        // init data for "phim hanh dong"
        arrayListPhimHanhDong = movieHandler.getMoviesByGenre(3);


        // init data for "phim anime"
        arrayListPhimAnime = movieHandler.getMoviesByGenre(2);

        // Display list film of "Hien dang thinh hanh"
        recyclerViewPhimThinhHanh.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimThinhHanh.setLayoutManager(layoutManager);
        recyclerViewPhimThinhHanh.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilm = new CustomAdapterRecyFilm(arrayListPhimThinhHanh);
        recyclerViewPhimThinhHanh.setAdapter(adapterRecyFilm);



        // Display list film of "Anime"
        recyclerViewPhimAnime.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManagerAnime;
        layoutManagerAnime = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimAnime.setLayoutManager(layoutManagerAnime);
        recyclerViewPhimAnime.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmAnime = new CustomAdapterRecyFilm(arrayListPhimAnime);
        recyclerViewPhimAnime.setAdapter(adapterRecyFilmAnime);


        // Display list film of "Hanh dong"
        recyclerViewPhimHanhDong.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManagerHanhDong;
        layoutManagerHanhDong = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimHanhDong.setLayoutManager(layoutManagerHanhDong);
        recyclerViewPhimHanhDong.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmHanhDong = new CustomAdapterRecyFilm(arrayListPhimHanhDong);
        recyclerViewPhimHanhDong.setAdapter(adapterRecyFilmHanhDong);

        // Display list film of "Kinh di"
        recyclerViewPhimKinhDi.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManagerKinhDi;
        layoutManagerKinhDi = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPhimKinhDi.setLayoutManager(layoutManagerKinhDi);
        recyclerViewPhimKinhDi.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilmKinhDi = new CustomAdapterRecyFilm(arrayListPhimKinhDi);
        recyclerViewPhimKinhDi.setAdapter(adapterRecyFilmKinhDi);
        adapterRecyFilmKinhDi.setOnItemClickListener(this);



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

    private boolean hasBackgroundTint(Button button) {
        // Get the background drawable of the button
        Drawable backgroundDrawable = button.getBackground();

        // Check if the background drawable is an instance of ColorDrawable (solid color)
        return backgroundDrawable instanceof ColorDrawable;
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

}