package android.genzinema.View;

import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailMovie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailMovie extends Fragment {
    ImageView imgDM;
    TextView tvTenMV,tvNamMV,tvDetailMV,tvActorMV,tvAuthorMV;
    ProgressBar pb;
    Button btnEp,btnSimilar;
    MovieHandler movieHandler;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailMovie() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailMovie.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailMovie newInstance(String param1, String param2) {
        DetailMovie fragment = new DetailMovie();
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
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResultListener("keyMain", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int idMV = result.getInt("idMV");
                int idGenre = result.getInt("idGenreMV");
                int idStyle = result.getInt("idStyleMV");
                Toast.makeText(getContext(),"idMV "+idMV,Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"idGenreMV "+idGenre,Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"idStyleMV "+idStyle,Toast.LENGTH_SHORT).show();

                movieHandler = new MovieHandler(getContext(),MovieHandler.DB_NAME,null,1);
                Movie movie = movieHandler.GetMovieByID(idMV);
                tvTenMV.setText(movie.getNameMovie());
                tvNamMV.setText(movie.getYearProduce());
                tvActorMV.setText("Diễn viên: "+movie.getActors());
                tvAuthorMV.setText("Đạo diễn: "+movie.getAuthors());
                tvDetailMV.setText(movie.getDetail());
                imgDM.setImageResource(movie.getIdThumbnails());

                Bundle results = new Bundle();
                results.putInt("idMV", idMV);
                results.putInt("idGenreMV", idGenre);
                results.putInt("idStyleMV", idStyle);

                getParentFragmentManager().setFragmentResult("keyEpsMV", results);

                if(idStyle==1){
                    btnEp.setText("Bộ sưu tập");
                    btnEp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            loadFragment(new FragmentCollect());
                        }

                    });
                } else {
                    btnEp.setText("Các tập");

                    btnEp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            loadFragment(new FragmentEps());
                        }

                    });
                }

                btnSimilar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadFragment(new FragmentSimilarStyle());
                    }
                });
            }
        });

    }

    private void addControl(View view){
        pb = view.findViewById(R.id.pbDetailMV);
        btnEp = view.findViewById(R.id.btnEps);
        btnSimilar = view.findViewById(R.id.btnSimilarStyle);
        imgDM = view.findViewById(R.id.imgDM);
        tvActorMV = view.findViewById(R.id.tvActorMV);
        tvDetailMV = view.findViewById(R.id.tvDetailMV);
        tvAuthorMV = view.findViewById(R.id.tvAuthorMV);
        tvNamMV = view.findViewById(R.id.tvNamMV);
        tvTenMV = view.findViewById(R.id.tvTenMV);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        addControl(view);
        pb.setProgress(20);


        return view;
    }
    public void loadFragment(Fragment fragment){
//        Toast.makeText(getContext(),"loadFragment",Toast.LENGTH_SHORT).show();
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}