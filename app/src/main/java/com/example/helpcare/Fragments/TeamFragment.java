package com.example.helpcare.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helpcare.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class TeamFragment extends Fragment {

    private static final int REQUEST_CALL = 1;
    private static final String TAG = "TeamActivity";
    private static final String[] CALL_PERMISSIONS = {Manifest.permission.CALL_PHONE };
    private static String cmd="find";
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private TextView name1;
    private TextView name2;
    private TextView name3;
    private TextView class1;
    private TextView class2;
    private TextView class3;

    private Button teambtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_team, container, false);


        teambtn=view.findViewById(R.id.btnUI);
        teambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.putBoolean("team",true);
                editor.commit();

                showTeam();
            }
        });
        img1=view.findViewById(R.id.img1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall("0604145650");
            }
        });
        img2=view.findViewById(R.id.img2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall("0655048091");
            }
        });
        img3=view.findViewById(R.id.img3);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall("066425253");
            }
        });

        name1=view.findViewById(R.id.twTim1);
        name2=view.findViewById(R.id.twTim2);
        name3=view.findViewById(R.id.twTim3);

        class1=view.findViewById(R.id.twUSR1);
        class2=view.findViewById(R.id.twUSR2);
        class3=view.findViewById(R.id.twUSR3);


        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("preferences",MODE_PRIVATE);
        boolean team=sharedPreferences.getBoolean("team",false);
        if(team)
            showInstant();
       /* SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("team",false);
        editor.commit();*/


        return view;
    }

    private void showTeam()
    {
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                img1.setImageResource(R.drawable.covek_od_celika);
                img1.setVisibility(View.VISIBLE);
                name1.setText("Nikola Mitrović");
                class1.setText("Medicinski tehničar");
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 200);

        Runnable progressRunnable1 = new Runnable() {
            @Override
            public void run() {
                img2.setImageResource(R.drawable.dimi_celavi);
                img2.setVisibility(View.VISIBLE);
                name2.setText("Dimitrije Stanojević");
                class2.setText("Lekar opšte prakse");
            }
        };
        Handler pdCanceller1 = new Handler();
        pdCanceller1.postDelayed(progressRunnable1, 600);

        Runnable progressRunnable2 = new Runnable() {
            @Override
            public void run() {
                img3.setImageResource(R.drawable.nina);
                img3.setVisibility(View.VISIBLE);
                name3.setText("Nina Stanković");
                class3.setText("Administrator");
            }
        };
        Handler pdCanceller2 = new Handler();
        pdCanceller2.postDelayed(progressRunnable2, 800);


    }

    private void showInstant()
    {
        img1.setImageResource(R.drawable.covek_od_celika);
        img1.setVisibility(View.VISIBLE);
        name1.setText("Nikola Mitrović");
        class1.setText("Medicinski tehničar");

        img2.setImageResource(R.drawable.dimi_celavi);
        img2.setVisibility(View.VISIBLE);
        name2.setText("Dimitrije Stanojević");
        class2.setText("Lekar opšte prakse");

        img3.setImageResource(R.drawable.nina);
        img3.setVisibility(View.VISIBLE);
        name3.setText("Nina Stanković");
        class3.setText("Administrator");
    }

    private void makePhoneCall(@NonNull String number) {
        verifyPermissions();
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }


    }
    private void verifyPermissions(){
        Log.d(TAG, "verifyPermissions: Checking Permissions.");

        int permissionCallPhone = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
        if (permissionCallPhone != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    CALL_PERMISSIONS,
                    1
            );
        }

    }
}
