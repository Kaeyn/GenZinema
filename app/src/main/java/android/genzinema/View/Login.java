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

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText edtUsername,edtPass;
    Button btnLogin;
    UserHandler userHandler;
    SQLiteDatabase db;
    ArrayList<User> arrayListUser = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControl();
        userHandler = new UserHandler(this,UserHandler.DB_NAME,null,1);
        userHandler.onCreate(db);
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
                                Intent intent = new Intent(Login.this, MainHome.class);
                                intent.putExtra("Email",user.getEmail());
//                                intent.putExtra("DisplayName",user.getDisplayName());
//                                intent.putExtra("Phone",user.getPhone());
//                                intent.putExtra("Password",user.getPassword());
                                startActivity(intent);
                                break;
                    }
                }

            }
        });
    }
}