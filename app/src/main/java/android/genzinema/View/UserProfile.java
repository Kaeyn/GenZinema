package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.genzinema.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    LinearLayout lineLay_edit,lineLay_notification,lineLay_list,
                    lineLay_setting,lineLay_account,lineLay_help;
    Button btn_logout;
    ImageButton imgBtn_return;
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
        lineLay_setting = findViewById(R.id.layout_setting);
        lineLay_account = findViewById(R.id.layout_account);
        lineLay_help = findViewById(R.id.layout_help);
        btn_logout = findViewById(R.id.btn_Logout);
        imgBtn_return = findViewById(R.id.imgBtn_return);
    }
    private void addEvent()
    {
        lineLay_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, UserProfile_Edit.class);
                startActivity(intent);
            }
        });
    }
}