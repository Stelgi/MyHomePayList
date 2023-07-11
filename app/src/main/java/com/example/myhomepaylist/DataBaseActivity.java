package com.example.myhomepaylist;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;

public class DataBaseActivity {
    Connection con;
    private void createDb(){

    }

    public boolean connectToDB(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase("test.db", MODE_PRIVATE, null);
        if(db.isOpen()){
            Log.d("BDOpen", "База данных успешно открыта");

            Cursor query = db.rawQuery("SELECT * FROM users;", null);
            //ArrayList<String> as = new ArrayList<>();

            while(query.moveToNext()){
                String name = query.getString(0);
                int age = query.getInt(1);
            }
        }
        return true;
    }
}
