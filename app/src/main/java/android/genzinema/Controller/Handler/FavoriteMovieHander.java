package android.genzinema.Controller.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.FavoriteMovie;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FavoriteMovieHander extends SQLiteOpenHelper {
    private Context context;
    static ArrayList<FavoriteMovie> arrayListFMV = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/files/database/qlmv.db";
    static final String TABLE_NAME = "FavoriteMovies";
    static final String  ID_COL = "fvmovie_id";
    static final String IDMOVIE_COL = "movie_id";
    static final String EMAIL_COL = "email";
    static int lastIndex = 7;


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
        loadData();
    }
    void dropTable(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql = "Drop table " + TABLE_NAME;
        db.execSQL(sql);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList<Integer> getArrayListFMV (String email)
    {
        ArrayList<Integer> arrayList= new ArrayList<>();
        for (FavoriteMovie movie : arrayListFMV)
        {
            if(movie.getEmailUser().equals(email))
            {
                arrayList.add(movie.getIdMovie());
            }
        }
        return arrayList;
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
    public String AddOrDelete(String email, Integer idMV){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + IDMOVIE_COL + " = ? AND " + EMAIL_COL + " = ?",new String[]{idMV.toString(), email});
        Log.d("me", "AddOrDelete: "+cursor.getCount());
        if(cursor.getCount() > 0){
            cursor.close(); // Close the cursor after use
            db.close();
            return DeleteFavoriteMV(email, idMV);
        }else{
            cursor.close(); // Close the cursor after use
            db.close();
            return AddFavoriteMV(email, idMV);
        }





    }

    public String AddFavoriteMV(String email, Integer idMV){
        loadData();
        lastIndex++;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(ID_COL,lastIndex);
        values.put(IDMOVIE_COL,idMV);
        values.put(EMAIL_COL,email);
        db.insert(TABLE_NAME,null, values);
        db.close();
        return "Thêm ok";
    }
    public String DeleteFavoriteMV(String email, Integer idMV){
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
//        String sql = "DELETE FROM " + TABLE_NAME +" WHERE " + IDMOVIE_COL + " = "+idMV+" AND " + EMAIL_COL + " = "+email;
//        db.execSQL(sql);
//        db.close();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME,IDMOVIE_COL+"=? and "+EMAIL_COL+" =?",new String[]{String.valueOf(idMV),email});
        db.close();
        return "Xoa ok";
    }





}
