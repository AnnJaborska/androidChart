package com.example.indicators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    LineChart mpLineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpLineChart= (LineChart) findViewById(R.id.line_chart);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Wartosc firmy Snap na gieldzie NYSE");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
    }

//        Button button =findViewById(R.id.button);
//        final TextView textView= findViewById(R.id.textView3);


        private ArrayList<Entry> dataValues1()
        {
            ArrayList<Entry> dataVals = new ArrayList<Entry>();




            try {
                URL url = new URL("https://intraday.worldtradingdata.com/api/v1/intraday?symbol=SNAP&interval=1&range=1&api_token=BnSmosz4kRmySElvbriaqgVYEP6grGtIw5x7eAYgHXoTRfhmPyOKtCAqDd84");
                URLConnection conn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine, output = "";
                while ((inputLine = in.readLine()) != null) {
                    output += inputLine;
                }
                //         System.out.println("eygtrydrudtjx btrhdtrh");
                //         Thread.sleep(20);
                //         System.out.println("obudzony");
                JSONObject newArr = new JSONObject(output);
                //         System.out.println(output);
//                String name = (String) newArr.get("name");

                JSONObject history = newArr.getJSONObject("intraday");

                Iterator x = history.keys();

                //       tablica czasu
                JSONArray datearray = new JSONArray();

                //         ArrayList<Entry> dataVals = new ArrayList<Entry>();
                List<String> lista = new ArrayList<String>();
                while (x.hasNext()) {
//                    key to moja data
                    String key = (String) x.next();
                    //        	 System.out.println(key);

                    lista.add(key);
                    datearray.put(history.get(key));



                }
                List<Integer> lista2 = new ArrayList<Integer>();
                System.out.println(datearray);
                for (int i = 0; i < datearray.length(); i++) {
                    JSONObject obiekt = datearray.getJSONObject(i);
                    //        	 System.out.println(obiekt);
                    //        	 Iterator key = obiekt.keys();
                    String volume = (String) obiekt.get("close");
                    int z = Integer.parseInt(volume);
                    lista2.add(z);
                    //        	 Teraz dodac do tablicy i na wykres zwizualizowac



                }
                for (int s = 0; s < datearray.length(); s++){
//
                    int b = lista2.get(s);
                    dataVals.add(new Entry(s,b));

                }




            } catch (Exception e) {
                System.out.println("jhf");
            }
            return dataVals;



}
}




