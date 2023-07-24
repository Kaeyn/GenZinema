package android.genzinema.Controller;

import android.genzinema.Model.Movie;
import android.genzinema.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterRecyFilm extends RecyclerView.Adapter<CustomAdapterRecyFilm.MyViewHolder>{
    ArrayList<Movie> movieArrayList = new ArrayList<>();

    public CustomAdapterRecyFilm(ArrayList<Movie> filmArrayList) {
        this.movieArrayList = filmArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_recycle_view_custom, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.imgFilm.setImageResource(movie.getIdThumbnails());
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFilm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFilm = itemView.findViewById(R.id.imgHomeFilm);
        }
    }
}
