package android.genzinema.Controller;

import android.content.Context;
import android.genzinema.Model.Movie;
import android.genzinema.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class Custom_Adapter_GridView_Collection extends BaseAdapter {

    Context context;
    ArrayList<Movie> arrayListMovie = new ArrayList<Movie>();

    public Custom_Adapter_GridView_Collection(Context context, ArrayList<Movie> arrayListMovie) {
        this.context = context;
        this.arrayListMovie = arrayListMovie;
    }

    @Override
    public int getCount() {
        return arrayListMovie.size();
    }

    @Override
    public Object getItem(int position) {return arrayListMovie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        ImageView idThumbnailMVs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout_item_grid_collectmv, parent, false);
            holder.idThumbnailMVs = convertView.findViewById(R.id.idThumbnailMV);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Movie movie = arrayListMovie.get(position);
        holder.idThumbnailMVs.setImageResource(movie.getIdThumbnails());

        return convertView;
    }
    public Movie GetItem(int position){
        return arrayListMovie.get(position);
    }

}
