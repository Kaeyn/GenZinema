package android.genzinema.Controller;

import android.genzinema.View.HotnNew_RecycleView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Hot_New_Pager_Adapter extends FragmentStateAdapter {

    public Hot_New_Pager_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){

            return new HotnNew_RecycleView("Popular");

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
