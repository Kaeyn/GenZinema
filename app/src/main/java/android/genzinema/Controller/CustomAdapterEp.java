package android.genzinema.Controller;

import android.content.Context;
import android.genzinema.Model.Ep;
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

public class CustomAdapterEp extends ArrayAdapter {
    Context context;
    ArrayList<Ep> arrayListEp= new ArrayList<>();
    int layoutItem;

    public CustomAdapterEp(@NonNull Context context, int resource, @NonNull ArrayList<Ep>arrayListEp) {
        super(context, resource, arrayListEp);
        this.context = context;
        this.arrayListEp = arrayListEp;
        this.layoutItem = resource;
    }
    @Override
    public int getCount() {
        return arrayListEp.size();
    }

    public Ep getItem(int position) {
        return arrayListEp.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ep ep = arrayListEp.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutItem,null);
        }

        ImageView imgEp = (ImageView)convertView.findViewById(R.id.imgEp);
        imgEp.setImageResource(ep.getIdImgEp());

        TextView tvName = (TextView) convertView.findViewById(R.id.tvNameEp);
        tvName.setText(ep.getNameEp());

        TextView tvDetail = (TextView) convertView.findViewById(R.id.tvDetailEp);
        tvDetail.setText(ep.getDetailEp());

        return convertView;
    }

}
