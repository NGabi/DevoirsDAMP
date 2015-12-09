package com.example.ibagn.buildcoursecode;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CallsActivity extends Activity {

    private static final String TAG ="CallLog" ;
    ListView lv;
    TextView et_title, ID_tv,et_description;
    int numberRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calls_list);
        lv = (ListView)findViewById(R.id.listview);

        String[] requestedColumns ={
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };

        numberRows = getIntent().getIntExtra("number",0);


        CursorLoader loader =
                new CursorLoader(this,CallLog.Calls.CONTENT_URI,requestedColumns,null,null,"DATE DESC LIMIT "+numberRows);
       // "ROWID LIMIT 5"

        Cursor calls = loader.loadInBackground();
        String[] from={calls.getColumnName(0),calls.getColumnName(1),calls.getColumnName(2)};
        int[] to = new int[] {   R.id.call_id,R.id.ph_number,R.id.duration};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                CallsActivity.this, R.layout.call_patern, calls,
                from, to,0);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView number_tv = (TextView) view.findViewById(R.id.ph_number);


                Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number_tv.getText().toString()));
                try {
                    startActivity(in);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}







