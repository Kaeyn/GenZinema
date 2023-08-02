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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Register extends AppCompatActivity {

    EditText edtRUsername,edtRPass, edtRConfirmPass;

    TextView textToLogin;

    Button btnRegis;

    UserHandler userHandler;
    SQLiteDatabase db;
    ArrayList<User> arrayListUser = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControl();
        userHandler = new UserHandler(this,UserHandler.DB_NAME,null,1);
        userHandler.onCreate(db);
        arrayListUser = userHandler.GetAllData();
        addEvent();
    }

    private void addControl()
    {
        edtRUsername = findViewById(R.id.edt_resUsername);
        edtRPass = findViewById(R.id.edt_resPassword);
        edtRConfirmPass = findViewById(R.id.edt_resConfirmPassword);
        btnRegis = findViewById(R.id.btn_Regis);
        textToLogin = findViewById(R.id.txttoLogin);
    }
    private void addEvent(){
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListUser = userHandler.GetAllData();
                if(edtRUsername.getText().length() == 0 || edtRConfirmPass.getText().length() == 0 || edtRPass.getText().length() == 0){
                    Toast.makeText(Activity_Register.this,"Thông tin không được để trống!",Toast.LENGTH_SHORT).show();
                }else{
                    if(!isEmailValid(edtRUsername.getText().toString())){
                        Toast.makeText(Activity_Register.this,"Email không hợp lệ!",Toast.LENGTH_SHORT).show();
                    }else{
                        boolean RegisCheck = true;
                        int counter = 0;
                        for (User user: arrayListUser) {
                            if(edtRUsername.getText().toString().equals(user.getEmail())){
                                RegisCheck = false;
                                break;
                            }else{
                                counter += 1;
                            }
                        }
                        if(!edtRPass.getText().toString().equals(edtRConfirmPass.getText().toString())){
                            Toast.makeText(Activity_Register.this,"Mật khẩu xác nhận lại không đúng!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(counter == arrayListUser.size()){
                            userHandler.InsertUser(edtRUsername.getText().toString(), edtRPass.getText().toString());
                            Toast.makeText(Activity_Register.this,"Đăng kí thành công!",Toast.LENGTH_SHORT).show();
                            edtRUsername.setText("");
                            edtRPass.setText("");
                            edtRConfirmPass.setText("");
                            RegisCheck = true;
                        }
                        if(!RegisCheck){
                            Toast.makeText(Activity_Register.this,"Email đã tồn tại!",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
        textToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public boolean isEmailValid(String email) {
        return email.contains("@gmail.com");
    }
}