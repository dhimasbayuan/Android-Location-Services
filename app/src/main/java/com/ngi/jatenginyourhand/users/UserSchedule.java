package com.ngi.jatenginyourhand.users;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.ngi.jatenginyourhand.R;
import com.ngi.jatenginyourhand.models.ScheduleData;
import com.ngi.jatenginyourhand.receiver.AlarmService;
import com.ngi.jatenginyourhand.utils.Message;
import com.ngi.jatenginyourhand.utils.myDbAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;
import java.util.List;

public class UserSchedule extends AppCompatActivity implements View.OnClickListener {
    EditText Title, in_date, in_date2, in_time, save;
    myDbAdapter helper;
    private String TAG = "UserSchedule:";
    Toolbar toolbar;
    Button btnSimpanNotif;
    Button btnBatalNotif;
    ImageView btnDatePicker , btnTimePicker;
    ImageView btnDatePicker2;
    EditText txtDate, txtTime;
    EditText txtDate2;
    CheckBox ch1,ch2,ch3;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private AlarmManager manager;
    private PendingIntent pendingIntent;
    private Context context;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_schedule);
        Title = (EditText) findViewById(R.id.Title);
        in_date = (EditText) findViewById(R.id.in_date);
        in_date2 = (EditText) findViewById(R.id.in_date2);
        in_time = (EditText) findViewById(R.id.in_time);

        btnDatePicker=(ImageView)findViewById(R.id.btn_date);
        txtDate=(EditText)findViewById(R.id.in_date);
        btnDatePicker2=(ImageView)findViewById(R.id.btn_date2);
        txtDate2=(EditText)findViewById(R.id.in_date2);
        txtTime=(EditText)findViewById(R.id.in_time);
        btnTimePicker=(ImageView)findViewById(R.id.btn_time);
        btnDatePicker.setOnClickListener(this);
        btnDatePicker2.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.addschedule));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        helper = new myDbAdapter(this);
        ch1=(CheckBox)findViewById(R.id.is_1);
        ch2=(CheckBox)findViewById(R.id.is_3);
        ch3=(CheckBox)findViewById(R.id.is_7);
        ch1.setOnClickListener(this);
        ch2.setOnClickListener(this);
        ch3.setOnClickListener(this);

        btnSimpanNotif = findViewById(R.id.btnSimpanNotif);
        btnSimpanNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t1 = Title.getText().toString();
                String t2 = in_date.getText().toString();
                String t3 = in_date2.getText().toString();
                String t4 = in_time.getText().toString();
                String t5 = "0";
                String t6 = "0";
                String t7 = "0";

               if(ch1.isChecked()) {
                    t5 = "1";
               }
               if(ch2.isChecked()){
                     t6 = "1";
               }
               if(ch3.isChecked()) {
                    t7 = "1";
               }
                if(t1.isEmpty()||t2.isEmpty()||t3.isEmpty()||t4.isEmpty()){
                    Message.message(getApplicationContext(),getString(R.string.setNoIsEm));
                }else {
                    long id = helper.insertData(t1,t2,t3,t4,t5,t6,t7);
                    if(id<=0)
                    {
                        Message.message(getApplicationContext(),getString(R.string.mesAddNoEr));
                        Title.setText("");
                        in_date.setText("");
                        in_date2.setText("");
                        in_time.setText("");
                    }else{
                        Message.message(getApplicationContext(),getString(R.string.mesAddNo));
                        Title.setText("");
                        in_date.setText("");
                        in_date2.setText("");
                        in_time.setText("");
                    }
                }

                

            }
        });

        btnBatalNotif = findViewById(R.id.btnBatalNotif);
        btnBatalNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }
    @Override
    public void onClick(View v) {
        if( v == ch1) {
            if(ch1.isChecked()) {
                StringBuffer result = new StringBuffer();
                result.append(getString(R.string.day1)).append(ch1.isChecked());
            }
        }
        if( v == ch2) {
            if(ch2.isChecked()) {
                StringBuffer result = new StringBuffer();
                result.append(getString(R.string.day3)).append(ch2.isChecked());
            }
        }
        if( v == ch3) {
            if(ch3.isChecked()) {
                StringBuffer result = new StringBuffer();
                result.append(getString(R.string.day7)).append(ch3.isChecked());
            }
        }
        if (v == btnDatePicker ) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnDatePicker2 ) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(String.format("%02d", hourOfDay)+ ":" + String.format("%02d", minute));
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void startService(){
        Intent alarmIntent = new Intent(this, AlarmService.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 100000;
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval , pendingIntent);
    }
}
