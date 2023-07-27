package android.genzinema.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.genzinema.Controller.Cus_Item_Search_Adapter;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;
import android.genzinema.R;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity implements Cus_Item_Search_Adapter.OnItemClickListener {
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Movie> arrayList = new ArrayList<>();
    Cus_Item_Search_Adapter adapter;
    MovieHandler movieHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        addControls();

        movieHandler = new MovieHandler(getApplicationContext(),MovieHandler.DB_NAME,null,1);
        arrayList = movieHandler.getAllMovie();
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Cus_Item_Search_Adapter(arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(),e.getY());
                if(view != null){
                    int position = rv.getChildAdapterPosition(view);
                    Movie movie = adapter.GetItem(position);
                    Intent intent = new Intent(SearchPage.this, DetailMoviePage.class);
                    intent.putExtra("idMV",movie.getIdMV());
                    intent.putExtra("idGenreMV",movie.getIdGenre());
                    intent.putExtra("idStyleMV",movie.getIdType());

                    startActivity(intent);
                }
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchView.getQuery().length() == 0){
                    adapter = new Cus_Item_Search_Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    ArrayList<Movie> tempArrayList = movieHandler.searchData(searchView.getQuery().toString());
                    if (tempArrayList != null){
                        adapter = new Cus_Item_Search_Adapter(tempArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        adapter = new Cus_Item_Search_Adapter(arrayList);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(searchView.getQuery().length() == 0){
                    adapter = new Cus_Item_Search_Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    ArrayList<Movie> tempArrayList = movieHandler.searchData(searchView.getQuery().toString());
                    if (tempArrayList != null){
                        adapter = new Cus_Item_Search_Adapter(tempArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        adapter = new Cus_Item_Search_Adapter(arrayList);
                        recyclerView.setAdapter(adapter);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(searchView.getQuery().length() == 0){
                    adapter = new Cus_Item_Search_Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                    Log.d("go", " go");
                }
                else{
                    ArrayList<Movie> tempArrayList = movieHandler.searchData(searchView.getQuery().toString());
                    if (tempArrayList == null){

                    }
                    else{
                        adapter = new Cus_Item_Search_Adapter(tempArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                }
                return false;
            }
        });
    }


    private void addControls(){
        recyclerView =findViewById(R.id.searchRecycleView);
        searchView = findViewById(R.id.searchViewText);
    }

    @Override
    public void onItemClick(int position) {

    }

}