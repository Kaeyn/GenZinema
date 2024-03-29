package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.genzinema.Controller.UserHandler;
import android.genzinema.Model.User;
import android.os.Bundle;
import android.genzinema.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Activity_Login extends AppCompatActivity {

    EditText edtUsername,edtPass;
    Button btnLogin;
    UserHandler userHandler;
    SQLiteDatabase db;
    Boolean check = false;
    ArrayList<User> arrayListUser = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControl();
        File internalStorageDir = getFilesDir();

        // Append the folder name "database" to the internal storage directory path
        String folderPath = internalStorageDir.getAbsolutePath() + "/database";

        // Create the folder
        File databaseFolder = new File(folderPath);
        if (databaseFolder.exists()) {
            userHandler = new UserHandler(this,UserHandler.DB_NAME,null,1);
            userHandler.onCreate(db);
        } else {
            if (databaseFolder.mkdirs()) {
                userHandler = new UserHandler(this,UserHandler.DB_NAME,null,1);
                userHandler.onCreate(db);
            } else {
                // Failed to create the folder
            }
        }
        arrayListUser = userHandler.GetAllData();
        addEvent();
    }

    private void addControl()
    {
        edtUsername = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }
    private void addEvent()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (User user: arrayListUser) {
                    if(edtUsername.getText().toString().equals(user.getEmail()) &&
                            edtPass.getText().toString().equals(user.getPassword())){
                                Intent intent = new Intent(Activity_Login.this, Activity_Home.class);
                                intent.putExtra("email",user.getEmail());
                                startActivity(intent);
                                check = true;
                                break;
                    }
                }
                if(check == false)
                {
                    Toast.makeText(Activity_Login.this,"Tên đăng nhập hoặc mật khẩu không đúng!",Toast.LENGTH_SHORT).show();
                    edtUsername.setText("");
                    edtPass.setText("");
                }
            }
        });
    }
}