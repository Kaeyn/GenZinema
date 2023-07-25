package android.genzinema.View;

import android.annotation.SuppressLint;
import android.genzinema.Controller.CustomAdapterRecyFilm;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnMovie;

    Spinner spinner_type_film;
    ArrayList<String> type_of_filmArrayList = new ArrayList<>();

    String[] lsNameType = new String[]{"Châu Á","Anime", "Hành động", "Viễn tưởng"};

    int[] lstId = new int[]{1,2,3,4,5};
    int[] lstImg = new int[]{R.drawable.overlord, R.drawable.sieunhan, R.drawable.yinan, R.drawable.doraemon, R.drawable.johnweak};

    RecyclerView recyclerView;
    ImageView imgFilm;
    ArrayList<Movie> filmArrayList = new ArrayList<>();
    CustomAdapterRecyFilm adapterRecyFilm;


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
        btnMovie = rootView.findViewById(R.id.btnMovie);
        recyclerView = rootView.findViewById(R.id.recyViewFilm);
        imgFilm = rootView.findViewById(R.id.imgHomeFilm);

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
        filmArrayList = Movie.initData(lstId, lstImg);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilm = new CustomAdapterRecyFilm(filmArrayList);
        recyclerView.setAdapter(adapterRecyFilm);


        return rootView;


    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}