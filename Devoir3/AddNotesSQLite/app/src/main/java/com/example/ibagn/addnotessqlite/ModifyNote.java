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

public class ModifyNote extends Activity implements View.OnClickListener {

    EditText et;
    Button edit_bt, delete_bt;

    long note_id;


    SQLController dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_note);

        dbcon = new SQLController(this);
        try {
            dbcon.open();

        } catch (SQLException e) {
            e.printStackTrace();
        }









        edit_bt = (Button) findViewById(R.id.update_bt_id);
        delete_bt = (Button) findViewById(R.id.delete_bt_id);
        edit_bt.setOnClickListener(this);
        delete_bt.setOnClickListener(this);
        et = (EditText) findViewById(R.id.edit_mem_id);


        Intent i = getIntent();
        String noteID = i.getStringExtra("ID");
        note_id = Long.parseLong(noteID);
        //String title = i.getStringExtra("title");









        et.setText( i.getStringExtra("description"));

        edit_bt.setOnClickListener(this);
        delete_bt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.update_bt_id:

                DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());
                String description = et.getText().toString();
                String title="";
                if( description.trim().length()!=0){

                     title = description.split(" ")[0];
                }

                dbcon.updateData(note_id,title,description,date);
                this.returnHome();
                break;

            case R.id.delete_bt_id:
                dbcon.deleteData(note_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }

}