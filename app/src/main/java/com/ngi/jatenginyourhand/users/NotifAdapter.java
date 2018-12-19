package com.ngi.jatenginyourhand.users;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ngi.jatenginyourhand.R;
import com.ngi.jatenginyourhand.listener.ScheduleListener;
import com.ngi.jatenginyourhand.utils.myDbAdapter;

import java.util.ArrayList;
import java.util.logging.Handler;
import android.support.v7.app.AlertDialog;

public class NotifAdapter extends ArrayAdapter<NotifSubject> {

    myDbAdapter db;
    SimpleCursorAdapter adapter;
    ArrayList<NotifSubject> subjects = null;
    Context context;
    int resource;
    ImageView delSchedule;
    ScheduleListener listener;

    public NotifAdapter(Context context, int resource, ArrayList<NotifSubject> subjects, ScheduleListener listener) {
        super(context, resource, subjects);
        this.context = context;
        this.resource = resource;
        this.subjects = subjects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final NotifSubject subject = subjects.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.cus_list, parent, false);
        }

        db = new myDbAdapter(context);

        TextView tujuan = (TextView) convertView.findViewById(R.id.listDest);
        TextView tglBerangkat = (TextView) convertView.findViewById(R.id.listTglBrgk);
        TextView tglKembali = (TextView) convertView.findViewById(R.id.listTglPlg);
        TextView waktu = (TextView) convertView.findViewById(R.id.listTime);
        //txtid = (EditText) convertView.findViewById(R.id.hpsList);
        tujuan.setText(subject.tujuanUser);
        tglBerangkat.setText(subject.userBerangkat);
        tglKembali.setText(subject.userKembali);
        waktu.setText(subject.notifUser);
        delSchedule=(ImageView) convertView.findViewById(R.id.hpsList);


        delSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deletedRows = db.deleteData(subject.id);
                Log.d("bayu",String.valueOf(deletedRows));
                if(deletedRows > 0){
                    Toast.makeText(context, "Data Delete", Toast.LENGTH_SHORT).show();
                    listener.refres();
                }
                else {
                    Toast.makeText(context, "Failed To Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return convertView;
    }






}
