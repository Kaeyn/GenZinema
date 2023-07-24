package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.genzinema.Controller.CustomAdapterRecyFilm;
import android.genzinema.Model.Movie;
import android.os.Bundle;
import android.genzinema.R;
import android.widget.ImageView;

import java.util.ArrayList;

public class RecycleVIewHomeFilm extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imgFilm;
    ArrayList<Movie> filmArrayList = new ArrayList<>();
    CustomAdapterRecyFilm adapterRecyFilm;

    int[] lstId = new int[]{1,2,3,4,5};
    int[] lstImg = new int[]{R.drawable.johnweak, R.drawable.johnweak, R.drawable.johnweak, R.drawable.johnweak, R.drawable.johnweak};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_home_film);

        addControls();

        filmArrayList = Movie.initData(lstId, lstImg);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapterRecyFilm = new CustomAdapterRecyFilm(filmArrayList);
        recyclerView.setAdapter(adapterRecyFilm);
    }

    private void addControls(){
        recyclerView = findViewById(R.id.recyViewFilm);
        imgFilm = findViewById(R.id.imgHomeFilm);
    }

    private void addEvents(){

    }
}