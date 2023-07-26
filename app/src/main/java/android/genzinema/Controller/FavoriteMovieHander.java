package android.genzinema.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.FavoriteMovie;
import android.genzinema.Model.Movie;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FavoriteMovieHander extends SQLiteOpenHelper {
    private Context context;
    static ArrayList<FavoriteMovie> arrayListFMV = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/files/database/qlmv.db";
    static final String TABLE_NAME = "FavoriteMovies";
    static final String  ID_COL = "fvmovie_id";
    static final String IDMOVIE_COL = "style_id";
    static final String EMAIL_COL = "genre_id";


    public FavoriteMovieHander(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABLE_NAME, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = " CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
                ID_COL+" INTEGER NOT NULL UNIQUE, " +
                IDMOVIE_COL+" INTEGER NOT NULL, " +
                EMAIL_COL +" TEXT NOT NULL, " +
                "FOREIGN KEY("+IDMOVIE_COL+") REFERENCES "+MovieHandler.TABLE_NAME+"("+MovieHandler.IDMOVIE_COL+")," +
                "FOREIGN KEY("+EMAIL_COL+") REFERENCES "+UserHandler.TABLE_NAME+"("+UserHandler.EMAIL_COL+")," +
                "PRIMARY KEY( "+ID_COL+" ));";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (1,1,'1')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (2,1,'2')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (3,2,'2')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (4,4,'2')";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void loadData(){
        arrayListFMV.clear();
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            FavoriteMovie favoriteMovie = new FavoriteMovie();
            favoriteMovie.setIdFMV(cursor.getInt(0));
            favoriteMovie.setIdMovie(cursor.getInt(1));
            favoriteMovie.setEmailUser(cursor.getString(2));
            arrayListFMV.add(favoriteMovie);
        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
    }
    public boolean IsAdded(String email, Integer idMV){
        for (FavoriteMovie favoriteMovie : arrayListFMV){
            if(favoriteMovie.getIdMovie() == idMV && favoriteMovie.getEmailUser() == email){
                return true;
            }
        }
        return false;
    }





}
