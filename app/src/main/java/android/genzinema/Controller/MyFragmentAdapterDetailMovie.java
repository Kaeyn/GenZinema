package android.genzinema.Controller;

import android.genzinema.View.FragmentSimilarStyle;
import android.genzinema.View.FrgamentEps;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapterDetailMovie extends FragmentStateAdapter {

    public MyFragmentAdapterDetailMovie(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new FrgamentEps();
        }
        return new FragmentSimilarStyle();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
