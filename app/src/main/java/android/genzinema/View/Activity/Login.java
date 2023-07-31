package android.genzinema.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.Handler.UserHandler;
import android.genzinema.Model.User;
import android.os.Bundle;
import android.genzinema.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    //Controls
    EditText edtUsername,edtPass;
    Button btnLogin;

    //Database
    UserHandler userHandler;
    SQLiteDatabase db;
    ArrayList<User> arrayListUser = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControl();
        if(loadUserTableOnDataBase()){
            addEvent();
        }
    }

    private void addControl()
    {
        edtUsername = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }
    private boolean loadUserTableOnDataBase(){
        File internalStorageDir = getFilesDir();
        // Append the folder name "database" to the internal storage directory path
        String folderPath = internalStorageDir.getAbsolutePath() + "/database";
        // Create the folder
        File databaseFolder = new File(folderPath);
        if (databaseFolder.exists() || databaseFolder.mkdirs()) {
            userHandler = new UserHandler(this,UserHandler.DB_NAME,null,1);
            userHandler.onCreate(db);
            arrayListUser = userHandler.GetAllData();
            return true;
        }
        return false;
        // Failed to create the folder
    }
    private void addEvent()
    {
//        Intent intent = new Intent(Login.this, MainHome.class);
//        intent.putExtra("email","1");
//        startActivity(intent);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check = false;
                for (User user: arrayListUser) {
                    if(edtUsername.getText().toString().equals(user.getEmail()) &&
                            edtPass.getText().toString().equals(user.getPassword())){
                                Intent intent = new Intent(Login.this, MainHome.class);
                                intent.putExtra("email",user.getEmail());
                                startActivity(intent);
                                check = true;
                                break;
                    }
                }
                if(!check){
                    Toast.makeText(Login.this,"Tên đăng nhập hoặc mật khẩu không đúng!",Toast.LENGTH_SHORT).show();
                    edtUsername.setText("");
                    edtPass.setText("");
                }
            }
        });
    }
}