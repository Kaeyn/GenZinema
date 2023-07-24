package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.genzinema.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class UserProfile_Edit extends AppCompatActivity {

    Button btnUpdateImg, btnBack,btnUpdate;
    ImageButton imageBtn_return;
    EditText edtUsername, edtEmail,edtSDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);
        addControl();
        addEvent();
    }
    private void addControl()
    {
        btnUpdateImg = findViewById(R.id.btn_updateImg);
        btnBack = findViewById(R.id.btn_back);
        btnUpdate = findViewById(R.id.btn_update);
        imageBtn_return = findViewById(R.id.imgBtn_return);
        edtUsername = findViewById(R.id.edt_UserName);
        edtEmail = findViewById(R.id.edt_Email);
        edtSDT = findViewById(R.id.edt_SDT);
    }
    private void addEvent()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile_Edit.this, UserProfile.class);
                startActivity(intent);
            }
        });
        imageBtn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile_Edit.this, UserProfile.class);
                startActivity(intent);
            }
        });
    }
}