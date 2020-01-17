package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhoneSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_setup);
        final Button btn =findViewById(R.id.btnPhnChng);
        final String s1="UNESI BROJ";
        final String s2="UKLONI BROJ";
        btn.setText(s1);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String btntxt=btn.getText().toString();
            if(btntxt.equals(s1)) {
                btn.setText(s2);
                btn.setBackgroundResource(R.drawable.dugmetaraaccent);
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(PhoneSetupActivity.this);
                builder.setTitle("PAŽNJA");
                builder.setMessage("DA LI SIGURNO ŽELITE DA UKLONITE BROJ?");
                builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn.setText(s1);
                        btn.setBackgroundResource(R.drawable.dugmetara);
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
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PhoneSetupActivity.this,TeamActivity.class);
        startActivity(intent);
    }


}
