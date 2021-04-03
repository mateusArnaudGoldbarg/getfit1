package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    Button click;
    public static TextView data;
    EditText texto,amount;


    //private Kcal foody;
    //private String dado;
    //private final String app_id = "3a05913f";
    //private final String key = "63848f04ae8749a91754aa7e5f9044bb";
    //private String food = "apple";
    //private String url = "https://api.edamam.com/api/food-database/v2/parser?nutrition-type=logging&ingr="+food+"&app_id=" + app_id + "&app_key="+key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d("PEIDO","ASDAKSDJALSKJDALSKDJKALSJDASKLJDLKASJDKLSAJDKLASJDLKJASD");

        click = (Button) findViewById(R.id.button);
        data = (TextView) findViewById(R.id.fecheddata);
        texto = (EditText) findViewById(R.id.foodEnter);
        amount = (EditText) findViewById(R.id.amount);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String foodName = texto.getText().toString();
                String amt = amount.getText().toString();
                int a = Integer.parseInt(amt);
                fetchData process = new fetchData(foodName,a);
                process.execute();

            }
        });





    }



/*

    private class DownloadFilesTask extends AsyncTask<URL, Integer, String> {
        @Override
        protected String doInBackground(URL... urls) {
            try {
                // Convert the String url to a URL object which will allow a connection to be established
                URL urlObj = new URL(url);
                // Open a Connection to the remote site.  This will send the HTTP Get request
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                // Open an input stream to read the response.  I prefer the BufferedReader since it provides
                // the readLine function.
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // When concatenating strings read from the remote server, we get some performance improvement by
                // using the StringBuilder class.
                StringBuilder data = new StringBuilder();
                String line;
                // Keep reading from the remote server until we get a null which indicates the connection has
                // been closed.
                do {
                    line = reader.readLine();  // Read the next line
                    if (line != null) {
                        data.append(line); // Append will concatenate the new string just read into the StringBuilder
                    }
                }
                while (line != null);
                // Convert the StringBuilder into a regular String for use by the calling function
                dado = data.toString();
                return data.toString();
            }
            catch (IOException ioe) {
                // Print out an error if something went wrong and return a null response string
                System.out.println("Error reading HTTP Response: "+ioe);
                return null;
            }
        }

    }
    */



}