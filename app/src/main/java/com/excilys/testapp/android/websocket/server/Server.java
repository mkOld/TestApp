package com.excilys.testapp.android.websocket.server;



import android.util.Log;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.net.InetSocketAddress;

import com.excilys.testapp.android.model.message.Message;
import com.excilys.testapp.android.service.message.MessageService;
import com.excilys.testapp.android.service.message.impl.MessageServiceImpl;
import com.excilys.testapp.android.websocket.Utils;
import com.excilys.testapp.android.websocket.mapper.JacksonMapper;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

/**
 * Created by excilys on 13/06/14.
 */
@EBean
public class Server extends WebSocketServer {
    private static final String TAG = Server.class.getSimpleName();
    private JacksonMapper jacksonMapper = new JacksonMapper();

    @Bean(MessageServiceImpl.class)
    MessageService messageService;

    public Server() {
        super(new InetSocketAddress(Utils.PORT));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Log.i(TAG, "Entering onOpen method in Server");

        Log.i(TAG, "New connection from a client : " + handshake.getResourceDescriptor());

        Log.i(TAG, "Leaving onOpen method in Server");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Log.i(TAG, "Entering onClose method in Server");
        Log.i(TAG, "Leaving onClose method in Server");
    }

    @Override
    public void onMessage(WebSocket conn, String message_com) {
        Log.i(TAG, "Entering onMessage method in Server");

        String[] list = message_com.trim().split("@-@");


        switch (list[0]) {
            case "NewMessage:":
                Message message = jacksonMapper.toJavaObject(list[1], Message.class);
                messageService.create(message);
                break;
            default:
                Log.i(TAG, "Impossible to decode the message");
                break;
        }

        Log.i(TAG, "Leaving onMessage method in Server");
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.e(TAG, "Entering onError method in Server");
        Log.e(TAG, "Leaving onError method in Server");
    }

}
