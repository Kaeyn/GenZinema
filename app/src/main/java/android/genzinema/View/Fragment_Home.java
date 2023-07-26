package android.genzinema.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.Cus_Item_Search_Adapter;
import android.genzinema.Controller.CustomAdapterRecyFilm;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment implements CustomAdapterRecyFilm.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnMovie;

    Spinner spinner_type_film;

    MovieHandler movieHandler;;
    SQLiteDatabase db;
    ArrayList<String> type_of_filmArrayList = new ArrayList<>();

    String[] lsNameType = new String[]{"Châu Á","Anime", "Hành động", "Viễn tưởng"};

    // Data of Phim thinh hanh
    int[] lstIdPhimThinhHanh = new int[]{1,2,3,4,5};
    int[] lstImgPhimThinhHanh = new int[]{R.drawable.overlord, R.drawable.sieunhan, R.drawable.yinan, R.drawable.doraemon, R.drawable.johnweak};


    // Data of Phim thinh hanh
    int[] lstIdPhimAnime = new int[]{1,2,3,4,5};
    int[] lstImgPhimAnime = new int[]{R.drawable.sieunhan, R.drawable.yinan, R.drawable.overlord, R.drawable.doraemon, R.drawable.johnweak};


    // Data of Phim hanh dong
    int[] lstIdPhimHanhDong = new int[]{1,2,3,4,5};
    int[] lstImgPhimHanhDong = new int[]{R.drawable.yinan, R.drawable.johnweak, R.drawable.yinan, R.drawable.doraemon, R.drawable.overlord};

    // Data of Phim kinh di
    int[] lstIdPhimKinhDi = new int[]{1,2,3,4,5};
    int[] lstImgPhimKinhDi = new int[]{R.drawable.johnweak, R.drawable.yinan, R.drawable.sieunhan, R.drawable.doraemon, R.drawable.overlord};

    RecyclerView recyclerViewPhimThinhHanh, recyclerViewPhimAnime, recyclerViewPhimHanhDong, recyclerViewPhimKinhDi;
    ImageView imgFilm;

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

    int selectedPosition = 0;

    TextView titleHanhDong;
    NestedScrollView nestedScrollView;

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



        btnMovie = rootView.findViewById(R.id.btnMovie);
        recyclerViewPhimThinhHanh = rootView.findViewById(R.id.recyViewPhimThinhHanh);
        recyclerViewPhimAnime = rootView.findViewById(R.id.recyViewPhimAnime);
        recyclerViewPhimHanhDong = rootView.findViewById(R.id.recyViewPhimHanhDong);
        recyclerViewPhimKinhDi = rootView.findViewById(R.id.recyViewPhimKinhDi);
        imgFilm = rootView.findViewById(R.id.imgHomeFilm);
        nestedScrollView = rootView.findViewById(R.id.nestedScrollHome);
        titleHanhDong = rootView.findViewById(R.id.actionTitle);

        // Initialize your ArrayList and populate it with data
        type_of_filmArrayList = new ArrayList<>();
        // Add your data to the ArrayList
        type_of_filmArrayList.add("Thể loại");
        type_of_filmArrayList.add("Animme");
        type_of_filmArrayList.add("Hành động");
        type_of_filmArrayList.add("Kinh dị");
        // ... Add more items as needed

        // Find the Spinner in your fragment's layout
        Spinner spinner = rootView.findViewById(R.id.spinner_type_film);

        // Create the ArrayAdapter using your type_of_filmArrayList and a default spinner layout
        ArrayAdapter<String> adapterTypeFilmSpinner = new ArrayAdapter<>(getActivity(), R.layout.custom_spinner_item, type_of_filmArrayList);

        // Specify the layout to use when the list of choices appears
        adapterTypeFilmSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapterTypeFilmSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                if(position == 3){
                    int[] location = new int[2];
                    titleHanhDong.getLocationInWindow(location);
                    // Calculate the y-coordinate of the "title action" view relative to the NestedScrollView
                    int titleY = location[1] - nestedScrollView.getTop() - 250;
                    // Smooth scroll to the "title action" view
                    nestedScrollView.smoothScrollTo(0, titleY, 1000);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // init data for "phim thinh hanh"
        arrayListPhimThinhHanh = movieHandler.getMoviesByGenre(1);

        // init data for "phim anime"
        arrayListPhimAnime = movieHandler.getMoviesByGenre(2);

        // init data for "phim hanh dong"
        arrayListPhimHanhDong = movieHandler.getMoviesByGenre(3);

        // init data for "phim kinh di"
        arrayListPhimKinhDi = movieHandler.getMoviesByGenre(4);

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
        adapterRecyFilm.setOnItemClickListener(this);
        recyclerViewPhimThinhHanh.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(),e.getY());
                if(view != null){
                    int position = rv.getChildAdapterPosition(view);
                    Movie movie = adapterRecyFilm.GetItem(position);
                    Intent intent = new Intent(getContext(), DetailMoviePage.class);
                    intent.putExtra("idMV",movie.getIdMV());
                    intent.putExtra("idGenreMV",movie.getIdGenre());
                    intent.putExtra("idStyleMV",movie.getIdType());
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        recyclerViewPhimAnime.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(),e.getY());
                if(view != null){
                    int position = rv.getChildAdapterPosition(view);
                    Movie movie = adapterRecyFilmAnime.GetItem(position);
                    Intent intent = new Intent(getContext(), DetailMoviePage.class);
                    intent.putExtra("idMV",movie.getIdMV());
                    intent.putExtra("idGenreMV",movie.getIdGenre());
                    intent.putExtra("idStyleMV",movie.getIdType());
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        recyclerViewPhimHanhDong.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(),e.getY());
                if(view != null){
                    int position = rv.getChildAdapterPosition(view);
                    Movie movie = adapterRecyFilmHanhDong.GetItem(position);
                    Intent intent = new Intent(getContext(), DetailMoviePage.class);
                    intent.putExtra("idMV",movie.getIdMV());
                    intent.putExtra("idGenreMV",movie.getIdGenre());
                    intent.putExtra("idStyleMV",movie.getIdType());
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        recyclerViewPhimKinhDi.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(),e.getY());
                if(view != null){
                    int position = rv.getChildAdapterPosition(view);
                    Movie movie = adapterRecyFilmKinhDi.GetItem(position);
                    Intent intent = new Intent(getContext(), DetailMoviePage.class);
                    intent.putExtra("idMV",movie.getIdMV());
                    intent.putExtra("idGenreMV",movie.getIdGenre());
                    intent.putExtra("idStyleMV",movie.getIdType());
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return rootView;


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
}