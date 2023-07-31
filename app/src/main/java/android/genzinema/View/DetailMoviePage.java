package android.genzinema.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;
import android.genzinema.R;
import android.view.MenuItem;
import android.widget.Toast;

public class DetailMoviePage extends AppCompatActivity {

    String email;
    MovieHandler movieHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Thông tin phim");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail_movie_page);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        movieHandler = new MovieHandler(getApplicationContext(),MovieHandler.DB_NAME,null,1);
        if(intent.hasExtra("idMV")) {
            Movie movie = movieHandler.GetMovieByID(intent.getIntExtra("idMV",0));
            Bundle bundle = new Bundle();
            bundle.putInt("idMV", movie.getIdMV());
            bundle.putString("email",email);
            FragmentManager fm = getSupportFragmentManager();
            fm.setFragmentResult("keyMain", bundle);
            loadFragment(new FragmentDetailMovie());
        }
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutDMP, fragment);
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainHome.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Call the onBackPressed() method to navigate back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}