package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.MovieHandler;
import android.os.Bundle;
import android.genzinema.R;

public class HomePage extends AppCompatActivity {
    MovieHandler movieHandler;;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        movieHandler = new MovieHandler(getApplicationContext(),MovieHandler.DB_NAME,null,1);
        movieHandler.onCreate(db);
        Intent intent = getIntent();
        if(intent.hasExtra("idMV")){
            Bundle bundle = new Bundle();
            bundle.putInt("idMV",intent.getIntExtra("idMV",0));
            FragmentManager fm = getSupportFragmentManager();
            fm.setFragmentResult("keyMain",bundle);
            loadFragment(new DetailMovie());
        }
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}