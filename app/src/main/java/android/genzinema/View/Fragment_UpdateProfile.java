package android.genzinema.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.genzinema.Controller.UserHandler;
import android.genzinema.Model.User;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.genzinema.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_UpdateProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_UpdateProfile extends Fragment {
    EditText edtUsername, edtEmail,edtSDT;

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

    public Fragment_UpdateProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_UpdateProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_UpdateProfile newInstance(String param1, String param2) {
        Fragment_UpdateProfile fragment = new Fragment_UpdateProfile();
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
        userHandler = new UserHandler(getContext(),UserHandler.DB_NAME,null,1);
        fm.setFragmentResultListener("UpdateProfileKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Intent intent = getActivity().getIntent();
                User user = userHandler.getUserByEmail(intent.getStringExtra("email"));
                Log.d("USEREMAIL", intent.getStringExtra("email"));
                edtEmail.setText(user.getEmail());
                edtUsername.setText(user.getDisplayName());
                edtSDT.setText(user.getPhone());
                email = intent.getStringExtra("email");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__update_profile, container, false);
        edtUsername = view.findViewById(R.id.edt_UserName);
        edtEmail =  view.findViewById(R.id.edt_Email);
        edtSDT =  view.findViewById(R.id.edt_SDT);
        btnUpdate = view.findViewById(R.id.btn_update);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsername.getText().toString().equals("")|| edtSDT.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!edtUsername.getText().toString().equals(userHandler.getUserByEmail(email).getDisplayName())
                        || !edtSDT.getText().toString().equals(userHandler.getUserByEmail(email).getPhone())) {
                    userHandler.UpdateUserProfile(edtEmail.getText().toString(),edtUsername.getText().toString(),edtSDT.getText().toString());
                    Intent intent = new Intent(getContext(), UserProfile.class);
                    intent.putExtra("email",edtEmail.getText().toString());
                    startActivity(intent);
                    Toast.makeText(getContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Vui lòng cập nhật thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}