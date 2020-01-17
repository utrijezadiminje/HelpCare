package com.example.helpcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class TeamActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private static final String TAG = "TeamActivity";
    private static final String[] CALL_PERMISSIONS = {Manifest.permission.CALL_PHONE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_team);
        ImageView iv1 = findViewById(R.id.tel1);
        ImageView iv2 = findViewById(R.id.tel2);
        iv1.setClickable(true);
        iv2.setClickable(true);
        final Button button=findViewById(R.id.btnUI);
        final String broj1 = "+381604145650";
        final String broj2 = "+381691491011";
        final String nadji = "NAĐI TIM";
        final String izadji = "IZAĐI IZ TIMA";
        ImageView menjaj = findViewById(R.id.imgBrojPromeni);
        button.setText(nadji);
        super.onCreate(savedInstanceState);
        iv1.setClickable(true);
        iv2.setClickable(true);
        menjaj.setClickable(true);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             makePhoneCall(broj1);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             makePhoneCall(broj2);
            }
        });
        menjaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamActivity.this,PhoneSetupActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String btntxt=button.getText().toString();
            if(btntxt.equals(nadji)) {
                button.setText(izadji);
                button.setBackgroundResource(R.drawable.dugmetaraaccent);
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(TeamActivity.this);
                builder.setTitle("PAŽNJA");
                builder.setMessage("DA LI SIGURNO ŽELITE DA NAPUSTITE TIM?");
                builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button.setText(nadji);
                        button.setBackgroundResource(R.drawable.dugmetara);
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
            }

        });

    }
    private void makePhoneCall(@NonNull String number) {
        verifyPermissions();
        if (ContextCompat.checkSelfPermission(TeamActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TeamActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }


    }
    private void verifyPermissions(){
        Log.d(TAG, "verifyPermissions: Checking Permissions.");

        int permissionCallPhone = ActivityCompat.checkSelfPermission(TeamActivity.this, Manifest.permission.CALL_PHONE);
        if (permissionCallPhone != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    TeamActivity.this,
                    CALL_PERMISSIONS,
                    1
            );
        }

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(TeamActivity.this,MainActivity.class);
        startActivity(intent);
    }

}
