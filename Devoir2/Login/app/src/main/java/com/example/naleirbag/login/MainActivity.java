package com.example.naleirbag.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static EditText usernameEt;
    EditText passwordEt;
    Button loginBtn;
    boolean firstTime=false;
    static String usr = "";
    static String pass = "";
    CheckBox checkBoxRemember;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this.getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usernameEt = (EditText) findViewById(R.id.login_username_text);
        passwordEt = (EditText) findViewById(R.id.login_password_text);

        final String[] users = {"ana", "ion", "dan"};
        final String[] passwords = {"123", "456", "789"};
        final ArrayList<User> usersData=new ArrayList<>();
        usersData.add(new User("Ana Popescu","begginer","Romania"));
        usersData.add(new User("Ion Popescu","begginer","Romania"));
        usersData.add(new User("Dan Popescu","advanced","Romania"));
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usr = usernameEt.getText().toString();
                pass = passwordEt.getText().toString();


                if (usr.length() == 0 || usr.matches("^\\s+$")
                        || pass.length() == 0 || pass.matches("^\\s+$")) {

                    Toast.makeText(
                            ctx, getResources().getText(
                                    R.string.insert_username),
                            Toast.LENGTH_LONG).show();
                    Toast.makeText(
                            ctx, getResources().getText(
                                    R.string.insert_password),
                            Toast.LENGTH_LONG).show();

                } else {


                    for (int i = 0; i < users.length; i++)
                        if (usr.matches(users[i]) & pass.matches(passwords[i])) {
                            Intent intent = new Intent(ctx, ProfileActivity.class);
                            String usrn = MainActivity.usernameEt.getText()
                                    .toString();
                            intent.putExtra("username", usersData.get(i).name);
                            intent.putExtra("country", usersData.get(i).country);
                            intent.putExtra("usertype", usersData.get(i).usertype);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ctx.startActivity(intent);
                            break;

                        } else {
                            Toast.makeText(
                                    ctx, "invalid username or password",
                                    Toast.LENGTH_LONG).show();
                        }
                }


            }


        });


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
