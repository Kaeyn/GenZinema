package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.genzinema.Controller.UserHandler;
import android.genzinema.Model.User;
import android.os.Bundle;
import android.genzinema.R;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class UserProfile_Edit extends AppCompatActivity {

    Button btn_UpdateImg, btn_Update, btn_UpdatePassword,btn_UpdateProfile;
    ImageButton imageBtn_return;
    UserHandler userHandler;
    boolean isProfile = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);
        addControl();
        addEvent();
    }
    private void addControl()
    {
        btn_UpdateImg = findViewById(R.id.btn_updateImg);
        btn_Update = findViewById(R.id.btn_update);
        btn_UpdatePassword = findViewById(R.id.btn_UpdatePassword);
        btn_UpdateProfile = findViewById(R.id.btn_UpdateProfile);
        imageBtn_return = findViewById(R.id.imgBtn_return);
    }
    private void addEvent()
    {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("Email",intent.getStringExtra("Email"));
        FragmentManager fm = getSupportFragmentManager();
        fm.setFragmentResult("UpdateProfileKey",bundle);
        loadFragment(new Fragment_UpdateProfile());
        btn_UpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isProfile){
                    isProfile = false;
                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();
                    bundle.putString("Email",intent.getStringExtra("Email"));
                    FragmentManager fm = getSupportFragmentManager();
                    fm.setFragmentResult("UpdatePasswordKey",bundle);
                    loadFragment(new Fragment_UpdatePassword());
                }
            }
        });
        btn_UpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isProfile){
                    isProfile = true;
                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();
                    bundle.putString("Email",intent.getStringExtra("Email"));
                    FragmentManager fm = getSupportFragmentManager();
                    fm.setFragmentResult("UpdateProfileKey",bundle);
                    loadFragment(new Fragment_UpdateProfile());
                }

            }
        });
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }
}