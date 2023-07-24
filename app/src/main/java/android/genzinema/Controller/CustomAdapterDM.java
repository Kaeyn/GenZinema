package android.genzinema.Controller;

import android.content.Context;
import android.genzinema.Model.Ep;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapterDM extends ArrayAdapter {
    Context context;
    ArrayList<Ep> arrayListEp= new ArrayList<>();
    int layoutItem;

    public CustomAdapterDM(@NonNull Context context, int resource, @NonNull Object[] objects, Context context1, ArrayList<Ep> arrayListEp, int layoutItem) {
        super(context, resource, objects);
        this.context = context1;
        this.arrayListEp = arrayListEp;
        this.layoutItem = layoutItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
//        Ep ep = arrayListEp.get(position);

//        if(convertView == null){
//            convertView = LayoutInflater.from(context).inflate(layoutItem,null);
//        }
//        ImageView imgEp = (ImageView)convertView.findViewById(R.id.imagev);
//        imgEp.setImageResource(ep.getIdImgEp());
//
//        TextView tvName = (TextView) convertView.findViewById(R.id.tvname);
//        tvName.setText(ep.getNameEp());







    }
}
