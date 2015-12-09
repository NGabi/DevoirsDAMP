package com.example.naleirbag.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginWithSharedPreferences extends AppCompatActivity implements View.OnClickListener {
    private static EditText usernameEt;
    private EditText passwordEt;
    private Button btnLogin;
    private CheckBox checkBox;
    private SharedPreferences loginPref;
    private SharedPreferences.Editor loginEditor;
    private static String usr = "", pass = "";

    private final static String USERNAME_KEY = "username";
    private final static String PASSWORD_KEY = "password";
    private final static String SAVED_KEY = "saved";
    private Intent logIntent;
    private boolean isSaved = false;
    Button loginBtn;
    boolean firstTime = false;
    ArrayList<User> usersData = new ArrayList<>();
    final String[] users = {"ana", "ion", "dan"};
    final String[] passwords = {"123", "456", "789"};


    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        isSaved = loginPref.getBoolean(SAVED_KEY, false);
        if (isSaved) {
            fillData();
        } else {
            clearFiels();
        }
        usr = loginPref.getString(USERNAME_KEY, "");
        if (isSaved) {
            fillData();
        }


    }

    private void init() {
        ctx = this.getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usernameEt = (EditText) findViewById(R.id.login_username_text);
        passwordEt = (EditText) findViewById(R.id.login_password_text);
        checkBox = (CheckBox) findViewById(R.id.checkBoxRemember);


        loginBtn = (Button) findViewById(R.id.login_btn);
        loginPref = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginEditor = loginPref.edit();
        loginBtn.setOnClickListener(this);
        usersData.add(new User("Ana Popescu", "begginer", "Romania"));
        usersData.add(new User("Ion Popescu", "begginer", "Romania"));
        usersData.add(new User("Dan Popescu", "advanced", "Romania"));


    }

    private void fillData() {
        usr = loginPref.getString(USERNAME_KEY, "");
        pass = loginPref.getString(PASSWORD_KEY, "");
        passwordEt.setText(pass);
        usernameEt.setText(usr);
        checkBox.setChecked(isSaved);
    }

    private boolean checkSaveOption(String usr, String pass) {
        if (checkBox.isChecked()) {
            isSaved = true;
            saveData(usr, pass);

        } else {
            loginEditor.clear();
            loginEditor.commit();
            isSaved = false;

        }
        return isSaved;
    }

    private void saveData(String usr, String pass) {
        Toast.makeText(
                ctx, "Username is saved",
                Toast.LENGTH_LONG).show();
        loginEditor.putString(USERNAME_KEY, usr);
        loginEditor.putString(PASSWORD_KEY, pass);
        loginEditor.putBoolean(SAVED_KEY, isSaved);
        loginEditor.commit();


    }

    private void clearFiels() {
        Toast.makeText(
                ctx, "Username is not saved",
                Toast.LENGTH_LONG).show();
        passwordEt.setText("");
        usernameEt.setText("");

    }


    private boolean checkUserValability(String usr, String pass) {
        if (usr.trim().length() == 0) {
            Toast.makeText(
                    ctx, getResources().getText(
                            R.string.insert_username),
                    Toast.LENGTH_LONG).show();
            usernameEt.requestFocus();
            return false;

        } else {
            if (pass.trim().length() == 0) {


                Toast.makeText(
                        ctx, getResources().getText(
                                R.string.insert_password),
                        Toast.LENGTH_LONG).show();
                passwordEt.requestFocus();
                return false;

            } else {
                for (int i = 0; i < users.length; i++)
                    if ((usr.matches(users[i]) & pass.matches(passwords[i]))) {
                        logIntent = new Intent(LoginWithSharedPreferences.this, ProfileActivity.class);
                        logIntent.putExtra("username", usersData.get(i).name);
                        logIntent.putExtra("country", usersData.get(i).country);
                        logIntent.putExtra("usertype", usersData.get(i).usertype);
                        Intent intent = new Intent(ctx, ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                        return true;
                    }else {
                        Toast.makeText(
                                ctx, "Invalid username or password",
                                Toast.LENGTH_LONG).show();
                        return false;
                    }
            }
        }

        return true;
    }







    @Override
    public void onClick(View view) {
       int id=view.getId();


            switch (id){

                case(R.id.login_btn):
                    usr = usernameEt.getText().toString();
                    pass = passwordEt.getText().toString();
                    checkSaveOption(usr, pass);
                    if(checkUserValability(usr,pass)){
                        Intent inte =new Intent(getBaseContext(),LoginService.class);
                        startService(inte);

                        startActivity(logIntent);
                        finish();
                    }




                    break;


        }
    }
}
