package com.example.naleirbag.logcatexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Log.i(TAG, "onCreate()");
        final EditText addrText = (EditText)findViewById(R.id.location);
        final Button button = (Button)findViewById(R.id.mapButton);
        button.setOnClickListener(new View.OnClickListener() {
            // Called when user clicks the Show Map button
            public void onClick(View v) {
                try {
// Process text for network transmission
                    String address = addrText.getText().toString();
// convert the text to a geo valid URI
                    address = address.replace(' ', '+');
// Create Intent object for starting Google Maps application
                    Intent geoIntent = new Intent(
                            android.content.Intent.ACTION_VIEW, Uri
                            .parse("geo:44.4379849,25.9542096z?q=" + address+" ,Bucharest"));
// Use the Intent to start Google Maps application using
//Intent.ACTION_VIEW show some info that can be showen to the user: photo in galerry app, addres on maps
                    //ACTION_SEND - to share data with other apps
// the Uri contains data to that the action is  set on
                    //setDataAndType
                    //CATHEGORIE
                   // i.setType(audio)  audio,jpg,gif text... application/pdf

                    startActivity(geoIntent);
                } catch (Exception e) {
// Log any error messages to LogCat using Log.e()
                    Log.e(TAG, e.toString());
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
