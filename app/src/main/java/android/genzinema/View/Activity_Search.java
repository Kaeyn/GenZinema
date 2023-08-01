package android.genzinema.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.genzinema.Controller.Custom_Adapter_RecyclerView_Search;
import android.genzinema.Controller.MovieHandler;
import android.genzinema.Model.Movie;
import android.os.Bundle;
import android.genzinema.R;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;

public class Activity_Search extends AppCompatActivity implements Custom_Adapter_RecyclerView_Search.OnItemClickListener {
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Movie> arrayList = new ArrayList<>();
    Custom_Adapter_RecyclerView_Search adapter;
    MovieHandler movieHandler;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
//        Movie movie = new Movie(1, 1, 1, R.drawable.johnweak,"url", "Mua He Hoa Phuong No", "SonTungMTP", "LeHuuMyn", "2018", "Phim aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        arrayList.add(movie);
        movieHandler = new MovieHandler(getApplicationContext(),MovieHandler.DB_NAME,null,1);
        arrayList = movieHandler.getAllMovie();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Custom_Adapter_RecyclerView_Search(arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(),e.getY());
                if(view != null){
                    int position = rv.getChildAdapterPosition(view);
                    Movie movie = adapter.GetItem(position);
                    Intent intent = new Intent(Activity_Search.this, Activity_Detail_Movie.class);
                    intent.putExtra("idMV",movie.getIdMV());
                    intent.putExtra("idGenreMV",movie.getIdGenre());
                    intent.putExtra("idStyleMV",movie.getIdType());
                    intent.putExtra("email", email);
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
                    adapter = new Custom_Adapter_RecyclerView_Search(arrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else{
                    ArrayList<Movie> tempArrayList = movieHandler.searchData(query);
                    if (tempArrayList == null){
                        ArrayList<Movie> emptyArrayList = new ArrayList<>();
                        adapter = new Custom_Adapter_RecyclerView_Search(emptyArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        adapter = new Custom_Adapter_RecyclerView_Search(arrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText == ""){
                    adapter = new Custom_Adapter_RecyclerView_Search(arrayList);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    ArrayList<Movie> tempArrayList = movieHandler.searchData(newText);
                    if (tempArrayList == null){
                        ArrayList<Movie> emptyArrayList = new ArrayList<>();
                        adapter = new Custom_Adapter_RecyclerView_Search(emptyArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        adapter = new Custom_Adapter_RecyclerView_Search(tempArrayList);
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
            finish(); // Call the onBackPressed() method to navigate back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}