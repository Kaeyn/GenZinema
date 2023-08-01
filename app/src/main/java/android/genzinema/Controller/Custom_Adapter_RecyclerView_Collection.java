package android.genzinema.Controller;

import android.genzinema.Model.Movie;
import android.genzinema.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Custom_Adapter_RecyclerView_Collection extends RecyclerView.Adapter<Custom_Adapter_RecyclerView_Collection.MyViewHolder>{

    ArrayList<Movie> movieArrayList = new ArrayList<>();

    public Custom_Adapter_RecyclerView_Collection(ArrayList<Movie> filmArrayList) {
        this.movieArrayList = filmArrayList;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Custom_Adapter_RecyclerView_Search.OnItemClickListener clickListener;

    public void setOnItemClickListener(Custom_Adapter_RecyclerView_Search.OnItemClickListener listener) {
        this.clickListener = listener;
    }
    public Movie GetItem(int position){
        return movieArrayList.get(position);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collections_recycle_view_custom, parent, false);
        return new Custom_Adapter_RecyclerView_Collection.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.imgColFilm.setImageResource(movie.getIdThumbnails());
        holder.nameColFilm.setText(movie.getNameMovie());
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgColFilm;
        TextView nameColFilm;
        Button playColFilm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgColFilm = itemView.findViewById(R.id.imgColFilm);
            nameColFilm = itemView.findViewById(R.id.nameColFilm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
