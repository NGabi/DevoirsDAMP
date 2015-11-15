package com.example.naleirbag.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gabrielan on 10/28/2015.
 */
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name,country,usertype;
    Button logout;
    private  SharedPreferences loginPref;
    private  SharedPreferences.Editor loginEditor;
    private  static String usr = "", pass = "";
    private final static String USERNAME_KEY = "username";
    private final static String SAVED_KEY = "saved";
    private boolean isSaved = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        isSaved = loginPref.getBoolean(SAVED_KEY,false);
        usr = loginPref.getString(USERNAME_KEY,"");

        logout.setOnClickListener(this);
        // logout clear fields







    }
    private void init(){
        loginPref = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        Log.e("LogPrefs", loginPref.getString(USERNAME_KEY, "") + "");
        Log.e("LogPrefs", loginPref.getBoolean(SAVED_KEY, false) + "");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);

        logout = new Button(this);

        logout.setId(0);

        logout.setText("Logout");
        logout.setBackgroundColor(Color.CYAN);
        ViewGroup.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,Gravity.RIGHT);

        toolbar.addView(logout, lp);



        name=(TextView)findViewById(R.id.name);
        country=(TextView)findViewById(R.id.country);
        usertype=(TextView)findViewById(R.id.usertype);
        Intent intent = getIntent();

        name.setText(intent.getStringExtra("username"));
        country.setText(intent.getStringExtra("country"));
        usertype.setText(intent.getStringExtra("usertype"));
    }





    @Override
    public void onClick(View view) {
        if(view.getId() == 0){
            Toast.makeText(
                    this.getApplicationContext(), "Button logout",
                    Toast.LENGTH_LONG).show();
            Intent logoutIntent = new Intent(ProfileActivity.this,LoginWithSharedPreferences.class);
            startActivity(logoutIntent);
            finish();

        }
    }


}
