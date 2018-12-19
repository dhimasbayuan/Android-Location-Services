package com.ngi.jatenginyourhand.users;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ngi.jatenginyourhand.R;
import com.ngi.jatenginyourhand.listener.ScheduleListener;
import com.ngi.jatenginyourhand.models.ScheduleData;
import com.ngi.jatenginyourhand.utils.myDbAdapter;

import java.util.ArrayList;
import java.util.List;


public class UserScheduleFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, ScheduleListener {

    ListView listView;
    myDbAdapter db;
    ListView scheduleListView;
    LinearLayout nojadwal;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.user_schedule, container, false);
        setHasOptionsMenu(true);

        scheduleListView = v.findViewById(R.id.scheduleListView);
        db = new myDbAdapter(getActivity());
        List<ScheduleData> data = db.getData();


        String[] dataarray = new String[data.size()];

        ArrayList<NotifSubject> subjects = new ArrayList<NotifSubject>();

        int i = 0;

        for (ScheduleData a : data){
            NotifSubject tamp = new NotifSubject(a.getId(),a.getTitle(), a.getInDate(), a.getInDate2(), a.getInTime());
            subjects.add(tamp);
            i++;
        }


        NotifAdapter adapter = new NotifAdapter(getActivity(), R.layout.cus_list, subjects,this);
        scheduleListView.setAdapter(adapter);

        nojadwal = v.findViewById(R.id.nojadwal);

        if(i > 0){
            nojadwal.setVisibility(View.GONE);
        }else{
            nojadwal.setVisibility(View.VISIBLE);
        }


        FloatingActionButton addScheduleBtn = v.findViewById(R.id.addScheduleBtn);
        addScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserSchedule.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onResume() {

        super.onResume();
        ArrayList<NotifSubject> subjects = new ArrayList<NotifSubject>();
        db = new myDbAdapter(getActivity());
        List<ScheduleData> data = db.getData();

        int i = 0;
        for (ScheduleData a : data){
            NotifSubject tamp = new NotifSubject(a.getId(),a.getTitle(), a.getInDate(), a.getInDate2(), a.getInTime() );
            subjects.add(tamp);
            i++;
        }

        NotifAdapter adapter = new NotifAdapter(getActivity(), R.layout.cus_list, subjects, this);
        scheduleListView.setAdapter(adapter);


        if(i > 0){
            nojadwal.setVisibility(View.GONE);
        }else{
            nojadwal.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void refres() {
        Log.d("bayu","Listener Refresh");
        ArrayList<NotifSubject> subjects = new ArrayList<NotifSubject>();
        db = new myDbAdapter(getActivity());
        List<ScheduleData> data = db.getData();

        int i = 0;
        for (ScheduleData a : data){
            NotifSubject tamp = new NotifSubject(a.getId(),a.getTitle(), a.getInDate(), a.getInDate2(), a.getInTime() );
            subjects.add(tamp);
            i++;
        }

        NotifAdapter adapter = new NotifAdapter(getActivity(), R.layout.cus_list, subjects, this);
        scheduleListView.setAdapter(adapter);


        if(i > 0){
            nojadwal.setVisibility(View.GONE);
        }else{
            nojadwal.setVisibility(View.VISIBLE);
        }
    }
}