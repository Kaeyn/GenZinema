package android.genzinema.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.Genres;
import android.genzinema.Model.Style;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StyleHandler extends SQLiteOpenHelper {
    static ArrayList<Style> arrayListStyle = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/database/qlmv.db";
    static final String TABLE_NAME = "Style_Movie";
    static final String IDSTYLE_COL = "style_id";
    static final String NAMESTYLE_COL = "style_name";
    public StyleHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = " CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " ( " +
                IDSTYLE_COL + " INTEGER NOT NULL UNIQUE, "+
                NAMESTYLE_COL + " TEXT NOT NULL UNIQUE, " +
                " PRIMARY KEY( " +IDSTYLE_COL+ ")) ";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('1','Movie')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('2','Series')";
        db.execSQL(sql);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static ArrayList<Style>loadData(){
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from Style_Movie", null);
        cursor.moveToFirst();
        do{
            Style style = new Style();
            style.setIdStyle(cursor.getInt(0));
            style.setNameStyle(cursor.getString(1));
            arrayListStyle.add(style);

        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
        return arrayListStyle;
    }
}
