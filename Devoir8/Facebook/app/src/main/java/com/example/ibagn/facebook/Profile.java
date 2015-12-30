package com.example.ibagn.facebook;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class Profile extends AppCompatActivity {
    TextView studyTxt,workTxt,locationTxt;
    Button btn_friends,btn_follow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        studyTxt = (TextView)findViewById(R.id.txt2);
        workTxt = (TextView)findViewById(R.id.txt1);
        btn_friends = (Button)findViewById(R.id.btn1);
        btn_follow = (Button)findViewById(R.id.btn2);
        locationTxt = (TextView)findViewById(R.id.txt3);
        Typeface studyFont = Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");
        studyTxt.setTypeface(studyFont);
        workTxt.setTypeface(studyFont);
        locationTxt.setTypeface(studyFont);

        btn_friends.setTypeface(studyFont);
        btn_follow.setTypeface(studyFont);
        getSupportActionBar().setTitle(R.string.usr_name);
        getSupportActionBar().setDisplayShowTitleEnabled(true);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.otion, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                return true;

            case R.id.action_search:

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    }

