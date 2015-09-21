package com.mycompany.remindme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int hour;
    public static int minute;
    public static int month;
    public static int day;
    public static int year;

    public static  String titleText;
    public static  String descText;

    AlarmReceiver alarm = new AlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setReminder(View view) {

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        EditText title = (EditText) findViewById(R.id.titleText);
        EditText desc = (EditText) findViewById(R.id.descText);

        titleText = String.valueOf(title.getText());
        descText = String.valueOf(desc.getText());

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();

        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth();
        year = datePicker.getYear();

        alarm.setAlarm(this);
        Toast.makeText(MainActivity.this, "Reminder is set", Toast.LENGTH_SHORT).show();

    }
}
