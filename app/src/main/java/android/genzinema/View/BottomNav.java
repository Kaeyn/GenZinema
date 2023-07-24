package android.genzinema.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.genzinema.R;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav extends AppCompatActivity {
    ActionBar actionBar;
    FrameLayout frameFragment;
    BottomNavigationView bttNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        actionBar = getSupportActionBar();

        addControls();

        addEvents();
    }

    public void addControls(){
        frameFragment = (FrameLayout) findViewById(R.id.frameFragment);
        bttNav = (BottomNavigationView) findViewById(R.id.bttnav);
    }

    public void addEvents(){

    }
}