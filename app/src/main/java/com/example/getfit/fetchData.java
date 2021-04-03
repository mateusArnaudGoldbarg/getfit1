package com.example.getfit;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataParsed = "";
    List<Parsed> parsed = null;
    Food food = null;
    String singleParsed="";
    JSONObject object;
    private String foodname;
    private int amount;

    public fetchData(String food, int amt){
        foodname = food;
        amount = amt;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            URL url = new URL("https://api.edamam.com/api/food-database/v2/parser?nutrition-type=logging&ingr="+foodname+"&app_id="+ "3a05913f" +"&app_key=" + "63848f04ae8749a91754aa7e5f9044bb");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }



            JSONObject JO = new JSONObject(data);


            String p = JO.getString("parsed");

            JSONArray Parsed = new JSONArray(p);

            JSONObject food = Parsed.getJSONObject(0);


            JSONObject ff = food.getJSONObject("food");

            JSONObject nutrients = ff.getJSONObject("nutrients");


            double kcal = (nutrients.getDouble("ENERC_KCAL"))*(amount/100.0);


            dataParsed = "You ingested " + kcal + " Kcal";


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } //catch (JSONException e) {
        catch (JSONException e) {
            e.printStackTrace();
        }
        //  e.printStackTrace();
        //}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);
    }
}
