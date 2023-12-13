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
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myhomepaylist.adapters.CustomListAdapter;
import com.example.myhomepaylist.simple.Period;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentListActivity extends AppCompatActivity {
    public static ListView listView;
    public static ArrayList<Period> arrayList;
    private ArrayAdapter<Period> arrayAdapter;
    public static CustomListAdapter adapter;
    public static Long currentID;
    private DatabaseReference databaseReference, databaseReference2;
    private DataBaseActivity dba;
    private Button btnAdd;
    private ImageButton logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment_list);
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
    }

        private void initView(){
            btnAdd = findViewById(R.id.btnAddPeriod);
            logoutBtn = findViewById(R.id.logoutBtn);
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
                    Intent newIntent = new Intent(PaymentListActivity.this, PeriodActivity.class);
                    newIntent.putExtra("Period", period);
                    startActivity(newIntent);
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent newIntent = new Intent(MainActivity.this, AddingPeriodActivity.class);
                    Intent newIntent = new Intent(PaymentListActivity.this, LoginActivity.class);

                    startActivity(newIntent);
                }
            });

            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
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
            Intent b = new Intent(PaymentListActivity.this, PeriodActivity.class);
            startActivity(b);
        }

}

