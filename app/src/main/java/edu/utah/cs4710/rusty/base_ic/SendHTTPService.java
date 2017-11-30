package edu.utah.cs4710.rusty.base_ic;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rusty on 11/29/2017.
 */

public class SendHTTPService {

    String processURL = "http://192.168.86.239/api/v1/process/zigbee";

    public void sendHTTPPostRequest (String address, String serviceId, String serviceNumber, String value) {
        // {"address":"address String","data":["serviceId string", "serviceNumber String", "value String"]}
        URL createGameURL = null;
        try {
            createGameURL = new URL(processURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //_gameInfoForNewGameReceivedListener.onGameInfoForNewGameReceived(false, null);
        }
    }
}
