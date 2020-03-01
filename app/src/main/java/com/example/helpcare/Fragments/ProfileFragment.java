package com.example.helpcare.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helpcare.LoginActivity;
import com.example.helpcare.PasswordActivity;
import com.example.helpcare.PrefUtils;
import com.example.helpcare.R;
import com.example.helpcare.UserActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private EditText name;
    private Button password;
    private TextView logOut;
    private Button changeUsername;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_user, container, false);


        name=view.findViewById(R.id.editText);
        password = view.findViewById(R.id.button2);
        logOut=view.findViewById(R.id.logOut);
        name.requestFocus();

        String Username = PrefUtils.getUser(getContext());
        name.setText(Username);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        InputMethodManager imm = (InputMethodManager )getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        changeUsername=view.findViewById(R.id.button);

        final String changeUser=changeUsername.getText().toString();
        imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User1 = name.getText().toString();
                String User2 = PrefUtils.getUser(getContext());
                if (User1.equals(User2)) {
                    CharSequence text = "UPIŠITE NOVO IME";
                    Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
                    toast.show();

                } else{
                    String cmd = "newname";
                  /*  Database database = new Database(this,getApplicationContext());
                    database.execute(cmd, User2, User1);*/
                    PrefUtils.saveUser(User1, getContext());
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PasswordActivity.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("PAŽNJA");
                builder.setMessage("DA LI SIGURNO ŽELITE DA SE ODJAVITE?");
                builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Logout();
                    }
                });
                builder.setNegativeButton("NE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        return view;
    }

    public void Logout()
    {
        PrefUtils.saveLog(false,getContext());
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        CharSequence text = "USPEŠNO STE SE IZLOGOVALI";
        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
