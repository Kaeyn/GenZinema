package android.genzinema.View;

import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.Custom_Adapter_RecyclerView_HotNew;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Controller.Custom_RecyclerView_ItemTouchListener;
import android.genzinema.Controller.UserHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Fragment_HotnNew_RecycleView extends Fragment implements Custom_Adapter_RecyclerView_HotNew.OnItemClickListener {
    private String subcategoryName;
    MovieHandler movieHandler;
    SQLiteDatabase database;
    Custom_Adapter_RecyclerView_HotNew adapter;

    String email;

    public Fragment_HotnNew_RecycleView(String subcategoryName, String email){
        this.subcategoryName = subcategoryName;
        this.email = email;
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_HotnNew_RecycleView() {
        // Required empty public constructor
    }
    public static Fragment_HotnNew_RecycleView newInstance(String subcategoryName, String email) {
        Fragment_HotnNew_RecycleView fragment = new Fragment_HotnNew_RecycleView(subcategoryName, email);
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
        View view = inflater.inflate(R.layout.fragment_hotn_new__recycle_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleViewHotnNew);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movieHandler = new MovieHandler(getContext(), UserHandler.DB_NAME,null,1);
        movieHandler.onCreate(database);

        ArrayList<Movie> dataList = getSubcategoryData(subcategoryName);

        adapter = new Custom_Adapter_RecyclerView_HotNew(dataList);
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false); // Disable nested scrolling if needed
        recyclerView.addOnItemTouchListener(createOnItemTouchListenerEvent(recyclerView));

        return view;
    }

    private ArrayList<Movie> getSubcategoryData(String subcategoryName) {
        ArrayList<Movie> arrayList = new ArrayList<>();

        if(subcategoryName.equals("Top10")){
            arrayList.clear();
            arrayList = movieHandler.GetTop10Movie();
            return arrayList;
        } else if (subcategoryName.equals("Newest")) {
            arrayList.clear();
            arrayList = movieHandler.GetNewestMovie();
            return arrayList;
        } else {
            arrayList.clear();
            arrayList = movieHandler.GetGoodMovie();
            return arrayList;
        }
    }

    private Custom_RecyclerView_ItemTouchListener createOnItemTouchListenerEvent(RecyclerView recyclerView){
        Custom_RecyclerView_ItemTouchListener itemTouchListener = new Custom_RecyclerView_ItemTouchListener(getActivity(), recyclerView,
                new Custom_RecyclerView_ItemTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Movie movie = adapter.GetItem(position);
                        Bundle results = new Bundle();
                        results.putInt("idMV", movie.getIdMV());
                        results.putInt("idGenreMV", movie.getIdGenre());
                        results.putInt("idStyleMV", movie.getIdType());
                        results.putString("email", email);
                        getParentFragmentManager().setFragmentResult("keyDetailMV", results);
                        loadFragment(new Fragment_Detail_Movie());
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                    }
                });
        return itemTouchListener;
    }
    @Override
    public void onItemClick(int position) {

    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
