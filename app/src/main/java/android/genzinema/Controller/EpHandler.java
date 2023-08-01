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
        droptbEp();
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
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (1,'ep1ttcs','Tập 1', 3, 'Tôi từ 1 thằng thất nghiệp chuyển sinh sang thế giới mới','16pmiD1vUO1mpNWU85vTgdsZ_fYiu4Ks4')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (2,'ep2ttcs','Tập 2', 3, 'Sư phụ của tôi là một cô nhóc?','1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (3,'ep3ttcs','Tập 3', 3, 'Thế giới mới bắt đầu!','17DqvniSoy6PT0VaGYeMDcsb51rIMwpw8')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (4,'ep1hdmt','Tập 1', 6, 'Cuộc gặp gỡ đầu tiên với y tá Won','1nejTzBNkuLJmsXcbHH_uxWnW5zAXusN0')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (5,'ep2hdmt','Tập 2', 6, 'Chiến tranh giữa Triều Tiên và Hàn! Cuộc gặp kết thúc','1JBxlijogEPvKjbqf5S5u8xWMLN1DUqP')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (6,'ep3hdmt','Tập 3', 6, 'Trên đường quay về từ chiến thắng','1UQdmP460mYAZKXw-UAamjCwZJhTPWWU9')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (7,'ep1over','Tập 1', 1, 'Game đã bị xóa và tôi bị kẹt lại??','1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (8,'ep2over','Tập 2', 1, 'Giả vờ lãnh đạo như một nhà vua','1nejTzBNkuLJmsXcbHH_uxWnW5zAXusN0')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (9,'ep3over','Tập 3', 1, 'Bắt đầu kế hoạch cai trị thế giới!','1UQdmP460mYAZKXw-UAamjCwZJhTPWWU9')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (10,'ep1sanquy','Tập 1', 9, 'Thức tỉnh năng lực!','1mcvF1aLucjkfB06Usm2sh_SZjzrqNxQB')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (11,'ep2sanquy','Tập 2', 9, 'Tiệm mì kì lạ và bất ổn','1d2FtVxPdVAxntHkXJwRagJyW30OYwL6U')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (12,'ep3sanquy','Tập 3', 9, 'Cuộc săn quỷ. Bắt đầu','16pmiD1vUO1mpNWU85vTgdsZ_fYiu4Ks4')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (13,'ep1kingdom','Tập 1', 12, 'Kinh thành đóng cửa, Xác sống là gì?','16pmiD1vUO1mpNWU85vTgdsZ_fYiu4Ks4')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (14,'ep2kingdom','Tập 2', 12, 'Cuộc săn xác sống. Bắt đầu','1nejTzBNkuLJmsXcbHH_uxWnW5zAXusN0')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (15,'ep1cha','Tập 1', 13, 'Cuộc gặp gỡ định mệnh','1nejTzBNkuLJmsXcbHH_uxWnW5zAXusN0')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (16,'ep2cha','Tập 2', 13, 'Ấn tượng xấu và những chiêu trò của dân làng','1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW')";
        db.execSQL(sql);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static void loadData(){
        arrayListEpisode.clear();
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
    public void droptbEp(){
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql = "Drop table " + TABLE_NAME;
        db.execSQL(sql);
        db.close();
    }


}
