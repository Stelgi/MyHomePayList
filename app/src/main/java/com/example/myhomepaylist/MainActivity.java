package com.example.myhomepaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView a;
    private DataBaseActivity dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dba = new DataBaseActivity();
        if(dba.connectToDB(getBaseContext())){
            //next action
        }
//sssssss
            //db.execSQL("CREATE TABLE IF NOT EXISTS users('name' TEXT, 'age' INTEGER)");
            //db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31);");

        //Cursor query = db.rawQuery("SELECT * FROM users;", null);
        //ArrayList<String> as = new ArrayList<>();

        /*while(query.moveToNext()){
            String name = query.getString(0);
            int age = query.getInt(1);
            as.add("Name: " + name + " Age: " + age);
        }

        query.close();
        db.close();*/
//        for(String ab : as){
//            System.out.println(ab);
//        }
        a = findViewById(R.id.tl);

        String[] names = {"1", "2", "3", "4",  "5", "6", "7", "8", "9", "10", "September"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.simple_line_month, R.id.textView123, names);
        a.setAdapter(arrayAdapter);

        a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent a = new Intent(MainActivity.this, ActivityTwo.class);
                String ab = (String) parent.getItemAtPosition(position);
                a.putExtra("nameMonth", ab);
                startActivity(a);
            }
        });
    }

    public void toOpenMenu(View v){
        Intent a = new Intent(this, ActivityTwo.class);
        startActivity(a);

    }
}