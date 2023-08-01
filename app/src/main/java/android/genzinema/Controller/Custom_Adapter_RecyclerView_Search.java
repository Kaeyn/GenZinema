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

public class Custom_Adapter_RecyclerView_Search extends RecyclerView.Adapter<Custom_Adapter_RecyclerView_Search.MyViewHolder> {

    ArrayList<Movie> arrayListMovie = new ArrayList<>();

    @NonNull
    @Override
    public Custom_Adapter_RecyclerView_Search.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_item_search, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Custom_Adapter_RecyclerView_Search.MyViewHolder holder, int position) {
        Movie movie = arrayListMovie.get(position);
        holder.imageView.setImageResource(movie.getIdThumbnails());
        holder.textView.setText(movie.getNameMovie());
        holder.imageButton.setImageResource(R.drawable.circleplay_icon);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }
    public Movie GetItem(int position){
        return arrayListMovie.get(position);
    }
    @Override
    public int getItemCount() {
        return arrayListMovie.size();
    }

    public Custom_Adapter_RecyclerView_Search(ArrayList<Movie> arrayListMovie){
        this.arrayListMovie = arrayListMovie;
    }

    @Override
    public int findRelativeAdapterPositionIn(@NonNull RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter, @NonNull RecyclerView.ViewHolder viewHolder, int localPosition) {
        return super.findRelativeAdapterPositionIn(adapter, viewHolder, localPosition);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
