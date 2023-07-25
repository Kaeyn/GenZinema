package android.genzinema.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.genzinema.R;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainHome extends AppCompatActivity {
    ActionBar actionBar;
    FrameLayout frameFragment, childFrameLayout;
    BottomNavigationView bttNav;



    @SuppressLint("MissingInflatedId")
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
        childFrameLayout = (FrameLayout) findViewById(R.id.framelayout_content);
    }

    public void addEvents(){
        loadFragment(new Fragment_Home());


        bttNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int idFrame = item.getItemId();
                if(idFrame == R.id.home){
                    loadFragment(new Fragment_Home());
                    return true;
                } else if (idFrame == R.id.hotnnew) {
                    loadFragment(new Fragment_HotnNew());
                    return true;
                }
                else if (idFrame == R.id.collections) {
                    loadFragment(new Fragment_Collections());
                    return true;
                }
                return true;
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_navigation, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.searchIcon){
            Intent intent = new Intent(MainHome.this, SearchPage.class);
            startActivity(intent);
        } else if (id == R.id.userProfile) {
            Intent intent = new Intent(MainHome.this, UserProfile.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragment, fragment);
        ft.commit();
    }
}