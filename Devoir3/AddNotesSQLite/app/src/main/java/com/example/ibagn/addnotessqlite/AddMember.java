package com.example.ibagn.addnotessqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class AddMember extends Activity implements View.OnClickListener {
    EditText et;
    Button add_bt, read_bt;
    SQLController dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_member);
        et = (EditText) findViewById(R.id.member_et_id);
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
                String title = et.getText().toString();
                String description = et.getText().toString();
                String date = et.getText().toString();

                dbcon.insertData(title,description,date);
                //flag claer top eliminates activity from stack
                Intent main = new Intent(AddMember.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }
    }

}
