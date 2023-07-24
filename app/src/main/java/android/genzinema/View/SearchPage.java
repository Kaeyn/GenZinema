package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.genzinema.Controller.Cus_Item_Search_Adapter;
import android.genzinema.Model.Movie;
import android.os.Bundle;
import android.genzinema.R;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<Movie> arrayList = new ArrayList<>();
    Cus_Item_Search_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        addControls();

        Movie movie = new Movie(1, 1, 1, R.drawable.johnweak,"url", "Mua He Hoa Phuong No", "SonTungMTP", "LeHuuMyn", "2018", "Phim aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        arrayList.add(movie);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Cus_Item_Search_Adapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void addControls(){
        recyclerView =findViewById(R.id.searchRecycleView);

    }
}