package android.genzinema.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.genzinema.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity_User_Profile_Edit extends AppCompatActivity {

    Button btn_Update, btn_UpdatePassword,btn_UpdateProfile;
    ImageButton imageBtn_return;
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

        btn_Update = findViewById(R.id.btn_update);
        btn_UpdatePassword = findViewById(R.id.btn_UpdatePassword);
        btn_UpdateProfile = findViewById(R.id.btn_UpdateProfile);
        imageBtn_return = findViewById(R.id.imgBtn_return);
    }
    private void addEvent()
    {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("email",intent.getStringExtra("email"));
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
                    bundle.putString("email",intent.getStringExtra("email"));
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
                    bundle.putString("email",intent.getStringExtra("email"));
                    FragmentManager fm = getSupportFragmentManager();
                    fm.setFragmentResult("UpdateProfileKey",bundle);
                    loadFragment(new Fragment_UpdateProfile());
                }

            }
        });
        imageBtn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Activity_User_Profile_Edit.this, Activity_User_Profile.class);
                intent1.putExtra("email",intent.getStringExtra("email"));
                startActivity(intent1);
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