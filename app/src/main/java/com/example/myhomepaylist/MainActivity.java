package com.example.myhomepaylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myhomepaylist.adapters.CustomListAdapter;
import com.example.myhomepaylist.simple.Period;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ListView listView;
    public static ArrayList<Period> arrayList;
    private ArrayAdapter<Period> arrayAdapter;
    public static CustomListAdapter adapter;
    public static Long currentID;
    private DatabaseReference databaseReference, databaseReference2;
    private DataBaseActivity dba;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("UserID");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentID = (Long) snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Error getting data");
            }
        });
        initView();


        //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DataBaseActivity");








//        testManager taskManager = new testManager(dba);
//
//        // Выполняем задачу в фоновом потоке и получаем результат в главном потоке
//        taskManager.executeTaskInThread(() -> {
//            // Обновление UI с использованием результата
//            // Например, отобразить данные из БД на экране
//        }, this::updateUIWithData);

//        CompletableFuture<Long> future = taskManager.executeTaskInThread();
////        try {
////            Long result = future.get(); // Дожидаемся завершения задачи
////            updateUIWithData(result); // Обновляем UI с использованием результата
////        } catch (ExecutionException | InterruptedException e) {
////            // Обработка исключений
////        }
//
//        future.thenAccept(result -> {
//            // Обновление UI с использованием результата
//            // Например, отобразить данные из БД на экране
//            updateUIWithData(result);
//        });
    }

        private void initView(){
            btnAdd = findViewById(R.id.btnAddPeriod);
            listView = findViewById(R.id.tl);
            arrayList = new ArrayList();
            //initArray();
            adapter = new CustomListAdapter(this, arrayList);
            initArray(new DataCallback() {
                @Override
                public void onDataLoaded(ArrayList<Period> periods) {
                    // Устанавливаем адаптер и скролл на середину списка
//                    listView.setAdapter(adapter);
//                    int middlePosition = adapter.getCount() / 2;
//                    listView.setSelection(middlePosition);
                    listView.setAdapter(adapter);
                    listView.setSelection(adapter.getCount() / 2);
                    // Другие действия, которые нужно выполнить после загрузки данных
                }
            });

            //arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);





            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Period period = arrayList.get(position);
                    Intent newIntent = new Intent(MainActivity.this, PeriodActivity.class);
                    newIntent.putExtra("Period", period);
                    startActivity(newIntent);
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(MainActivity.this, AddingPeriodActivity.class);

                    startActivity(newIntent);
                }
            });
        }

        private void initArray(final DataCallback callback){
            databaseReference = FirebaseDatabase.getInstance().getReference("Period");
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(arrayList.size() > 0) arrayList.clear();
                    for (DataSnapshot ds: snapshot.getChildren()){
                        Period period = ds.getValue(Period.class);
                        arrayList.add(period);
                    }
                    adapter.notifyDataSetChanged();

                    if (callback != null) {
                        callback.onDataLoaded(arrayList);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("MainAcitvity", "error", error.toException());
                }
            };
            databaseReference.addValueEventListener(valueEventListener);

        }

        private void updateUIWithData(Long a) {
            Intent b = new Intent(MainActivity.this, PeriodActivity.class);
            startActivity(b);
        }








//
//
//
//        dba = new DataBaseActivity();
//        if(dba.connectToDB(getBaseContext())){
//            //next action
//        }
//
//
//
//
//
//
//
//
//
//
//
//            //db.execSQL("CREATE TABLE IF NOT EXISTS users('name' TEXT, 'age' INTEGER)");
//            //db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31);");
//
//        //Cursor query = db.rawQuery("SELECT * FROM users;", null);
//        //ArrayList<String> as = new ArrayList<>();
//
//        /*while(query.moveToNext()){
//            String name = query.getString(0);
//            int age = query.getInt(1);
//            as.add("Name: " + name + " Age: " + age);
//        }
//
//        query.close();
//        db.close();*/
////        for(String ab : as){
////            System.out.println(ab);
////        }
//        a = findViewById(R.id.tl);
//
//        String[] names = {"1", "2", "3", "4",  "5", "6", "7", "8", "9", "10", "September"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.simple_line_month, R.id.textView123, names);
//        a.setAdapter(arrayAdapter);
//
//        a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent a = new Intent(MainActivity.this, ActivityTwo.class);
//                String ab = (String) parent.getItemAtPosition(position);
//                a.putExtra("nameMonth", ab);
//                startActivity(a);
//            }
//        });
//    }
//
//    public void toOpenMenu(View v){
//        Intent a = new Intent(this, ActivityTwo.class);
//        startActivity(a);
//
//    }
}

