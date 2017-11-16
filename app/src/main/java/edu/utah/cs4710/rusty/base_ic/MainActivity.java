package edu.utah.cs4710.rusty.base_ic;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    boolean hexButtonClicked = false;
    boolean toggleButtonClicked = false;
    boolean rangeButtonClicked = false;
    String PI_URL = "http://10.0.0.115/api/v1/peripherals/";
    private PeripheralListReceivedListener _peripheralListReceivedListener = null;

    /**
     * listener for when the list of games is received
     */
    public interface PeripheralListReceivedListener {
        void onPeripheralListReceived(boolean success, PeripheralResponse peripheralResponse);
    }
    public void setPeripheralListReceivedListener(PeripheralListReceivedListener listener) {
        _peripheralListReceivedListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootLayout);

        Button hexButton = new Button(this);
        hexButton.setText("hex");
        hexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showHexButton = new Intent();
                showHexButton.setClass(view.getContext(), HexActivity.class);
                view.getContext().startActivity(showHexButton);
            }
        });
        Button toggleButton = new Button(this);
        toggleButton.setText("toggle");
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showToggleButton = new Intent();
                showToggleButton.setClass(view.getContext(), ToggleActivity.class);
                view.getContext().startActivity(showToggleButton);
            }
        });

        Button rangeButton = new Button(this);
        rangeButton.setText("range");
        rangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRangeButton = new Intent();
                showRangeButton.setClass(view.getContext(), RangeActivity.class);
                view.getContext().startActivity(showRangeButton);
            }
        });

        rootLayout.addView(hexButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rootLayout.addView(toggleButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        rootLayout.addView(rangeButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        PeripheralResponse peripheralResponse = getPeripheralsList();
        List<String> peripheralNameList = new ArrayList<>();
        for (Peripheral p : peripheralResponse.peripherals){
            peripheralNameList.add(p.name);
        }
        TextView peripheralListView = new TextView(this);
        peripheralListView.setText(peripheralNameList.toString());
        rootLayout.addView(peripheralListView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

    }

    private PeripheralResponse getPeripheralsList() {
        URL peripheralsListURL = null;
        try {
            peripheralsListURL = new URL(PI_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Define task
        AsyncTask<URL, Integer, PeripheralResponse> task = new AsyncTask<URL, Integer, PeripheralResponse>() {
            @Override
            protected PeripheralResponse doInBackground(URL... urls) {
                String responseBody = null;
                try {
                    URL url = urls[0];
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    InputStream inputStream = connection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    StringBuilder builder = new StringBuilder();
                    while (scanner.hasNext()) {
                        builder.append(scanner.nextLine());
                    }
                    responseBody = builder.toString();
                    Log.i("Response", responseBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();
                PeripheralResponse peripheralResponse = gson.fromJson(responseBody, PeripheralResponse.class);

                return peripheralResponse;

            }

            @Override
            protected void onPostExecute(PeripheralResponse peripheralResponse) {
                super.onPostExecute(peripheralResponse);
                //It might not be a good idea to have a global list like this with all the
                //multi-threadedness and response times. listeners are a better option I think.
                //_allGames = gameSummaries;
                _peripheralListReceivedListener.onPeripheralListReceived(true, peripheralResponse);
            }
        };
        task.execute(peripheralsListURL);

    }

//    @Override
//    public void onClick(View view) {
////        if (hexButtonClicked) {
////            Intent showHexButton = new Intent();
////            showHexButton.setClass(this, HexActivity.class);
////            startActivity(showHexButton);
////        }
//        //startActivityForResult(showHexButton, PICK_PAINT_REQUEST);
//    }
}
