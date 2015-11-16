package com.example.ibagn.addnotessqlite;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {

    Button addmem_bt;
    ListView lv;
    SQLController dbcon;
    TextView et_title, ID_tv,et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbcon = new SQLController(this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addmem_bt = (Button) findViewById(R.id.addmem_bt_id);
        lv = (ListView) findViewById(R.id.memberList_id);

        // onClickListiner for addmember Button
        addmem_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(MainActivity.this, AddNote.class);
                startActivity(add_mem);
            }
        });

        // Attach The Data From DataBase Into ListView Using Crusor Adapter
        Cursor cursor = dbcon.readData();
        String[] from = new String[] { DBhelper.NOTE_ID, DBhelper.NOTE_TITLE, DBhelper.NOTE_DESCRITION,DBhelper.NOTE_DATE };
        int[] to = new int[] { R.id.member_id, R.id.note_title, R.id.note_content,R.id.date};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.note_patern, cursor, from, to);

        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        // OnCLickListiner For List Items
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //ID_tv = (TextView) view.findViewById(R.id.member_id);
                et_title = (TextView) view.findViewById(R.id.note_title);
                et_description = (TextView) view.findViewById(R.id.note_content);


               //String ID_val = ID_tv.getText().toString();
                String ID_val=""+id;
                String title = et_title.getText().toString();
                System.out.print("Titlu"+title+"");
                String description = et_description.getText().toString();



                Intent modify_intent = new Intent(getApplicationContext(),
                        ModifyNote.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("description", description);
                DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());
                modify_intent.putExtra("date", date);


                modify_intent.putExtra("ID", ID_val);
                startActivity(modify_intent);
            }
        });

    }

}