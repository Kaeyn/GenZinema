package android.genzinema.Controller.CustomAdapter;

import android.genzinema.View.HotnNew.Fragment_HotnNew_RecycleView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Hot_New_Pager_Adapter extends FragmentStateAdapter {

    public Hot_New_Pager_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new Fragment_HotnNew_RecycleView("Top10");
        } else if (position == 1) {
            return new Fragment_HotnNew_RecycleView("Newest");
        } else if (position == 2) {
            return new Fragment_HotnNew_RecycleView("GoodMovie");
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
