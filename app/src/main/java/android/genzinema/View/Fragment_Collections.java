package android.genzinema.View;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.CustomAdapterRecyCollectionFilm;
import android.genzinema.Controller.FavoriteMovieHander;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Controller.RecyclerItemTouchListener;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Collections#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Collections extends Fragment implements CustomAdapterRecyCollectionFilm.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    FavoriteMovieHander favoriteMovieHander;
    MovieHandler movieHandler;
    String email;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    ImageView imgColFilm;
    TextView nameColFilm;
    ArrayList<Movie> colFilmArrayList = new ArrayList<>();
    CustomAdapterRecyCollectionFilm adapterRecyColFilm;
    ArrayList<Integer> arrayList = new ArrayList<>();

    private String mParam1;
    private String mParam2;

    public Fragment_Collections() {
        // Required empty public constructor
    }
    public Fragment_Collections(String email) {
        // Required empty public constructor
        this.email = email;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Collections.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Collections newInstance(String param1, String param2) {
        Fragment_Collections fragment = new Fragment_Collections();
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
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.setFragmentResultListener("emailMainToFavorite", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

            }
        });
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment__collections, container, false);
        recyclerView = rootView.findViewById(R.id.recycleViewCollection);
        imgColFilm = rootView.findViewById(R.id.imgColFilm);
        nameColFilm = rootView.findViewById(R.id.nameColFilm);

        // Inflate the layout for this fragment
        movieHandler = new MovieHandler(getContext(),MovieHandler.DB_NAME,null,1);
        favoriteMovieHander = new FavoriteMovieHander(getContext(),FavoriteMovieHander.DB_NAME,null,1);
        favoriteMovieHander.loadData();
        movieHandler.loadData();
        arrayList = favoriteMovieHander.getArrayListFMV(email);
        colFilmArrayList = movieHandler.getMoviesById(arrayList);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterRecyColFilm = new CustomAdapterRecyCollectionFilm(colFilmArrayList);
        recyclerView.setAdapter(adapterRecyColFilm);
        return rootView;
    }
    private RecyclerItemTouchListener createOnItemTouchListenerEvent(RecyclerView recyclerView, CustomAdapterRecyCollectionFilm customAdapterRecyFilm){
        RecyclerItemTouchListener itemTouchListener = new RecyclerItemTouchListener(getActivity(), recyclerView, new RecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = adapterRecyColFilm.GetItem(position);
                Bundle results = new Bundle();
                results.putInt("idMV", movie.getIdMV());
                results.putString("email", email);
                results.putInt("idGenreMV", movie.getIdGenre());
                results.putInt("idStyleMV", movie.getIdType());
                getParentFragmentManager().setFragmentResult("keyDetailMVsc", results);
                loadFragment(new FragmentDetailMovie());
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        return itemTouchListener;
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