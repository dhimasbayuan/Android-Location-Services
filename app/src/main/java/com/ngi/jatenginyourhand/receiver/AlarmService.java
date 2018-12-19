package com.ngi.jatenginyourhand.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;

import com.ngi.jatenginyourhand.R;
import com.ngi.jatenginyourhand.models.ScheduleData;
import com.ngi.jatenginyourhand.utils.myDbAdapter;

//import net.danlew.android.joda.JodaTimeAndroid;
//
//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmService extends BroadcastReceiver {
    myDbAdapter db;
    private Context context;
    private Date date;

    public void onReceive(Context context1, Intent intent) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        JodaTimeAndroid.init(context1);
//        DateTime dateTime = new DateTime();
//        DateTimeFormatter dtf = DateTimeFormat.forPattern("d-M-yyyy HH:mm:ss");
//        dateTime.plusDays(1);

//        Date dateA = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(dateA);
//        calendar.add(Calendar.DAY_OF_YEAR,1);
//        dateA = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy K:m");
//        String alarmTime = sdf.format(dateA);



        this.context = context1;
        Log.e("bayu","**Test Alarm**");
        db = new myDbAdapter(context);

        List<ScheduleData> data = db.getData();


        for (ScheduleData a : data){
            String title =a.getTitle();
            String date = a.getInDate();
            String time = a.getInTime();
            String is_1 = a.getIs_1();
            String is_3 = a.getIs_3();
            String is_7 = a.getIs_7();


//            Date cDate = new Date(dateTime.toDate());
//            String alarmTime = dtf.print(dateTime);
//            Log.d("bayu",alarmTime +" = "+date+" "+time  + " is checked = "+is_1+" "+is_3+" "+is_7);

            //dd-mm-yy HH.mm

            if (is_1.equals("1")){
                Date dateB = new Date();
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(dateB);
                calendar1.add(Calendar.DAY_OF_YEAR,1);
                dateB = calendar1.getTime();
                SimpleDateFormat sdf1 = new SimpleDateFormat("d-M-yyyy HH:mm");
                String alarmTime = sdf1.format(dateB);
                if (alarmTime.equals(date+" "+time)){
                    Intent startServiceIntent = new Intent(context, AlarmService.class);
                    context.startService(startServiceIntent);

                    NotificationManager notif=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

                    Notification notify=new Notification.Builder
                            (context.getApplicationContext()).setContentTitle(title).setContentText(date+" "+time).
                            setSmallIcon(R.drawable.ic_schedule).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);
                }
                Log.d("bayu",alarmTime +" 1 Hari "+date+" "+time  + " is checked = "+is_1+" "+is_3+" "+is_7 +" ="+alarmTime);

            }
            if (is_3.equals("1")){
                Date dateC = new Date();
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(dateC);
                calendar3.add(Calendar.DAY_OF_YEAR,3);
                dateC = calendar3.getTime();
                SimpleDateFormat sdf3 = new SimpleDateFormat("d-M-yyyy HH:mm");
                String alarmTime3 = sdf3.format(dateC);
                if (alarmTime3.equals(date+" "+time)){
                    Intent startServiceIntent = new Intent(context, AlarmService.class);
                    context.startService(startServiceIntent);

                    NotificationManager notif=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

                    Notification notify=new Notification.Builder
                            (context.getApplicationContext()).setContentTitle(title).setContentText(date+" "+time).
                            setSmallIcon(R.drawable.ic_schedule).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);
                }
                Log.d("bayu",alarmTime3 +" 3 Hari "+date+" "+time  + " is checked = "+is_1+" "+is_3+" "+is_7+" ="+alarmTime3);

            }
            if (is_7.equals("1")){
                Date dateD = new Date();
                Calendar calendar7 = Calendar.getInstance();
                calendar7.setTime(dateD);
                calendar7.add(Calendar.DAY_OF_YEAR,7);
                dateD = calendar7.getTime();
                SimpleDateFormat sdf7 = new SimpleDateFormat("d-M-yyyy HH:mm");
                String alarmTime7 = sdf7.format(dateD);
                if (alarmTime7.equals(date+" "+time)){
                    Intent startServiceIntent = new Intent(context, AlarmService.class);
                    context.startService(startServiceIntent);

                    NotificationManager notif=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

                    Notification notify=new Notification.Builder
                            (context.getApplicationContext()).setContentTitle(title).setContentText(date+" "+time).
                            setSmallIcon(R.drawable.ic_schedule).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);
                }
                Log.d("bayu",alarmTime7 +" 7 Hari "+date+" "+time  + " is checked = "+is_1+" "+is_3+" "+is_7+" ="+alarmTime7);
            }




        }

    }
}
