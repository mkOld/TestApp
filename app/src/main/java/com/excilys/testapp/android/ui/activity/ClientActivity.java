package com.excilys.testapp.android.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.excilys.testapp.android.utils.Location;
import com.excilys.testapp.app.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_client)
public class ClientActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client, menu);
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

    @Click(R.id.connectToServer)
    public void goToConnect(){

        EditText editText = (EditText) findViewById(R.id.editTextIP);
        String ip = editText.getText().toString();

        editText = (EditText) findViewById(R.id.editTextPort);
        String port = editText.getText().toString();

        Location location = new Location(ip, port);

        Intent intent = new Intent(getApplicationContext(), ClientConnectionActivity_.class);
        intent.putExtra("com.excilys.testapp.android.utils.Location", location);
        startActivity(intent);
        finish();
    }

}
