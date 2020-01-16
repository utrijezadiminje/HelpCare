package com.example.helpcare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class Database extends AsyncTask<String,Void,String> {
    String rezultat = "";

    Context context;
    AlertDialog alertDialog;

    Database (Context ctx) { context = ctx; }

    @Override
    protected String doInBackground(String... params) {
        String cmd = params[0];
        String login_url = "https://webdatabaseandroid.000webhostapp.com/login.php";
        String register_url = "https://webdatabaseandroid.000webhostapp.com/register.php";
        if(cmd.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String line = "";
                while((line = bufferedReader.readLine())!= null) rezultat += line;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(cmd.equals("register")) {
            try {
                String username = params[1];
                String password = params[2];
                String type = params[3];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String line="";
                while((line = bufferedReader.readLine())!= null) rezultat += line;

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rezultat;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String rezultat) {
        if(rezultat.equals("Ltrue")) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        else if(rezultat.equals("Lfalseu")) {
            CharSequence text = "KORISNIK NIJE PRONAĐEN";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(rezultat.equals("Lfalsep")) {
            CharSequence text = "POGREŠNA LOZINKA";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(rezultat.equals("Rtrue")) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
        else if(rezultat.equals("RfalseU")) {
            CharSequence text = "KORISNIČKO IME JE ZAUZETO";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(rezultat.equals("Rfalse")) {
            CharSequence text = "DOŠLO JE DO GREŠKE";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            CharSequence text = "NEMA INTERNETA";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}