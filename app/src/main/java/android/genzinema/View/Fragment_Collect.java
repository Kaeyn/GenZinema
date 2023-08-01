package android.genzinema.View;

import android.genzinema.Controller.Custom_Adapter_GridView_Collection;
import android.genzinema.Controller.EpHandler;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Collect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Collect extends Fragment {
    GridView gridCollect;
    ArrayList<Movie> arrayListMovie = new ArrayList<>();
    Custom_Adapter_GridView_Collection adapter;
    MovieHandler movieHandler;
    String email = "";
    private int idGenre = 0;
    private int idStyle = 0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Collect() {
        // Required empty public constructor
    }

    public Fragment_Collect(String email, int idGenre, int idStyle){
        this.email = email;
        this.idGenre = idGenre;
        this.idStyle = idStyle;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Collect.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Collect newInstance(String param1, String param2) {
        Fragment_Collect fragment = new Fragment_Collect();
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
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        addControls(view);
        movieHandler = new MovieHandler(getContext(), EpHandler.DB_NAME, null, 1);
        arrayListMovie = movieHandler.GetCollectMVBy(idGenre, idStyle);
        adapter = new Custom_Adapter_GridView_Collection(getContext(), arrayListMovie);
        gridCollect.setAdapter(adapter);
        addEvents();
        return view;
    }
    void addControls(View view){
        gridCollect = view.findViewById(R.id.gridCollect);
    }
    void addEvents(){
        gridCollect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = adapter.GetItem(position);
                Bundle results = new Bundle();
                results.putInt("idMV", movie.getIdMV());
                results.putString("email", email);
                results.putInt("idGenreMV", movie.getIdGenre());
                results.putInt("idStyleMV", movie.getIdType());
                getParentFragmentManager().setFragmentResult("keyDetailMV", results);
            }
        });
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutDMP, fragment);
        ft.commit();
    }

}