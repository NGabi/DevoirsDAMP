package com.example.ibagn.buildcoursecode;

import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="CallLog" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] requestedColumns ={
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };
        CursorLoader loader =
                new CursorLoader(this,CallLog.Calls.CONTENT_URI,requestedColumns,null,null,null);
        Cursor calls = loader.loadInBackground();
        for(int i = 0;i<10;i++){
            if(calls.moveToNext()==false)
                break;
            Log.d(TAG,"Logging the record:"+i);
            Log.d(TAG,"Id:"+calls.getString(0));
            Log.d(TAG,"Number:"+calls.getString(1));
            Log.d(TAG,"Duration:"+calls.getString(2));

        }
        calls.close();
    }
}
