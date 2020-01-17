package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn1;
    private ImageButton btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrefUtils.saveLog(true,this);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.imageButton);
        btn2 = findViewById(R.id.imageButton2);
        btn3 = findViewById(R.id.btnZadatak);
        final String zadatak=getString(R.string.task);
        final String empty="";
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TeamActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btntxt=btn3.getText().toString();
                Database database = new Database(this, getApplicationContext());
                switch(btntxt) {
                  /*  case zadatak:
                        btn3.setText("");
                        if(cmd.equals("find")) {
                            String username = PrefUtils.getUser(getApplicationContext());
                            database.execute(cmd, username);
                            btn3.setText(empty);
                            btn3.setBackgroundResource(R.drawable.dugmetaraaccent);
                        }
                        else
                        {
                            final ProgressDialog myDialog = new ProgressDialog(MainActivity.this);
                            myDialog.setMessage("UČITAVANJE...");
                            myDialog.setCancelable(false);
                            myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "PREKINI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    btn3.setText(zadatak);

                                }
                            });
                            myDialog.show();
                        }
                        break;*/
                    case empty:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("PAŽNJA");
                        builder.setMessage("DA LI SIGURNO ŽELITE DA NAPUSTITE TIM?");
                        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn3.setText(zadatak);
                                btn3.setBackgroundResource(R.drawable.dugmetara);
                            }
                        });
                        builder.setNegativeButton("NE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        btn3.setText(zadatak);
                        break;
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("PAŽNJA");
        builder.setMessage("DA LI SIGURNO ŽELITE DA IZAĐETE?");
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                finish();
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

