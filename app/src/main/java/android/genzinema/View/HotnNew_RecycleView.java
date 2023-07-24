package android.genzinema.View;

import android.genzinema.Controller.Hot_New_Pager_Adapter;
import android.genzinema.Model.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomRecycleView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotnNew_RecycleView extends Fragment {
    private String subcategoryName;

    public HotnNew_RecycleView(String subcategoryName){
        this.subcategoryName = subcategoryName;
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HotnNew_RecycleView() {
        // Required empty public constructor
    }


    public static HotnNew_RecycleView newInstance(String subcategoryName) {
        HotnNew_RecycleView fragment = new HotnNew_RecycleView(subcategoryName);
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
        ArrayList<Movie> dataList = getSubcategoryData(subcategoryName);

        CustomRecycleView adapter = new CustomRecycleView(dataList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private ArrayList<Movie> getSubcategoryData(String subcategoryName) {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Movie movie = new Movie(1, 1, 1, R.drawable.johnwick,"url", "Mua He Hoa Phuong No", "SonTungMTP", "LeHuuMyn", "2018", "Phim aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        arrayList.add(movie);
        return arrayList;
    }
    private class CustomRecycleView extends RecyclerView.Adapter<CustomRecycleView.YourViewHolder> {

        private List<Movie> dataList;

        public CustomRecycleView(List<Movie> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public YourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom_item_hotnnew, parent, false);
            return new YourViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull YourViewHolder holder, int position) {
            Movie movie = dataList.get(position);
            // Bind data to your views in the holder (e.g., set text, images, etc.)
            // Example:
            holder.imageView.setImageResource(movie.getIdThumbnails());
            holder.textViewTitle.setText(movie.getNameMovie());
            holder.textViewDescription.setText(movie.getDetail());
            // Set other views as needed based on YourDataModel properties
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        // Define YourViewHolder class to hold references to views in your item layout
        private class YourViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textViewTitle;
            TextView textViewDescription;

            public YourViewHolder(View itemView) {
                super(itemView);
                // Initialize your views here (e.g., find views by id)
                imageView = itemView.findViewById(R.id.imgItemHotnNew);
                textViewTitle = itemView.findViewById(R.id.txtTitleHotnNew);
                textViewDescription = itemView.findViewById(R.id.txtDescriptionHotnNew);
                // Initialize other views based on your item layout
            }
        }
    }
}
