package com.example.ibagn.addnotessqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNote extends Activity implements View.OnClickListener {
    EditText et_title,et_description,et_date;
    Button add_bt;
    SQLController dbcon;

    @Override

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        et_title = (EditText) findViewById(R.id.et_note_title);
        et_description = (EditText) findViewById(R.id.et_note_content);

        add_bt = (Button) findViewById(R.id.add_bt_id);

        dbcon = new SQLController(this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        add_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_bt_id:
                String description = et_description.getText().toString();
                String title = et_title.getText().toString();
                if(title.trim().length()== 0 & description.trim().length()!=0){

                    title = description.split(" ")[0];
                }

                DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());



                dbcon.insertData(title,description,date);
                //flag claer top eliminates activity from stack
                Intent main = new Intent(AddNote.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }
    }

}
