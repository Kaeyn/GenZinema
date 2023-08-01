package android.genzinema.Controller;

import android.content.Context;
import android.genzinema.Model.Episode;
import android.genzinema.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Custom_Adapter_ListrView_Episode extends ArrayAdapter {
    Context context;
    ArrayList<Episode> arrayListEpisode = new ArrayList<>();
    int layoutItem;

    public Custom_Adapter_ListrView_Episode(@NonNull Context context, int resource, @NonNull ArrayList<Episode> arrayListEpisode) {
        super(context, resource, arrayListEpisode);
        this.context = context;
        this.arrayListEpisode = arrayListEpisode;
        this.layoutItem = resource;
    }
    @Override
    public int getCount() {
        return arrayListEpisode.size();
    }

    public Episode getItem(int position) {
        return arrayListEpisode.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Episode episode = arrayListEpisode.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutItem,null);
        }

        ImageView imgEp = (ImageView)convertView.findViewById(R.id.imgEp);
        imgEp.setImageResource(episode.getIdImgEp());

        TextView tvName = (TextView) convertView.findViewById(R.id.tvNameEp);
        tvName.setText(episode.getNameEp());

        TextView tvDetail = (TextView) convertView.findViewById(R.id.tvDetailEp);
        tvDetail.setText(episode.getDetailEp());

        return convertView;
    }

}
