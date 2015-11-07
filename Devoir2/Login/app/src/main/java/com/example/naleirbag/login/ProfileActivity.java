package com.example.naleirbag.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by gabrielan on 10/28/2015.
 */
public class ProfileActivity extends AppCompatActivity {
    TextView name,country,usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);
        name=(TextView)findViewById(R.id.name);
        country=(TextView)findViewById(R.id.country);
        usertype=(TextView)findViewById(R.id.usertype);


        Intent intent = getIntent();

        name.setText(intent.getStringExtra("username"));
        country.setText(intent.getStringExtra("country"));
        usertype.setText(intent.getStringExtra("usertype"));



    }
}
