package com.ngi.jatenginyourhand.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ngi.jatenginyourhand.models.ScheduleData;

import java.util.ArrayList;
import java.util.List;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String Title, String in_date, String in_date2 , String in_time, String is_1, String is_3 , String is_7)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TITLE, Title);
        contentValues.put(myDbHelper.INDATE, in_date);
        contentValues.put(myDbHelper.INDATE2, in_date2);
        contentValues.put(myDbHelper.INTIME, in_time);
        contentValues.put(myDbHelper.IS1, is_1);
        contentValues.put(myDbHelper.IS3, is_3);
        contentValues.put(myDbHelper.IS7, is_7);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }


    public List<ScheduleData> getData()
    {
        List<ScheduleData> datas = new ArrayList<>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.TITLE,myDbHelper.INDATE,myDbHelper.INDATE2,myDbHelper.INTIME,myDbHelper.IS1,myDbHelper.IS3,myDbHelper.IS7};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,myDbHelper.UID+" DESC");
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            ScheduleData data = new ScheduleData();
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String Title =cursor.getString(cursor.getColumnIndex(myDbHelper.TITLE));
            String  in_date =cursor.getString(cursor.getColumnIndex(myDbHelper.INDATE));
            String  in_date2 =cursor.getString(cursor.getColumnIndex(myDbHelper.INDATE2));
            String  in_time =cursor.getString(cursor.getColumnIndex(myDbHelper.INTIME));
            String  is_1 =cursor.getString(cursor.getColumnIndex(myDbHelper.IS1));
            String  is_3 =cursor.getString(cursor.getColumnIndex(myDbHelper.IS3));
            String  is_7 =cursor.getString(cursor.getColumnIndex(myDbHelper.IS7));
            data.setId(String.valueOf(cid));
            data.setTitle(Title);
            data.setInDate(in_date);
            data.setInDate2(in_date2);
            data.setInTime(in_time);
            data.setIs_1(is_1);
            data.setIs_3(is_3);
            data.setIs_7(is_7);
            datas.add(data);

//            buffer.append(cid+ "   " + Title + "   " + in_date +"  "+in_date2+" "+in_time+" "+is_1+" "+is_3+" "+is_7+" \n");
        }
        return datas;
    }

//
//    public void delete (Integer _id) {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//
//        db.delete(myhelper.TABLE_NAME, myhelper.UID + " = " + _id, null);
//        db.close();
//    }

    public Integer deleteData (String _id) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        return db.delete(myhelper.TABLE_NAME , "_id = ?",new String[] {_id});
    }

//    public void deleteItem(String _id){
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//
//        db.execSQL("DELETE FROM " + myhelper.TABLE_NAME + " WHERE " + myhelper.UID + "=\"" + _id + "\" ;"
//                //        + COLUMN_LINK + "=\"" + link + "\" ;"
//        );
//    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "jtginurhand";    // Database Name
        private static final String TABLE_NAME = "schedule";   // Table Name
        private static final int DATABASE_Version = 6;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String TITLE = "Title"; // Colum II
        private static final String INDATE = "in_date"; //Column III
        private static final String INDATE2 = "in_date2"; //Column IV
        private static final String INTIME = "in_time";    //Column V
        private static final String IS1 = "is_1";
        private static final String IS3 = "is_3";
        private static final String IS7 = "is_7";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE+" TEXT , "+ INDATE+" TEXT , "+INDATE2+" TEXT, "+ INTIME+" TEXT, "+IS1+" TEXT, "+IS3+" TEXT, "+IS7+" TEXT)";

        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }


    }
}
