package android.genzinema.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.Custom_Adapter_RecyclerView_Collection;
import android.genzinema.Controller.FavoriteMovieHander;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Controller.Custom_RecyclerView_ItemTouchListener;
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

import android.util.Log;
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
public class Fragment_Collections extends Fragment implements Custom_Adapter_RecyclerView_Collection.OnItemClickListener {

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
    Custom_Adapter_RecyclerView_Collection adapterRecyColFilm;
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

        movieHandler.loadData();
        favoriteMovieHander.loadData();

        arrayList = favoriteMovieHander.getArrayListFMV(email);
        colFilmArrayList = movieHandler.getMoviesById(arrayList);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapterRecyColFilm = new Custom_Adapter_RecyclerView_Collection(colFilmArrayList);
        recyclerView.setAdapter(adapterRecyColFilm);

        recyclerView.addOnItemTouchListener(createOnItemTouchListenerEvent(recyclerView, adapterRecyColFilm));
        return rootView;
    }
    private Custom_RecyclerView_ItemTouchListener createOnItemTouchListenerEvent(RecyclerView recyclerView, Custom_Adapter_RecyclerView_Collection customAdapterRecyFilm){
        Custom_RecyclerView_ItemTouchListener itemTouchListener = new Custom_RecyclerView_ItemTouchListener(getActivity(), recyclerView, new Custom_RecyclerView_ItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = adapterRecyColFilm.GetItem(position);
                /*Log.d("custom", ""+ movie.getIdMV() +"-"+ email);
                Intent intent = new Intent(getContext(), Activity_Detail_Movie.class);
                intent.putExtra("idMV", movie.getIdMV());
                intent.putExtra("email", email);
                intent.putExtra("idGenreMV", movie.getIdGenre());
                intent.putExtra("idStyleMV", movie.getIdType());
                startActivity(intent);*/
                Bundle bundle = new Bundle();
                bundle.putInt("idMV", movie.getIdMV());
                bundle.putString("email",email);
                bundle.putInt("idGenreMV", movie.getIdGenre());
                bundle.putInt("idStyleMV", movie.getIdType());
                getParentFragmentManager().setFragmentResult("keyDetailMV", bundle);
                loadFragment(new Fragment_Detail_Movie());
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