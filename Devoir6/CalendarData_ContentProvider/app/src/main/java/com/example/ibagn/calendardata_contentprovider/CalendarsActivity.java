package com.example.ibagn.calendardata_contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static android.provider.CalendarContract.Events.CONTENT_URI;

//https://books.google.ro/books?id=3q4KCgAAQBAJ&pg=PT316&lpg=PT316&dq=calendarcontract+android&source=bl&ots=Xs4sSt7_jy&sig=J9wri3aTccmmN7qQZ2OQB5kwylY&hl=ro&sa=X&ved=0CFkQ6AEwCDgUahUKEwibpZmrjpzJAhVMbRQKHeM0DQs#v=onepage&q=calendarcontract%20android&f=false
public class CalendarsActivity extends AppCompatActivity {


    String MY_ACCOUNT_NAME = "anamaria@example.com";
    String calendarID, calendarName = "Medication Calendar";
    boolean calendarExists = false;
    public static Uri CALENDAR_URI =
            Uri.parse("content://com.android.calendar/calendars");

    public static final String[] FIELDS = {CalendarContract.Calendars._ID, CalendarContract.Calendars.ACCOUNT_NAME, CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendars);
        Button qry = (Button) findViewById(R.id.querybut);
        Button addEvent = (Button) findViewById(R.id.add);
        Button delete_btn = (Button) findViewById(R.id.delete);
        Button addCalendar = (Button) findViewById(R.id.addCalendar);
        Button deleteEvent = (Button) findViewById(R.id.rm_event);
        if (calendarExists == false) {
            createCalendar();
            calendarExists = true;
        }
        addCalendar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                if (calendarExists == false) {
                    createCalendar();
                    calendarExists = true;
                }


            }
        });

        qry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                query();


            }
        });
        deleteEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                removeEvent();


            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                deleteCalendar("Medication Calendar");

            }
        });
        addEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                addEvent();

            }
        });
    }

    public void createCalendar() {
        ContentValues values = new ContentValues();
        values.put(
                CalendarContract.Calendars.ACCOUNT_NAME, MY_ACCOUNT_NAME
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

        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = getContentResolver().insert(builder.build(), values);
        Cursor cursor = contentResolver.query(CALENDAR_URI, FIELDS, null, null, null);
        setId();
        calendarExists = true;

    }

    public void deleteCalendar(String calendar_name) {
        Uri evuri = CalendarContract.Calendars.CONTENT_URI;
        calendarExists = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
        }

        Cursor result = getContentResolver().query(evuri, new String[]{CalendarContract.Calendars._ID, CalendarContract.Calendars.ACCOUNT_NAME, CalendarContract.Calendars.CALENDAR_DISPLAY_NAME}, null, null, null);
        while (result.moveToNext()) {
            if (result.getString(2).equals(calendar_name)) {
                long calid = result.getLong(0);
                Uri deleteUri = ContentUris.withAppendedId(evuri, calid);
                getContentResolver().delete(deleteUri, null, null);
            }
        }
    }

    public void setId() {
        String[] projection = {CalendarContract.Calendars._ID,
                CalendarContract.Calendars.NAME};
        // Get a Cursor over the Events Provider.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
        }
        Cursor cursor = getContentResolver().query(
                CalendarContract.Calendars.CONTENT_URI, projection, CalendarContract.Calendars.VISIBLE + " = 1", null,
                CalendarContract.Calendars._ID);
        // Get the index of the columns.
        int nameIdx = cursor
                .getColumnIndexOrThrow(CalendarContract.Calendars.NAME);
        int idIdx = cursor.getColumnIndexOrThrow(CalendarContract.Calendars._ID);
        // Initialize the result set.
        String[] result = new String[cursor.getCount()];
        // Iterate over the result Cursor.
        while (cursor.moveToNext()) {
            // Extract the name.
            String name = cursor.getString(nameIdx);
            // Extract the unique ID.
            String id = cursor.getString(idIdx);
            result[cursor.getPosition()] = name + "(" + id + ")";
            if (name.matches("Medication Calendar")) {
//                Toast.makeText(this, name + "(" + id + ")", Toast.LENGTH_SHORT)
//                        .show();

                calendarID = id;


            }
        }
        // Close the Cursor.
        cursor.close();

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
                CONTENT_URI, projection, CalendarContract.Events.CALENDAR_ID + "= '" + calendarID + "'", null,
                null);

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

    public void removeEvent() {
        long calId = Integer.parseInt(calendarID);
        if (calId == -1) {
            // no calendar account; react meaningfully
            return;
        }
        Uri evuri = CalendarContract.Events.CONTENT_URI;
        Cursor result = getContentResolver().query(evuri, new String[]{ CalendarContract.Events._ID,  CalendarContract.Events.ACCOUNT_NAME, CalendarContract.Events.TITLE}, CalendarContract.Events.CALENDAR_ID + "= '" + calendarID + "'", null, null);

        while (result.moveToNext()) {
            if (result.getString(2).equals("Some title")) {
                long calid = result.getLong(0);

                Uri deleteUri = ContentUris.withAppendedId(evuri, calid);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                }
                getContentResolver().delete(deleteUri, null, null);

            }
        }
    }

    public void addEvent() {
        long calId = Integer.parseInt(calendarID);
        if (calId == -1) {
            // no calendar account; react meaningfully
            return;
        }
        Calendar cal = new GregorianCalendar(2012, 11, 14);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long start = cal.getTimeInMillis();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, start);
        values.put(CalendarContract.Events.DTEND, start);
        values.put(CalendarContract.Events.RRULE,
                "FREQ=DAILY;COUNT=20;BYDAY=MO,TU,WE,TH,FR;WKST=MO");
        values.put(CalendarContract.Events.TITLE, "Some title");
        values.put(CalendarContract.Events.EVENT_LOCATION, "Münster");
        values.put(CalendarContract.Events.CALENDAR_ID, calId);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Berlin");
        values.put(CalendarContract.Events.DESCRIPTION,
                "The agenda or some description of the event");
// reasonable defaults exist:
        values.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PUBLIC);
        values.put(CalendarContract.Events.SELF_ATTENDEE_STATUS,
                CalendarContract.Events.STATUS_CONFIRMED);
        values.put(CalendarContract.Events.ALL_DAY, 1);
        values.put(CalendarContract.Events.ORGANIZER, "some.mail@some.address.com");
        values.put(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS, 1);
        values.put(CalendarContract.Events.GUESTS_CAN_MODIFY, 1);
        values.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
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
        Uri evuri = CalendarContract.Calendars.CONTENT_URI;
        Cursor result = getContentResolver().query(evuri, new String[]{CalendarContract.Calendars._ID, CalendarContract.Calendars.ACCOUNT_NAME, CalendarContract.Calendars.CALENDAR_DISPLAY_NAME}, null, null, null);

        while (result.moveToNext()) {
            if (result.getString(2).equals(calendarName)) {
                long calid = result.getLong(0);
                Uri addUri = ContentUris.withAppendedId(evuri, calid);
                getContentResolver().insert(CONTENT_URI, values);
                long eventId = new Long(addUri.getLastPathSegment());
            }
        }


    }

}
