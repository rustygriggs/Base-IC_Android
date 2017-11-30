package edu.utah.cs4710.rusty.base_ic;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Rusty on 11/29/2017.
 */

public class SendHTTPService {
    private static SendHTTPService _Instance;

    public static SendHTTPService getInstance() {
        if (_Instance == null) {
            _Instance = new SendHTTPService();
        }
        return _Instance;
    }


    public static final String HTTP_POST = "POST";

    public static final String PROCESS_URL = "http://192.168.86.239/api/v1/process/zigbee";

    public void sendHTTPPostRequest (final String address, final String serviceId, final String serviceNumber, final String value) {
        // {"address":"address String","data":["serviceId string", "serviceNumber String", "value String"]}
        URL processURL = null;
        try {
            processURL = new URL(PROCESS_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //_gameInfoForNewGameReceivedListener.onGameInfoForNewGameReceived(false, null);
        }

        //Define task
        AsyncTask<URL, Integer, String> task = new AsyncTask<URL, Integer, String>() {
            @Override
            protected String doInBackground(URL... urls) {
                String responseBody = null;
                try {
                    URL url = urls[0];
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(HTTP_POST);
                    connection.setRequestProperty("Content-Type", "application/json");

                    connection.setDoOutput(true);
                    String str =  "{\"address\": \"" + address + "\",\"data\": [\"" + serviceId + "\",\" "
                            + serviceNumber + "\",\"" + value + "\"]}";

                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                    wr.write( str );
                    wr.flush();

                    InputStream inputStream = connection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    StringBuilder builder = new StringBuilder();
                    while (scanner.hasNext()) {
                        builder.append(scanner.nextLine());
                    }
                    responseBody= builder.toString();
                    Log.i("Response", responseBody);
                }
                catch (IOException e) {
                    e.printStackTrace();
//                    _gameInfoForNewGameReceivedListener.onGameInfoForNewGameReceived(false, null);
                }

//                Gson gson = new Gson();
//                Game game = gson.fromJson(responseBody, Game.class);

                return responseBody;
            }

//            @Override
//            protected void onPostExecute(Game game) {
//                super.onPostExecute(game);
//                _gameInfoForNewGameReceivedListener.onGameInfoForNewGameReceived(true, game);
//            }
        };
        task.execute(processURL);
    }
}
