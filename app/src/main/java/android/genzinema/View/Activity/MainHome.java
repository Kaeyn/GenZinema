package android.genzinema.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.Handler.EpHandler;
import android.genzinema.Controller.Handler.FavoriteMovieHander;
import android.genzinema.Controller.Handler.GenresHandler;
import android.genzinema.Controller.Handler.MovieHandler;
import android.genzinema.Controller.Handler.StyleHandler;
import android.genzinema.Model.Movie;
import android.genzinema.View.Fragment.Fragment_Collections;
import android.genzinema.View.Fragment.Fragment_Home;
import android.genzinema.View.Fragment.Fragment_HotnNew;
import android.os.Bundle;
import android.genzinema.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainHome extends AppCompatActivity{
    //Controls
    ActionBar actionBar;
    String email;
    FrameLayout frameFragment, childFrameLayout;
    BottomNavigationView bttNav;
    GenresHandler genresHandler;
    StyleHandler styleHandler;
    MovieHandler movieHandler;
    EpHandler epHandler;
    FavoriteMovieHander favoriteMovieHander;

    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        setUserEmail();
        addControls();
        //Load home page as default
        loadFragment(new Fragment_Home(email));

        addEvents();
    }
    private void setUserEmail(){
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
    }
    private void addControls(){
        actionBar = getSupportActionBar();
        bttNav = findViewById(R.id.bttnav);
        frameFragment = findViewById(R.id.frameFragment);
        childFrameLayout = findViewById(R.id.framelayout_content);
    }

    private void addEvents(){
        loadFragment(new Fragment_Home());
        movieHandler = new MovieHandler(getApplicationContext(),MovieHandler.DB_NAME,null,1);
        genresHandler = new GenresHandler(getApplicationContext(),GenresHandler.DB_NAME,null,1);
        styleHandler = new StyleHandler(getApplicationContext(),StyleHandler.DB_NAME,null,1);
        epHandler = new EpHandler(getApplicationContext(),EpHandler.DB_NAME,null,1);
        favoriteMovieHander = new FavoriteMovieHander(getApplication(), FavoriteMovieHander.DB_NAME,null,1);

        genresHandler.onCreate(db);
        styleHandler.onCreate(db);
        movieHandler.onCreate(db);
        favoriteMovieHander.onCreate(db);
        bttNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int idFrame = item.getItemId();
                if(idFrame == R.id.home){
                    loadFragment(new Fragment_Home(email));
                } else if (idFrame == R.id.hotnnew) {
                    loadFragment(new Fragment_HotnNew(email));
                }
                else if (idFrame == R.id.collections) {
                    loadFragment(new Fragment_Collections(email));
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
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.userProfile) {

            Intent intent = new Intent(MainHome.this, UserProfile.class);
            intent.putExtra("email",email);
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