package android.genzinema.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.genzinema.View.Fragment.Fragment_Collections;
import android.os.Bundle;
import android.genzinema.R;
import android.widget.FrameLayout;

public class Activity_Collections extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
        Intent intent = getIntent();
        Bundle results = new Bundle();
        results.putString("Email", intent.getStringExtra("Email"));
        getSupportFragmentManager().setFragmentResult("emailMainToFavorite", results);
        frameLayout = findViewById(R.id.frameFragment);
        loadFragment(new Fragment_Collections());

    }
    public void loadFragment(Fragment frag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragment, frag);
        ft.commit();
    }
}