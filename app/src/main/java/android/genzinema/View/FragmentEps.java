package android.genzinema.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.CustomAdapterEp;
import android.genzinema.Controller.EpHandler;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Ep;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEps extends Fragment {
    TextView tvNameMV;
    ListView lvEp;
    SQLiteDatabase db;
    EpHandler epHandler;
    MovieHandler movieHandler;
    ArrayList<Ep> arrayListEp = new ArrayList<Ep>();
    ArrayList<Movie> arrayListMV = new ArrayList<Movie>();

    String UrlMovie;

    //    int movieID ;
    CustomAdapterEp adapter;
    private int idMV;

     String email;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEps() {
        // Required empty public constructor
    }

    public FragmentEps(int idMV, String email) {
        this.email = email;
        this.idMV = idMV;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrgamentEps.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEps newInstance(String param1, String param2) {
        FragmentEps fragment = new FragmentEps();
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
        View view = inflater.inflate(R.layout.fragment_frgament_eps, container, false);
        addControls(view);
        epHandler = new EpHandler(getContext(), EpHandler.DB_NAME, null, 1);
        epHandler.onCreate(db);

        epHandler = new EpHandler(getContext(), EpHandler.DB_NAME, null, 1);
        arrayListEp = epHandler.GetAllEpByMovieID(idMV);

        adapter = new CustomAdapterEp(getContext(), R.layout.layout_custom_item_ep_dm, arrayListEp);
        movieHandler = new MovieHandler(getContext(),MovieHandler.DB_NAME,null,1);
        Movie movie = movieHandler.GetMovieByID(idMV);

        tvNameMV.setText(movie.getNameMovie());
        lvEp.setAdapter(adapter);
        addEvents();
        return view;
    }
    void addControls(View view){
        tvNameMV = view.findViewById(R.id.tvTenPhim);
        lvEp = view.findViewById(R.id.lvEp);
    }
    void addEvents(){
        lvEp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ep ep = adapter.getItem(position);
                UrlMovie = ep.getUrlEp();
                Intent intent = new Intent(getContext(), WatchMovie.class);
                intent.putExtra("vidUrl", UrlMovie);
                intent.putExtra("email", email);
                intent.putExtra("idMV", idMV);
                Log.d("idMVEp", "onClick: "+idMV);
                Log.d("vidUrlafterclick", ""+UrlMovie);
                startActivity(intent);
            }
        });
    }

}
