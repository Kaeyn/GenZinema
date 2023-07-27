package android.genzinema.View;

import android.genzinema.Controller.CustomGridCollectMV;
import android.genzinema.Controller.EpHandler;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSimilarStyle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSimilarStyle extends Fragment {
    GridView gridSimilar;
    MovieHandler movieHandler;
    ArrayList<Movie> arrayListMovie = new ArrayList<>();
    CustomGridCollectMV adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSimilarStyle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSimilarStyle.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSimilarStyle newInstance(String param1, String param2) {
        FragmentSimilarStyle fragment = new FragmentSimilarStyle();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_similar_style, container, false);
        addControls(view);
        addEvents();
        return view;
    }
    void addControls(View view){
        gridSimilar = view.findViewById(R.id.gridSimilar);
    }
    void addEvents(){

        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResultListener("similarMV", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                movieHandler = new MovieHandler(getContext(), EpHandler.DB_NAME, null, 1);
                arrayListMovie = movieHandler.GetSimilarMVBy(result.getInt("idGenreMV"));
                adapter = new CustomGridCollectMV(getContext(), arrayListMovie);
//                Toast.makeText(getContext(),"array: "+arrayListMovie.size(),Toast.LENGTH_SHORT).show();
                gridSimilar.setAdapter(adapter);
            }
        });

        gridSimilar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie movie = adapter.GetItem(position);
                Bundle results = new Bundle();
                results.putInt("idMV", movie.getIdMV());
                results.putInt("idGenreMV", movie.getIdGenre());
                results.putInt("idStyleMV", movie.getIdType());
                getParentFragmentManager().setFragmentResult("keyDetailMV", results);
            }
        });    }
}