package android.genzinema.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.Episode;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EpHandler extends SQLiteOpenHelper {
    private static Context context;
    static ArrayList<Episode> arrayListEpisode = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/files/database/qlmv.db";
    static final String TABLE_NAME = "Episode";
    static final String IDEP_COL = "ep_id";
    static final String THUMBNNAIL_COL = "ep_thumbnail";

    static final String NAMEEP_COL = "ep_name";
    static final String DETAILEP_COL = "ep_detail";
    static final String URLEP_COL = "ep_url";
    static final String IDMOVIE_COL = "ep_movie_id";


    public EpHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = " CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
                IDEP_COL+" INTEGER NOT NULL, " +
                THUMBNNAIL_COL+" TEXT NOT NULL, " +
                NAMEEP_COL +" TEXT NOT NULL, " +
                IDMOVIE_COL+" INTEGER NOT NULL, " +
                DETAILEP_COL +" TEXT NOT NULL, " +
                URLEP_COL +" TEXT NOT NULL, " +
                "FOREIGN KEY("+IDMOVIE_COL+") REFERENCES "+MovieHandler.TABLE_NAME+"("+MovieHandler.IDMOVIE_COL+")," +
                "PRIMARY KEY( "+IDEP_COL+" ));";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (1,'johnweak','Tâp 1', 3, 'Oh yeahhhhhhhhhhhhh.','1tazr67-wERTdQ9tCdnJBiK3-P_ZqWJU0')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (2,'sieunhan','Tâp 2', 3, 'Oh nooooooooooo.','1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (3,'johnweak','Tâp 3', 3, 'It movie 2.','1ENAzcgYVihG8NHmeNDOrxh3mjzJLjD0r')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (4,'bogia','Tâp 4', 3, 'It movie 1 about johnWeak','1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (5,'ttcs','Tâp 5', 3, 'It movie 1 about johnWeak','1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW')";
        db.execSQL(sql);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static void loadData(){
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME, null);
        cursor.moveToFirst();
        do{
            Episode episode = new Episode();
            episode.setIdEp(cursor.getInt(0));
            episode.setIdImgEp(context.getResources().getIdentifier(cursor.getString(1),"drawable","android.genzinema"));
            episode.setNameEp(cursor.getString(2));
            episode.setIdMV(cursor.getInt(3));
            episode.setDetailEp(cursor.getString(4));
            episode.setUrlEp(cursor.getString(5));

            arrayListEpisode.add(episode);

        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
    }
    public ArrayList<Episode> GetAllEpByMovieID(Integer movieID) {
        loadData();
        ArrayList<Episode> arrayList = new ArrayList<>();
        for (Episode episode : arrayListEpisode) {
            Integer idMV = episode.getIdMV();
            if (idMV != null && idMV.intValue() == movieID) {
                arrayList.add(episode);
            }
        }
        return arrayList;
    }

    public Episode GetMovieByID(int ID){
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+IDMOVIE_COL+" = "+ID, null);
        cursor.moveToFirst();
        Episode episode = new Episode();
        episode.setIdEp(cursor.getInt(0));
        episode.setIdImgEp(cursor.getInt(1));
        episode.setNameEp(cursor.getString(2));
        episode.setIdMV(cursor.getInt(3));
        episode.setDetailEp(cursor.getString(4));
        episode.setUrlEp(cursor.getString(5));
        cursor.close();
        db.close();
        return episode;
    }
    public void droptbEp(SQLiteDatabase db){
        db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql = "Drop table " + TABLE_NAME;
        db.execSQL(sql);
        db.close();
    }


}
