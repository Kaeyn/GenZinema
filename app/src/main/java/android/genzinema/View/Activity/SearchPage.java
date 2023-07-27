package android.genzinema.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.genzinema.Controller.CustomAdapter.Cus_Item_Search_Adapter;
import android.genzinema.Controller.Handler.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;
import android.genzinema.R;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

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
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query == ""){
                    adapter = new Cus_Item_Search_Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else{
                    ArrayList<Movie> tempArrayList = movieHandler.searchData(query);
                    if (tempArrayList == null){
                        ArrayList<Movie> emptyArrayList = new ArrayList<>();
                        adapter = new Cus_Item_Search_Adapter(emptyArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        adapter = new Cus_Item_Search_Adapter(arrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText == ""){
                    adapter = new Cus_Item_Search_Adapter(arrayList);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    ArrayList<Movie> tempArrayList = movieHandler.searchData(newText);
                    if (tempArrayList == null){
                        ArrayList<Movie> emptyArrayList = new ArrayList<>();
                        adapter = new Cus_Item_Search_Adapter(emptyArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        adapter = new Cus_Item_Search_Adapter(tempArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                }
                return true;
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Call the onBackPressed() method to navigate back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}