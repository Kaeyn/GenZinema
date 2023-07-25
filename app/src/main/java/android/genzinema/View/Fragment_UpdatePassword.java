package android.genzinema.View;

import android.content.Intent;
import android.genzinema.Controller.UserHandler;
import android.genzinema.Model.User;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_UpdatePassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_UpdatePassword extends Fragment {
    EditText edtOldPass, edtNewPass, edtNewPass2;
    Button btnUpdate;
    UserHandler userHandler;
    String email;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_UpdatePassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_UpdatePassword.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_UpdatePassword newInstance(String param1, String param2) {
        Fragment_UpdatePassword fragment = new Fragment_UpdatePassword();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResultListener("UpdatePasswordKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                email = result.getString("Email");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__update_password, container, false);
        edtOldPass =  view.findViewById(R.id.edt_oldPass);
        edtNewPass = view.findViewById(R.id.edt_newPass);
        edtNewPass2 =  view.findViewById(R.id.edt_newPass2);
        btnUpdate = view.findViewById(R.id.btn_update);
        userHandler = new UserHandler(getContext(),UserHandler.DB_NAME,null,1);

        String oldPass = edtOldPass.getText().toString();
        String newPass = edtNewPass.getText().toString();
        String confirmPass = edtNewPass2.getText().toString();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPass.equals("")|| newPass.equals("") || confirmPass.equals("")) {
                    Toast.makeText(getContext(), "Nhap dung hoac mat acc", Toast.LENGTH_SHORT).show();
                } else if (!oldPass.equals(userHandler.getUserByEmail(email).getPassword()) && !newPass.equals(oldPass) && !newPass.equals(confirmPass)) {
                    userHandler.UpdateUserPass(edtOldPass.getText().toString(), edtNewPass.getText().toString());
                    Intent intent = new Intent(getContext(), UserProfile.class);
                    intent.putExtra("Email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Mật khẩu xác nhận không khớp !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}