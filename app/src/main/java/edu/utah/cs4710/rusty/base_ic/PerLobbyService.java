package edu.utah.cs4710.rusty.base_ic;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rusty on 11/25/2017.
 */

public class PerLobbyService {
    private PeripheralListReceivedListener _peripheralListReceivedListener = null;
    private static PerLobbyService _Instance;
    String PI_URL_PERIPHERAL_LIST = "http://192.168.100.100/api/v1/peripherals/";


    public static PerLobbyService getInstance() {
        if (_Instance == null) {
            _Instance = new PerLobbyService();
        }
        return _Instance;
    }
    /**
     * listener for when the list of peripherals is received
     */
    public interface PeripheralListReceivedListener {
        void onPeripheralListReceived(boolean success, List<Peripheral> peripherals);
    }
    public void setPeripheralListReceivedListener(PeripheralListReceivedListener listener) {
        _peripheralListReceivedListener = listener;
    }

    public void getPeripheralsList() {
        URL peripheralsListURL = null;
        try {
            peripheralsListURL = new URL(PI_URL_PERIPHERAL_LIST);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            _peripheralListReceivedListener.onPeripheralListReceived(false, null);
        }

        //Define task
        AsyncTask<URL, Integer, PeripheralResponse> task = new AsyncTask<URL, Integer, PeripheralResponse>() {
            @Override
            protected PeripheralResponse doInBackground(URL... urls) {
                String responseBody = null;
                try {
                    URL url = urls[0];
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //connection.setRequestMethod("GET");

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
                    _peripheralListReceivedListener.onPeripheralListReceived(false, null);
                }

                Gson gson = new Gson();
                PeripheralResponse peripheralResponse = gson.fromJson(responseBody, PeripheralResponse.class);

                return peripheralResponse;

            }

            @Override
            protected void onPostExecute(PeripheralResponse peripheralResponse) {
                super.onPostExecute(peripheralResponse);

                List<Peripheral> peripheralList = peripheralResponse.peripherals;

                _peripheralListReceivedListener.onPeripheralListReceived(true, peripheralList);
            }
        };
        task.execute(peripheralsListURL);
    }
}
