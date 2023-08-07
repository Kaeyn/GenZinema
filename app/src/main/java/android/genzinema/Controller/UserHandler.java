package android.genzinema.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.genzinema.Model.User;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserHandler extends SQLiteOpenHelper {

    static ArrayList<User> arrayListUser = new ArrayList<>();
    public static final String DB_NAME = "qlmv";
    public static final String PATH = "/data/data/android.genzinema/files/database/qlmv.db";
    static final String TABLE_NAME = "User";
    static final String EMAIL_COL = "email";
    static final String DISPLAYNAME_COL = "display_name";
    static final String PASSWORD = "password";
    static final String PHONE = "phone";

    public UserHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABLE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = " CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
                EMAIL_COL+" TEXT NOT NULL UNIQUE, " +
                DISPLAYNAME_COL+" TEXT NOT NULL, " +
                PASSWORD +" TEXT NOT NULL, " +
                PHONE+" TEXT NOT NULL, " +
                "PRIMARY KEY( "+EMAIL_COL+" ));";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('1','CM','1', '0909090909')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('2','Tri','1', '0909090909')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('3','Tam','1', '0909090909')";
        db.execSQL(sql);
        sql = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('4','Tan','1', '0909090909')";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList<User> GetAllData(){
        LoadData();
        return arrayListUser;
    }
    public void LoadData(){
        arrayListUser = new ArrayList<User>();
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME, null);
        cursor.moveToFirst();
        do{
            User user = new User();
            user.setEmail(cursor.getString(0));
            user.setDisplayName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setPhone(cursor.getString(3));
            arrayListUser.add(user);
        }while (cursor.moveToNext());
        cursor.close(); // Close the cursor after use
        db.close();
    }

    public void InsertUser(String email, String pass){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sqlInsert = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('"+email+"','New Genzinemazer','"+pass+"', '000000000')";
        db.execSQL(sqlInsert);
        LoadData();
        db.close();
    }

    public User getUserByEmail(String email)
    {
        for (User user: arrayListUser)
        {
            if(user.getEmail().equals(email))
            {
                return user;
            }
        }
        return null;
    }

    public void UpdateUserProfile(String email,String name, String phone)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(DISPLAYNAME_COL,name);
        values.put(PHONE,phone);
        db.update(TABLE_NAME,values,EMAIL_COL+"= '"+email+"' ",null);
        db.close();
        LoadData();
    }
    public void UpdateUserPass(String email, String newPass)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(PASSWORD,newPass);
        db.update(TABLE_NAME,values,EMAIL_COL+" =? ",new String[]{String.valueOf(email)});
        db.close();
    }
}
