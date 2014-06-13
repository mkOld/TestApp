package com.excilys.testapp.android.websocket.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.excilys.testapp.android.utils.Location;
import com.excilys.testapp.android.websocket.mapper.JacksonMapper;
import com.excilys.testapp.app.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by hrandr on 13/06/14.
 */
//@EBean
public class Client{
    private static final String TAG = Client.class.getSimpleName();
    private JacksonMapper jacksonMapper = new JacksonMapper();
    private Location location;
    private WebSocketClient webSocketClient;

    //@ViewById(R.id.progressBarWaitingConnection)
    //ProgressBar progressBar;

    //@ViewById(R.id.infoConnection)
    //TextView textView;

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

    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    public void setWebSocketClient(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

//    /**
//     * Constructor where we do the connection to the server
//     * @param location
//     * @param context
//     */
//    public Client(Location location, Context context) throws URISyntaxException {
//        super(new URI(new StringBuilder("ws://").append(location.getiP()).append(":").append(location.getPort()).toString()), new Draft_10());
//        this.location = location;
//        this.context = context;
//    }

    public Client(Location location, Context context) throws URISyntaxException {
        Log.i(TAG, "Voila la location: " + location);
        webSocketClient = new WebSocketClient(new URI(new StringBuilder("ws://").append(location.getiP()).append(":").append(location.getPort()).toString()), new Draft_10()) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.d(TAG, "Enterring onOpen method in Client");
                this.send("GET connection : haja" + this);
                Log.d(TAG, "Leaving onOpen method in Client");
            }

            @Override
            //@UiThread
            public void onMessage(String message) {
                Log.d(TAG, "Enterring onMessage method in Client");
                Log.i(TAG, "Message received from server : " + message);
                String[] list = message.split("-");
                switch (message) {
                    case "OK":
                        Log.i(TAG, "Connection accepted");
                        //progressBar.setVisibility(View.GONE);
                        //textView.setText("Connection success!!!");
                        break;
                    default:
                        Log.e(TAG, "Message mal interprété par le client !");
                }
                Log.d(TAG, "Leaving onMessage method in Client");
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d(TAG, "Enterring onClose method in Client");
                Log.d(TAG, "Leaving onClose method in Client");
            }

            @Override
            public void onError(Exception ex) {
                Log.e(TAG, "Enterring onError method in Client" + ex.getMessage());

                Log.e(TAG, "Leaving onError method in Client");
            }
        };
    }



}
