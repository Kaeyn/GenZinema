package android.genzinema.View;

import android.genzinema.Controller.MyFragmentAdapterDetailMovie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailMovie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailMovie extends Fragment {
    ProgressBar pb;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    MyFragmentAdapterDetailMovie adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailMovie() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailMovie.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailMovie newInstance(String param1, String param2) {
        DetailMovie fragment = new DetailMovie();
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

    private void addControl(View view){
        pb = view.findViewById(R.id.pbDetailMV);
        tabLayout = view.findViewById(R.id.tablayoutDM);
        viewPager = view.findViewById(R.id.viewPageDM);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        addControl(view);
        pb.setProgress(20);
        tabLayout.addTab(tabLayout.newTab().setText("Các tập"));
        tabLayout.addTab(tabLayout.newTab().setText("Nội dung tương tự"));

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        adapter = new MyFragmentAdapterDetailMovie(fragmentManager , getLifecycle());
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });




        return view;
    }
}