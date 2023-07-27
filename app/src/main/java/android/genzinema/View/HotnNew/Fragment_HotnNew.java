package android.genzinema.View.HotnNew;

import android.genzinema.Controller.CustomAdapter.Hot_New_Pager_Adapter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

    ViewPager2 subcategoryViewPager;
    TabLayout subcategoryLayout;

    Hot_New_Pager_Adapter hot_new_pager_adapter;

    boolean isTransitionComplete = true;
    int selectedTabIndex = 0;

    int currentScrollX = 0;
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

        addControls(view);
        subcategoryViewPager.setUserInputEnabled(false);
        subcategoryLayout.setSelectedTabIndicator(R.drawable.tab_indicator);

        Hot_New_Pager_Adapter hot_new_pager_adapter = new Hot_New_Pager_Adapter(requireActivity());
        subcategoryViewPager.setAdapter(hot_new_pager_adapter);

        new TabLayoutMediator(subcategoryLayout, subcategoryViewPager, (tab, position) -> {

            tab.view.setBackground(getResources().getDrawable(R.drawable.tab_indicator_black));
            for(int i=0; i < subcategoryLayout.getTabCount(); i++) {
                View tab1 = ((ViewGroup) subcategoryLayout.getChildAt(0)).getChildAt(i);
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab1.getLayoutParams();
                p.setMargins(0, 0, 40, 0);
                tab1.requestLayout();
            }

            if (position == 0){
                tab.setText("Top 10 Phim Hot");
                tab.view.setBackground(getResources().getDrawable(R.drawable.tab_indicator_onclick));
            } else if (position == 1) {
                tab.setText("Phim mới nhất");
            } else if (position == 2) {
                tab.setText("Phim hay");
            }

        }).attach();

        addEvents();
        return view;
    }


    private void addControls(View view){
        subcategoryLayout = view.findViewById(R.id.hotNewTabLayout);
        subcategoryViewPager = view.findViewById(R.id.hotNewViewPager);

    }

    private void addEvents(){
        subcategoryLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTabIndex = tab.getPosition();
                // Set isTransitionComplete to false to indicate that a transition is in progress
                isTransitionComplete = false;
                // Update the ViewPager to the selected tab without smooth scrolling
                subcategoryViewPager.setCurrentItem(selectedTabIndex, true);
           }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.view.setBackground(getResources().getDrawable(R.drawable.tab_indicator_black));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                selectedTabIndex = tab.getPosition();
                subcategoryViewPager.setCurrentItem(selectedTabIndex, false);
            }
        });


        subcategoryViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    // The ViewPager2 has finished settling (scroll complete)
                    subcategoryLayout.getTabAt(selectedTabIndex).view.setBackground(getResources().getDrawable(R.drawable.tab_indicator_onclick));

                }
            }

            @Override
            public void onPageSelected(int position) {
                selectedTabIndex = position;
            }
        });
    }




}
