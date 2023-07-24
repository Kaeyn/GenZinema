package android.genzinema.Controller;

import android.genzinema.Model.Movie;
import android.genzinema.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cus_Item_Search_Adapter extends RecyclerView.Adapter<Cus_Item_Search_Adapter.MyViewHolder> {

    ArrayList<Movie> arrayListMovie = new ArrayList<>();

    @NonNull
    @Override
    public Cus_Item_Search_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_item_search, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Cus_Item_Search_Adapter.MyViewHolder holder, int position) {
        Movie movie = arrayListMovie.get(position);
        holder.textView.setText(movie.getNameMovie());
        holder.imageButton.setImageResource(R.drawable.play_icon);
    }

    @Override
    public int getItemCount() {
        return arrayListMovie.size();
    }

    public Cus_Item_Search_Adapter(ArrayList<Movie> arrayListMovie){
        this.arrayListMovie = arrayListMovie;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ImageButton imageButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageItem_Search);
            textView = itemView.findViewById(R.id.textItem_Search);
            imageButton = itemView.findViewById(R.id.btnToMovie_Search);
        }
    }
}
