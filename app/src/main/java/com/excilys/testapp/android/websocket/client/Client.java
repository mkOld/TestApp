package com.excilys.testapp.android.websocket.client;

import android.content.Context;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.excilys.testapp.android.utils.Location;
import com.excilys.testapp.android.websocket.mapper.JacksonMapper;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by hrandr on 13/06/14.
 */
public class Client extends WebSocketClient {
    private static final String TAG = Client.class.getSimpleName();
    private JacksonMapper jacksonMapper = new JacksonMapper();
    private Location location;



    private Context context;

    public JacksonMapper getJacksonMapper() {
        return jacksonMapper;
    }

    public void setJacksonMapper(JacksonMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Constructor where we do the connection to the server
     * @param location
     * @param context
     */
    public Client(Location location, Context context) throws URISyntaxException {
        super(new URI(new StringBuilder("ws://").append(location.getiP()).append(":").append(location.getPort()).toString()), new Draft_10());
        this.location = location;
        this.context = context;
        connect();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.d(TAG, "Enterring onOpen method in Client");
        this.send("Hello Server ! I'm a new client :  " + this);
        Log.d(TAG, "Leaving onOpen method in Client");
    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.d(TAG, "Enterring onClose method in Client");
        Log.d(TAG, "Leaving onClose method in Client");
    }

    @Override
    public void onError(Exception ex) {
        Log.e(TAG, "Enterring onError method in Client");
        Log.e(TAG, "Leaving onError method in Client");
    }

}
