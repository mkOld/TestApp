package com.excilys.testapp.android.ui.activity;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.excilys.testapp.app.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientConnectionActivity extends ActionBarActivity {

    private String ipAdress;
    private String portNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_connection);
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

    private void validateIP(String s) {
        String patternIP = "^((([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5]))[.]" +
                "(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5]))[.]" +
                "(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5]))[.]" +
                "(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5])))$";
        Pattern pattern = Pattern.compile(patternIP);
        Matcher matcher = pattern.matcher(s);

        // if it's an IP address format
        if (matcher.find()) {
            ipAdress = s;

            getInfoFromAdmin(s);
        } else {
            Toast.makeText(getApplicationContext(), "This is not an IP adress...", Toast.LENGTH_LONG).show();
        }
    }

}
