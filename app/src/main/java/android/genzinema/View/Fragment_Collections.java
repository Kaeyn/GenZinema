package android.genzinema.View;

import android.annotation.SuppressLint;
import android.genzinema.Controller.CustomAdapterRecyCollectionFilm;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
public class Fragment_Collections extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    int[] lstId = new int[]{1,2,3,4,5};
    int[] lstImg = new int[]{R.drawable.johnweak, R.drawable.johnweak, R.drawable.johnweak, R.drawable.johnweak, R.drawable.johnweak};
    String[] lstName = new String[]{"R.drawable.johnweak","R.drawable.johnweak","R.drawable.johnweak", "R.drawable.johnweak", "R.drawable.johnweak"};

    RecyclerView recyclerView;
    ImageView imgColFilm;
    TextView nameColFilm;
    ArrayList<Movie> colFilmArrayList = new ArrayList<>();
    CustomAdapterRecyCollectionFilm adapterRecyColFilm;
    private String mParam1;
    private String mParam2;

    public Fragment_Collections() {
        // Required empty public constructor
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
        colFilmArrayList = Movie.initDataCollection(lstId, lstImg, lstName);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterRecyColFilm = new CustomAdapterRecyCollectionFilm(colFilmArrayList);
        recyclerView.setAdapter(adapterRecyColFilm);
        return rootView;
    }
}