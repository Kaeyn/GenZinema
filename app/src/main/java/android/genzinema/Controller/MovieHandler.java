package android.genzinema.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.Movie;
import android.genzinema.Model.Style;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MovieHandler extends SQLiteOpenHelper {
    private Context context;
    static ArrayList<Movie> arrayListMovie = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/database/qlmv.db";
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


    public MovieHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
                "FOREIGN KEY("+IDGENREMOVIE_COL+") REFERENCES "+GenresHandler.TABLE_NAME+"("+GenresHandler.IDGENRE_COL+")," +
                "FOREIGN KEY("+IDSTYLEMOVIE_COL+") REFERENCES "+StyleHandler.TABLE_NAME+"("+StyleHandler.IDSTYLE_COL+")," +
                "PRIMARY KEY( "+IDMOVIE_COL+" ));";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (1,'JohnWaj','1','1','1S9Fj7wPhvFktzE5Pk4XWJ6ClLFRaadBW','Jon','Tony','2121','Phim rat hay.','overlord')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (2,'Wajigi','4','1','1ENAzcgYVihG8NHmeNDOrxh3mjzJLjD0r','Min','JonhnWajiz','2921','Phim danh cho anh JohnWaji.','sieunhan')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES (3,'Joji','2','2','1EUXzjIRJFniKTiHg9sW_T14eByhyCvcN','Jin','JohnATDR','221','Phim hay heo hut.','ttcs')";
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
        cursor.close(); // Close the cursor after use
        db.close();
        return movie;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void droptbMV(SQLiteDatabase db){
        db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql = "Drop table " + TABLE_NAME;
        db.execSQL(sql);
        db.close();
    }
    public ArrayList<Movie> getMovieByGenre(int genreId)
    {
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        for (Movie movie : arrayListMovie)
        {
            if(movie.getIdGenre()== genreId)
            {
                arrayList.add(movie);
            }
        }
        return arrayList;
    }
}
