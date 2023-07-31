package android.genzinema.Controller.Handler;

import android.genzinema.View.Fragment.Fragment_HotnNew_RecycleView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Hot_New_Pager_Adapter extends FragmentStateAdapter {

    String email;
    public Hot_New_Pager_Adapter(@NonNull FragmentActivity fragmentActivity, String email) {
        super(fragmentActivity);
        this.email = email;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new Fragment_HotnNew_RecycleView("Top10", email);
        } else if (position == 1) {
            return new Fragment_HotnNew_RecycleView("Newest", email);
        } else if (position == 2) {
            return new Fragment_HotnNew_RecycleView("GoodMovie", email);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
