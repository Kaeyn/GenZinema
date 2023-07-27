package android.genzinema.Controller;

import android.genzinema.Model.Movie;
import android.genzinema.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomRecycleView extends RecyclerView.Adapter<CustomRecycleView.YourViewHolder> {

    private List<Movie> dataList;

    public CustomRecycleView(List<Movie> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public YourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom_item_hotnnew, parent, false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle results = new Bundle();
            }
        });
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

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    private Cus_Item_Search_Adapter.OnItemClickListener clickListener;

    public void setOnItemClickListener(Cus_Item_Search_Adapter.OnItemClickListener listener) {
        this.clickListener = listener;
    }
    public Movie GetItem(int position){
        return dataList.get(position);
    }
    // Define YourViewHolder class to hold references to views in your item layout
    public class YourViewHolder extends RecyclerView.ViewHolder {
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
