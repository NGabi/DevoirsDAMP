package com.example.ibagn.calendarprovidermeds;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.GregorianCalendar;


public class MainActivity extends Activity {


    String MY_ACCOUNT_NAME = "myaccount";
    String eventID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button qry = (Button) findViewById(R.id.querybut);
        Button add = (Button) findViewById(R.id.function);
        ContentValues values = new ContentValues();
        values.put(
                CalendarContract.Calendars.ACCOUNT_NAME,MY_ACCOUNT_NAME
        );
        values.put(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL);
        values.put(
                CalendarContract.Calendars.NAME,
                "Medication Calendar");
        values.put(
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                "Medication Calendar");
        values.put(
                CalendarContract.Calendars.CALENDAR_COLOR,
                0xffff0000);
        values.put(
                CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
                CalendarContract.Calendars.CAL_ACCESS_OWNER);
        values.put(
                CalendarContract.Calendars.OWNER_ACCOUNT,
                "ana.exemplu@googlemail.com");
        values.put(
                CalendarContract.Calendars.CALENDAR_TIME_ZONE,
                "Europe/Berlin");
        values.put(
                CalendarContract.Calendars.SYNC_EVENTS,
                1);
        Uri.Builder builder =
                CalendarContract.Calendars.CONTENT_URI.buildUpon();
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_NAME,
                "com.ana");
        builder.appendQueryParameter(
                CalendarContract.Calendars.ACCOUNT_TYPE,
                CalendarContract.ACCOUNT_TYPE_LOCAL);
        builder.appendQueryParameter(
                CalendarContract.CALLER_IS_SYNCADAPTER,
                "true");
        Uri uri =
                getContentResolver().insert(builder.build(), values);
        eventID = uri.getLastPathSegment();
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                addEvent();
            }
        });

        qry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                query();
            }
        });
    }

    public void query() {
        String[] projection = {CalendarContract.Events._ID,
                CalendarContract.Events.TITLE};
        // Get a Cursor over the Events Provider.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        Cursor cursor = getContentResolver().query(
                CalendarContract.Events.CONTENT_URI, projection, CalendarContract.Calendars.VISIBLE + " = 1", null,
                CalendarContract.Calendars._ID);
        // Get the index of the columns.
        int nameIdx = cursor
                .getColumnIndexOrThrow(CalendarContract.Events.TITLE);
        int idIdx = cursor.getColumnIndexOrThrow(CalendarContract.Events._ID);
        // Initialize the result set.
        String[] result = new String[cursor.getCount()];
        // Iterate over the result Cursor.
        while (cursor.moveToNext()) {
            // Extract the name.
            String name = cursor.getString(nameIdx);
            // Extract the unique ID.
            String id = cursor.getString(idIdx);
            result[cursor.getPosition()] = name + "(" + id + ")";
            Toast.makeText(this, name + "(" + id + ")", Toast.LENGTH_SHORT)
                    .show();
        }
        // Close the Cursor.
        cursor.close();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private long getCalendarId() {
        String[] projection = new String[]{CalendarContract.Calendars._ID};
        String selection =
                CalendarContract.Calendars.ACCOUNT_NAME +
                        " = ? " +
                        CalendarContract.Calendars.ACCOUNT_TYPE +
                        " = ? ";
        // use the same values as above:
        String[] selArgs =
                new String[]{
                        MY_ACCOUNT_NAME,
                        CalendarContract.ACCOUNT_TYPE_LOCAL};
        if (checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return 1;
        }
        Cursor cursor =
                getContentResolver().
                        query(
                                CalendarContract.Calendars.CONTENT_URI,
                                projection,
                                selection,
                                selArgs,
                                null);
        if (cursor.moveToFirst()) {
            return cursor.getLong(0);
        }
        return -1;
    }


    public void addEvent() {

        // Intent calIntent = new Intent(Intent.ACTION_INSERT);
        // calIntent.setData(CalendarContract.Events.CONTENT_URI);
        // startActivity(calIntent);

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, "Learn Android");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Home suit home");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Download Examples");

        // Setting dates
        GregorianCalendar calDate = new GregorianCalendar(2012, 10, 02);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());

        // Make it a full day event
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // Make it a recurring Event
        intent.putExtra(CalendarContract.Events.RRULE,
                "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

        // Making it private and shown as busy
        intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        startActivity(intent);

    }

}