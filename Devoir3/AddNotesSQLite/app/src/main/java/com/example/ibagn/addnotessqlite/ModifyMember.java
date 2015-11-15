package com.example.ibagn.addnotessqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class ModifyMember extends Activity implements View.OnClickListener {

    EditText et;
    Button edit_bt, delete_bt;

    long member_id;
    String title, description, date;

    SQLController dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_member);

        dbcon = new SQLController(this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        et = (EditText) findViewById(R.id.edit_mem_id);
        edit_bt = (Button) findViewById(R.id.update_bt_id);
        delete_bt = (Button) findViewById(R.id.delete_bt_id);

        Intent i = getIntent();
        String memberID = i.getStringExtra("memberID");
        String title = i.getStringExtra("memberName");
        String description = i.getStringExtra("memberName");
        String date = i.getStringExtra("memberName");

        member_id = Long.parseLong(memberID);

        et.setText(title);

        edit_bt.setOnClickListener(this);
        delete_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.update_bt_id:
                String memName_upd = et.getText().toString();
                dbcon.updateData(member_id, memName_upd,description,date);
                this.returnHome();
                break;

            case R.id.delete_bt_id:
                dbcon.deleteData(member_id);
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