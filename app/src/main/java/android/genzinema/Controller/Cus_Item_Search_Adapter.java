package android.genzinema.Controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Cus_Item_Search_Adapter extends RecyclerView.Adapter<Cus_Item_Search_Adapter.MyViewHolder> {

    @NonNull
    @Override
    public Cus_Item_Search_Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Cus_Item_Search_Adapter.MyViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull Cus_Item_Search_Adapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ImageButton imageButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
