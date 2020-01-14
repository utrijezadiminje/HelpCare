package com.example.helpcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Spinner Spinner;
    private EditText Username;
    private EditText Password;
    private EditText Password2;
    private Button Register;
    private TextView LogIn;

    Context context;
    RegisterActivity (Context ctx) { context = ctx; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = findViewById(R.id.etUsername);
        Password=findViewById(R.id.etPassword1);
        Password2=findViewById(R.id.etPassword2);
        Spinner =findViewById(R.id.spinner);
        Register=findViewById(R.id.btnRegister);
        LogIn=findViewById(R.id.login);
        String username = Username.getText().toString();
        String pass1 = Password.getText().toString();
        String pass2 = Password2.getText().toString();
        if (pass1.equals(pass2)) {
            val(username, pass1);
        }
        else {
            CharSequence text = "LOZINKE SE NE POKLAPAJU";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    private void val(String username, String password){
        String cmd = "register";
        Database database = new Database(this);
        database.execute(cmd, username, password);
    }
}
