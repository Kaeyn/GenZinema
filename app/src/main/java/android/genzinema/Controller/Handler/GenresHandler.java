package android.genzinema.Controller.Handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.Genres;
import android.genzinema.Model.Movie;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GenresHandler extends SQLiteOpenHelper {

    static ArrayList<Genres> arrayListGenres = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/files/database/qlmv.db";
    static final String TABLE_NAME = "Genres_Movie";
    static final String IDGENRE_COL = "genre_id";
    static final String NAMEGENRE_COL = "genre_name";

    public GenresHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = " CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " ( " +
                IDGENRE_COL + " INTEGER NOT NULL UNIQUE, "+
                NAMEGENRE_COL + " TEXT NOT NULL, " +
                " PRIMARY KEY( " +IDGENRE_COL+ ")) ";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (1,'Hành động')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (2,'Kinh dị')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (3,'Tình cảm')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (4,'Hài ướt')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (5,'Anime')";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static ArrayList<Genres>loadData(){
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME, null);
        cursor.moveToFirst();
        do{
            Genres genres = new Genres();
            genres.setGenre_id(cursor.getInt(0));
            genres.setGenre_name(cursor.getString(1));
            arrayListGenres.add(genres);

        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
        return arrayListGenres;
    }

    public void droptbGenres(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql = "Drop table " + TABLE_NAME;
        Log.d("droptbGenres", "a");
        db.execSQL(sql);
        db.close();
    }
}
