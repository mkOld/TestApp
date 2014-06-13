package com.excilys.testapp.android.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.excilys.testapp.android.utils.Location;
import com.excilys.testapp.android.websocket.threads.ClientConnectionRunnable;
import com.excilys.testapp.app.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EActivity(R.layout.activity_client_connection)
public class ClientConnectionActivity extends ActionBarActivity {

    private static final String TAG = ClientConnectionActivity.class.getSimpleName();
    private Thread thread;

    private boolean isFormatOK = false;
    private Location location;
    private Intent intent;

    @ViewById(R.id.infoConnection)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_connection);

    }

    @AfterViews
    public void getStateConnection(){
        intent = getIntent();
        location = intent.getParcelableExtra("com.excilys.testapp.android.utils.Location");
        if(location!=null)
            Log.e(TAG, "IP pour se connecter: " + location.getiP());
        else
            Log.e(TAG, "C'est null!!!");

        if(!location.validate())
            textView.setText("Problème de connection... L'IP et/ou le port sont mal renseigné...");
        else{
            // We do the connection
            ClientConnectionRunnable clientConnectionRunnable = new ClientConnectionRunnable(location, getApplicationContext());
            thread = new Thread(clientConnectionRunnable);
            thread.start(); // to run() in ClientConnectionRunnable that call Client.connect()

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.e(TAG, "Error join getInfoFromAdmin in MeetTournamentActivity");
            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client_connection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
