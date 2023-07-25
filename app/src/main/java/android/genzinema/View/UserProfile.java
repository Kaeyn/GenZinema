package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;

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

    LinearLayout lineLay_edit,lineLay_notification,lineLay_list,lineLay_appInfo;
    Button btn_logout;
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
        lineLay_edit = findViewById(R.id.layout_edit);
        lineLay_notification = findViewById(R.id.layout_notification);
        lineLay_list = findViewById(R.id.layout_list);
        lineLay_appInfo = findViewById(R.id.layout_appInfo);
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

        lineLay_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, UserProfile_Edit.class);
                intent.putExtra("Email",user.getEmail());
                startActivity(intent);
            }
        });
        lineLay_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfile.this, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }
}