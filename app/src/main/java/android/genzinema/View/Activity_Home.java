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
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.EpHandler;
import android.genzinema.Controller.FavoriteMovieHander;
import android.genzinema.Controller.GenresHandler;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Controller.StyleHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;
import android.genzinema.R;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Activity_Home extends AppCompatActivity{
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
    ArrayList<Movie> arrayListMV = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

//        if(intent.hasExtra("idMV")) {
//            Toast.makeText(getApplication(),"Main home idMV: "+intent.getIntExtra("idMV",0),Toast.LENGTH_SHORT).show();
//            Bundle bundle = new Bundle();
//            bundle.putInt("idMV", intent.getIntExtra("idMV", 0));
//            bundle.putInt("idGenreMV", intent.getIntExtra("idGenreMV", 0));
//            bundle.putInt("idStyleMV", intent.getIntExtra("idStyleMV", 0));
//
//            FragmentManager fm = getSupportFragmentManager();
//            fm.setFragmentResult("keyMain", bundle);
//            loadFragment(new DetailMovie());
//        }
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
                    loadFragment(new Fragment_Home());
                    return true;
                } else if (idFrame == R.id.hotnnew) {
                    loadFragment(new Fragment_HotnNew(email));
                    return true;
                }
                else if (idFrame == R.id.collections) {
                    loadFragment(new Fragment_Collections(email));
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
            Intent intent = new Intent(Activity_Home.this, Activity_Search.class);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.userProfile) {

            Intent intent = new Intent(Activity_Home.this, Activity_User_Profile.class);
            intent.putExtra("email",email);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void loadFragment(Fragment fragment){
        Bundle results = new Bundle();
        results.putString("email", email);
        getSupportFragmentManager().setFragmentResult("emailMainToFHome", results);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragment, fragment);
        ft.commit();
    }
}