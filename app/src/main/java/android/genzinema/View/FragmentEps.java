package android.genzinema.View;

import android.genzinema.Controller.CustomAdapterEp;
import android.genzinema.Model.Ep;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEps extends Fragment {
    TextView tvNameMV;
    ListView lvEp;
    ArrayList<Ep> arrayListEp = new ArrayList<Ep>();
    int[]lstIdImg = new int[]{R.drawable.setting_icon, R.drawable.favorite_icon, R.drawable.return_icon,R.drawable.tab_indicator};
    String[] lstName = new String[]{"1.Tập 1","2.Tập 2","3.Tập 3","4.Tập 4"};
    String[] lstTime = new String[]{"23m","23m","23m","24m"};
    String[] lstDetail = new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
            "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc",
            "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"};


    CustomAdapterEp adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEps() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrgamentEps.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEps newInstance(String param1, String param2) {
        FragmentEps fragment = new FragmentEps();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frgament_eps, container, false);
        addControls(view);
        addEvents();
        return view;
    }
    void addControls(View view){
        tvNameMV = view.findViewById(R.id.tvTenPhim);
        lvEp = view.findViewById(R.id.lvEp);
    }
    void addEvents(){
        arrayListEp= Ep.initData(lstIdImg, lstName, lstTime,lstDetail);
        adapter=new CustomAdapterEp(getContext(),R.layout.layout_custom_item_ep_dm,arrayListEp);
        lvEp.setAdapter(adapter);
    }
}