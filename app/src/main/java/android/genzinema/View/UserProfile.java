package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.genzinema.Controller.UserHandler;
import android.genzinema.Model.User;
import android.os.Bundle;
import android.genzinema.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    Button btn_logout, btn_edit, btn_notification, btn_myList,btn_appInfo;
    TextView tv_UserName;
    ImageButton imgBtn_return;
    UserHandler userHandler;
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        addControl();
        addEvent();
    }

    private void addControl()
    {
        btn_edit = findViewById(R.id.btn_edit);
        btn_notification = findViewById(R.id.btn_notification);
        btn_myList = findViewById(R.id.btn_myList);
        btn_appInfo = findViewById(R.id.btn_appInfo);
        btn_logout = findViewById(R.id.btn_Logout);
        imgBtn_return = findViewById(R.id.imgBtn_return);
        tv_UserName = findViewById(R.id.tv_UserName);
    }
    private void addEvent()
    {
        userHandler = new UserHandler(this,UserHandler.DB_NAME,null,1);
        Intent intent = getIntent();
        user = userHandler.getUserByEmail(intent.getStringExtra("Email"));
        tv_UserName.setText(user.getDisplayName());

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, UserProfile_Edit.class);
                intent.putExtra("Email",user.getEmail());
                startActivity(intent);
            }
        });
        btn_myList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, Activity_Collections.class);
                intent.putExtra("Email",user.getEmail());
                startActivity(intent);
            }
        });
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserProfile.this, MainHome.class);
                intent1.putExtra("Email",user.getEmail());
                startActivity(intent1);
            }
        });
        imgBtn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserProfile.this, MainHome.class);
                intent1.putExtra("Email",user.getEmail());
                startActivity(intent1);

            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng xuất?");
        builder.setMessage("Bạn có muốn đăng xuất không!!");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent1 = new Intent(UserProfile.this, Login.class);
                startActivity(intent1);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}