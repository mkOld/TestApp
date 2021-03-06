package com.excilys.testapp.android.websocket.threads;

import android.content.Context;
import android.util.Log;

import com.excilys.testapp.android.utils.Location;
import com.excilys.testapp.android.websocket.client.Client;

import org.java_websocket.WebSocketImpl;

import java.net.URISyntaxException;

/**
 * Created by excilys on 13/06/14.
 */
public class ClientConnectionRunnable implements Runnable {
    private static final String TAG = Client.class.getSimpleName();
    private static boolean endOfClient = true; // when we want to stop the client
    private Client client;
    private Location location;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ClientConnectionRunnable(Location location, Context context) {
        Log.i(TAG, "Enterring default constructor ClientConnectionRunnable");
        this.location = location;
        endOfClient = true;
        try {
            WebSocketImpl.DEBUG = true;
            // the client to connect with webSocket to server
            client = new Client(location, context);

//            client.setLocation(location);
//            client.setContext(context);
        } catch (URISyntaxException e) {
            Log.e(TAG, "Error, catched URISyntaxException in default constructor ClientConnectionRunnable");
            e.printStackTrace();
        }
        Log.i(TAG, "Leaving default constructor ClientConnectionRunnable");
    }

    @Override
    public void run() {
        Log.i(TAG, "Enterring implemented run method in ClientConnectionRunnable");
        client.getWebSocketClient().connect();
        Log.i(TAG, "Connection to server : " + client.getWebSocketClient().getConnection());
        Log.i(TAG, "Enterring infinite loop implemented run method in ClientConnectionRunnable, listening...");

        while (endOfClient) {
        }

        client.getWebSocketClient().close();
    }
}
