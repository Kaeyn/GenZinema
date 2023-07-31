package android.genzinema.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.genzinema.R;
import android.view.MenuItem;
import android.widget.Toast;

public class DetailMoviePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("GenZinema");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail_movie_page);
        Intent intent = getIntent();
        if(intent.hasExtra("idMV")) {
//            Toast.makeText(getApplication(),"DetailMoviePage idMV: "+intent.getIntExtra("idMV",0),Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putInt("idMV", intent.getIntExtra("idMV", 0));
            bundle.putInt("idGenreMV", intent.getIntExtra("idGenreMV", 0));
            bundle.putInt("idStyleMV", intent.getIntExtra("idStyleMV", 0));

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
        super.onBackPressed();
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