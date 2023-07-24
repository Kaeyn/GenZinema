package android.genzinema.View;

import android.genzinema.Controller.Hot_New_Pager_Adapter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_HotnNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_HotnNew extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_HotnNew() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_HotnNew.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_HotnNew newInstance(String param1, String param2) {
        Fragment_HotnNew fragment = new Fragment_HotnNew();
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
        View view = inflater.inflate(R.layout.fragment__hotn_new, container, false);

        TabLayout subcategoryLayout = view.findViewById(R.id.hotNewTabLayout);
        ViewPager2 subcategoryViewPager = view.findViewById(R.id.hotNewViewPager);

        Hot_New_Pager_Adapter hot_new_pager_adapter = new Hot_New_Pager_Adapter(requireActivity());
        subcategoryViewPager.setAdapter(hot_new_pager_adapter);

        new TabLayoutMediator(subcategoryLayout, subcategoryViewPager, (tab, position) -> {
            if (position == 0){
                tab.setText("Popular");
            } else if (position == 1) {
                tab.setText("Top10");
            }
        }).attach();


        return view;
    }
}
