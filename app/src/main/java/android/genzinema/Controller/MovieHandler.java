package android.genzinema.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.Ep;
import android.genzinema.Model.Movie;
import android.genzinema.Model.Style;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class MovieHandler extends SQLiteOpenHelper {
    private Context context;
    static ArrayList<Movie> arrayListMovie = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/files/database/qlmv.db";
    static final String TABLE_NAME = "Movies";
    static final String IDMOVIE_COL = "movie_id";
    static final String IDSTYLEMOVIE_COL = "style_id";
    static final String IDGENREMOVIE_COL = "genre_id";
    static final String NAMEMOVIE_COL = "movie_name";
    static final String AUTHORMOVIE_COL = "authors";
    static final String ACTORMOVIE_COL = "actors";
    static final String YEARMOVIE_COL = "year_produce";
    static final String DETAILMOVIE_COL = "detail";
    static final String TRAILERMOVIE_COL = "trailer_url";
    static final String THUMBNAILMOVIE_COL = "thumbnail";
    static final String VIDEOMOVIE_COL = "video_url";





    public MovieHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABLE_NAME, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        droptbMV();
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = " CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
                IDMOVIE_COL+" INTEGER NOT NULL UNIQUE, " +
                NAMEMOVIE_COL+" TEXT NOT NULL, " +
                IDGENREMOVIE_COL+" INTEGER NOT NULL, " +
                IDSTYLEMOVIE_COL+" INTEGER NOT NULL, " +
                TRAILERMOVIE_COL+" TEXT NOT NULL, " +
                ACTORMOVIE_COL+" TEXT NOT NULL, " +
                AUTHORMOVIE_COL+" TEXT NOT NULL, " +
                YEARMOVIE_COL+" TEXT NOT NULL, " +
                DETAILMOVIE_COL+" TEXT NOT NULL, " +
                THUMBNAILMOVIE_COL+" TEXT NOT NULL, " +
                VIDEOMOVIE_COL+" TEXT NOT NULL, " +
                "FOREIGN KEY("+IDGENREMOVIE_COL+") REFERENCES "+GenresHandler.TABLE_NAME+"("+GenresHandler.IDGENRE_COL+")," +
                "FOREIGN KEY("+IDSTYLEMOVIE_COL+") REFERENCES "+StyleHandler.TABLE_NAME+"("+StyleHandler.IDSTYLE_COL+")," +
                "PRIMARY KEY( "+IDMOVIE_COL+" ));";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (1,'Overlord','5','1','1tazr67-wERTdQ9tCdnJBiK3-P_ZqWJU0','Albedo, Titan, Victim, Coctyus','Trí Tony','2019','Cốt truyện anime sẽ đưa khán giả đến năm 2138 trong tương lai, khi khoa học công nghệ phát triển vượt bậc và ngành game thực tế ảo đang nở rộ hơn bao giờ hết. Yggdrasil, một game online vô cùng phổ biến thời gian đó bỗng dưng bị đóng cửa đột ngột','overlord','1tazr67-wERTdQ9tCdnJBiK3-P_ZqWJU0')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (2,'Yinan','2','1','19nUvhiA3YeoHsT-mQSPVtxaMh_3CRV8F','Min Assung, Dook Hen, Dan Hen','JonhnWajiz','2008','Phim kinh dị không dành cho trẻ em dưới 16+.','yinan','19nUvhiA3YeoHsT-mQSPVtxaMh_3CRV8F')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (3,'Thất nghiệp chuyển sinh','5','2','16pmiD1vUO1mpNWU85vTgdsZ_fYiu4Ks4','Albedo, Titan, Victim, Coctyus','JohnATDR','2021','Một tên NEET 34 tuổi đã bị đuổi khỏi nhà sau cái chết của gia đình hắn. Hắn ngăn chặn một nhóm người thiếu niên ra khỏi một chiếc xe tải đang chạy và đã đẩy một người trong nhóm người đó ra thành công trước khi hắn chết.','ttcs','16pmiD1vUO1mpNWU85vTgdsZ_fYiu4Ks4')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (4,'Doraemon','5','1','1uAJ_UfmUVGDLyN9q3r6ElP5_2ESiEd_U','Doraemon, Nobita, Chaien, Shizuka, Suneo','ODA','2000','Các câu chuyện trong Doraemon thường có một công thức chung, đó là xoay quanh những rắc rối hay xảy ra với cậu bé Nobita học lớp bốn, nhân vật chính thứ nhì của bộ truyện.','doraemon','1uAJ_UfmUVGDLyN9q3r6ElP5_2ESiEd_U')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (5,'Siêu nhân','1','2','1UQdmP460mYAZKXw-UAamjCwZJhTPWWU9','Min Assung, Dook Hen, Dan Hen','JohnATDR','2010','Phim dành cho mọi lứa tuổi, lành mạnh cho trẻ nhỏ','sieunhan','1UQdmP460mYAZKXw-UAamjCwZJhTPWWU9')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (6,'Hậu duệ mặt trời','3','2','1-rsqefaW117LwMVB3mG5mmIMVsS3wZTe','Soong Joong Ki, Song Hye Kyo, Jin Goo, Kim Ji Won','JohnATDR','2018','Hậu duệ mặt trời (Hangul: 태양의 후예; RR: Taeyangui Huye) là một bộ phim truyền hình Hàn Quốc năm 2016 có sự tham gia của Song Joong-ki, Song Hye-kyo, Jin Goo và Kim Ji-won.Phim được phát sóng trên kênh KBS2 từ ngày 24 tháng 2 đến ngày 14 tháng 4 năm 2016 cho 16 tập','haudemattroi','1-rsqefaW117LwMVB3mG5mmIMVsS3wZTe')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (7,'FapTV','4','1','19nUvhiA3YeoHsT-mQSPVtxaMh_3CRV8F','Vinh Râu, Ribi Sachi, Thái Vũ, Phương','Đức Trần','2005','Phim hài hước dành cho gia đình.','faptv','19nUvhiA3YeoHsT-mQSPVtxaMh_3CRV8F')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (8,'Bố già','3','1','1UQdmP460mYAZKXw-UAamjCwZJhTPWWU9','Trấn Thành, Lê Giang, Tuấn Trần, Lan Phương, Hương Giang','Trấn Thành','2021','Bố già (tiếng Anh: The Godfather) là một bộ phim hình sự sản xuất năm 1972 dựa theo tiểu thuyết cùng tên của nhà văn Mario Puzo và do Francis Ford Coppola đạo diễn.','bogia','1UQdmP460mYAZKXw-UAamjCwZJhTPWWU9')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (9,'Hậu duệ mặt trời2','3','2','1-rsqefaW117LwMVB3mG5mmIMVsS3wZTe','Soong Joong Ki, Song Hye Kyo, Jin Goo, Kim Ji Won','JohnATDR','2018','Hậu duệ mặt trời (Hangul: 태양의 후예; RR: Taeyangui Huye) là một bộ phim truyền hình Hàn Quốc năm 2016 có sự tham gia của Song Joong-ki, Song Hye-kyo, Jin Goo và Kim Ji-won.Phim được phát sóng trên kênh KBS2 từ ngày 24 tháng 2 đến ngày 14 tháng 4 năm 2016 cho 16 tập','ttcs','1-rsqefaW117LwMVB3mG5mmIMVsS3wZTe')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (10,'Hậu duệ mặt trời3','3','2','1-rsqefaW117LwMVB3mG5mmIMVsS3wZTe','Soong Joong Ki, Song Hye Kyo, Jin Goo, Kim Ji Won','JohnATDR','2018','Hậu duệ mặt trời (Hangul: 태양의 후예; RR: Taeyangui Huye) là một bộ phim truyền hình Hàn Quốc năm 2016 có sự tham gia của Song Joong-ki, Song Hye-kyo, Jin Goo và Kim Ji-won.Phim được phát sóng trên kênh KBS2 từ ngày 24 tháng 2 đến ngày 14 tháng 4 năm 2016 cho 16 tập','ttcs','1-rsqefaW117LwMVB3mG5mmIMVsS3wZTe')";
        db.execSQL(sql);
        db.close();
    }

    public Movie GetMovieByID(int ID){
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+IDMOVIE_COL+" = "+ID, null);
        cursor.moveToFirst();
        Movie movie = new Movie();
        movie.setIdMV(cursor.getInt(0));
        movie.setNameMovie(cursor.getString(1));
        movie.setIdGenre(cursor.getInt(2));
        movie.setIdType(cursor.getInt(3));
        movie.setUrlTrailer(cursor.getString(4));
        movie.setActors(cursor.getString(5));
        movie.setAuthors(cursor.getString(6));
        movie.setYearProduce(cursor.getString(7));
        movie.setDetail(cursor.getString(8));
        movie.setIdThumbnails(context.getResources().getIdentifier(cursor.getString(9),"drawable","android.genzinema"));
        movie.setUrlVideo(cursor.getString(10));
        cursor.close();
        db.close();
        return movie;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void droptbMV(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql = "Drop table " + TABLE_NAME;
        db.execSQL(sql);
        db.close();
    }

    public void loadData(){
        arrayListMovie.clear();
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            Movie movie = new Movie();
            movie.setIdMV(cursor.getInt(0));
            movie.setNameMovie(cursor.getString(1));
            movie.setIdGenre(cursor.getInt(2));
            movie.setIdType(cursor.getInt(3));
            movie.setUrlTrailer(cursor.getString(4));
            movie.setActors(cursor.getString(5));
            movie.setAuthors(cursor.getString(6));
            movie.setYearProduce(cursor.getString(7));
            movie.setDetail(cursor.getString(8));
            movie.setIdThumbnails(context.getResources().getIdentifier(cursor.getString(9), "drawable", "android.genzinema"));
            movie.setUrlVideo(cursor.getString(10));
            arrayListMovie.add(movie);
        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
    }


    public ArrayList<Movie> searchData(String strSearch){
        arrayListMovie.clear();
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME +" WHERE UPPER(" + NAMEMOVIE_COL + ") LIKE UPPER('%"+strSearch+"%')", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            do {
                Movie movie = new Movie();
                movie.setIdMV(cursor.getInt(0));
                movie.setNameMovie(cursor.getString(1));
                movie.setIdGenre(cursor.getInt(2));
                movie.setIdType(cursor.getInt(3));
                movie.setUrlTrailer(cursor.getString(4));
                movie.setActors(cursor.getString(5));
                movie.setAuthors(cursor.getString(6));
                movie.setYearProduce(cursor.getString(7));
                movie.setDetail(cursor.getString(8));
                movie.setIdThumbnails(context.getResources().getIdentifier(cursor.getString(9), "drawable", "android.genzinema"));
                arrayListMovie.add(movie);
            }while (cursor.moveToNext());
        }
        else{
            return null;
        }

        cursor.close(); // Close the cursor after use
        db.close();
        return arrayListMovie;
    }


    public void loadTop10Movie(){
        arrayListMovie.clear();
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME + " LIMIT 10", null);
        cursor.moveToFirst();
        do {
            Movie movie = new Movie();
            movie.setIdMV(cursor.getInt(0));
            movie.setNameMovie(cursor.getString(1));
            movie.setIdGenre(cursor.getInt(2));
            movie.setIdType(cursor.getInt(3));
            movie.setUrlTrailer(cursor.getString(4));
            movie.setActors(cursor.getString(5));
            movie.setAuthors(cursor.getString(6));
            movie.setYearProduce(cursor.getString(7));
            movie.setDetail(cursor.getString(8));
            movie.setIdThumbnails(context.getResources().getIdentifier(cursor.getString(9), "drawable", "android.genzinema"));
            movie.setUrlVideo(cursor.getString(10));
            arrayListMovie.add(movie);
        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
    }



    public ArrayList<Movie> GetNewestMovie(){
        ArrayList<Movie> tempArrayList = new ArrayList<>();
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME + " ORDER BY "+IDMOVIE_COL+" DESC LIMIT 2", null);
        cursor.moveToFirst();
        do {
            Movie movie = new Movie();
            movie.setIdMV(cursor.getInt(0));
            movie.setNameMovie(cursor.getString(1));
            movie.setIdGenre(cursor.getInt(2));
            movie.setIdType(cursor.getInt(3));
            movie.setUrlTrailer(cursor.getString(4));
            movie.setActors(cursor.getString(5));
            movie.setAuthors(cursor.getString(6));
            movie.setYearProduce(cursor.getString(7));
            movie.setDetail(cursor.getString(8));
            movie.setIdThumbnails(context.getResources().getIdentifier(cursor.getString(9), "drawable", "android.genzinema"));
            movie.setUrlVideo(cursor.getString(10));
            tempArrayList.add(movie);
        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
        if(tempArrayList.size() > 0){
            return tempArrayList;
        }
        return null;
    }

    public ArrayList<Movie> GetCollectMVBy(Integer IDGenre, Integer IDStyle) {
        loadData();
        ArrayList<Movie> arrayList = new ArrayList<>();
        for (Movie movie : arrayListMovie) {
            Integer idgenre = movie.getIdGenre();
            Integer idstyle = movie.getIdType();
            if (idgenre.intValue() == IDGenre && idstyle.intValue() == IDStyle  ) {
                arrayList.add(movie);
            }
        }
        return arrayList;
    }

    public ArrayList<Movie> GetSimilarMVBy(Integer IDGenre) {
        loadData();
        ArrayList<Movie> arrayList = new ArrayList<>();
        for (Movie movie : arrayListMovie) {
            Integer idgenre = movie.getIdGenre();
            if (idgenre.intValue() == IDGenre) {
                arrayList.add(movie);
            }
        }
        return arrayList;
    }


    public ArrayList<Movie> GetTop10Movie() {
        loadTop10Movie();
        ArrayList<Movie> arrayList = new ArrayList<>();
        for (Movie movie : arrayListMovie) {
            arrayList.add(movie);
        }
        return arrayList;
    }

    public ArrayList<Movie> getAllMovie(){
        loadData();
        return arrayListMovie;
    }

    public ArrayList<Movie> GetGoodMovie() {
        loadData();
        ArrayList<Movie> arrayList = new ArrayList<>();
        Random random = new Random();
        int randomMovie = random.nextInt(arrayListMovie.size() - 1) + 1;
        for (Movie movie : arrayListMovie) {
            if (movie.getIdMV() == randomMovie){
                arrayList.add(movie);
            }
        }
        return arrayList;
    }

    public Movie GetRecommendedMovie() {
        loadData();
        Movie movie = new Movie();
        Random random = new Random();
        int randomMovie = random.nextInt(arrayListMovie.size() - 1) + 1;
        for (Movie m : arrayListMovie) {
            if (m.getIdMV() == randomMovie){
                movie = m;
            }
        }
        return movie;
    }
    public ArrayList<Movie> getMoviesByGenre(int genreId) {
        loadData();
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        for (Movie movie : arrayListMovie) {
//            Log.d("me",String.valueOf(movie.getIdGenre() == genreId)+" "+movie.getIdGenre());
            if (movie.getIdGenre() == genreId) {
                arrayList.add(movie);
            }
        }
        return arrayList;
    }

    public ArrayList<Movie> getMoviesByMovie(int genreId, int idType) {
        loadData();
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        for (Movie movie : arrayListMovie) {
//            Log.d("me",String.valueOf(movie.getIdGenre() == genreId)+" "+movie.getIdGenre());
            if (movie.getIdType() == idType && movie.getIdGenre() == genreId) {
                arrayList.add(movie);
            }
        }
        return arrayList;
    }


}
