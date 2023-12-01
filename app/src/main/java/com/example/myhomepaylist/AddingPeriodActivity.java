package com.example.myhomepaylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myhomepaylist.simple.Payment;
import com.example.myhomepaylist.simple.Period;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddingPeriodActivity extends AppCompatActivity {
    private EditText namePeriod;
    private Button btnAdd;
    private DatabaseReference databaseReference, databaseReference2;
    private static final String PERIOD = "Period";
    private static final String PAYMENT = "Payments";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_period);
        btnAdd = findViewById(R.id.buttonAddPeriod);
        namePeriod = findViewById(R.id.periodValue);
        databaseReference = FirebaseDatabase.getInstance().getReference(PERIOD);
        databaseReference2 = FirebaseDatabase.getInstance().getReference(PAYMENT);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingPeriod();
            }
        });
    }

    private void initPeriod(int id){
        long currentTime = System.currentTimeMillis();
        Random random = new Random(currentTime);
        Payment pay1 = new Payment(random.nextInt(1000000), "Электричество + ТКО", id, false);
        Payment pay2 = new Payment(random.nextInt(1000000), "Ростелеком (Телефон)", id, false);
        Payment pay3 = new Payment(random.nextInt(1000000), "Интернет", id, false);
        Payment pay4 = new Payment(random.nextInt(1000000), "Вода (РАЦ)", id, false);
        Payment pay5 = new Payment(random.nextInt(1000000), "Кабельное", id, false);
        databaseReference2.push().setValue(pay1);
        databaseReference2.push().setValue(pay2);
        databaseReference2.push().setValue(pay3);
        databaseReference2.push().setValue(pay4);
        databaseReference2.push().setValue(pay5);
    }

    private void addingPeriod(){
        Period period = new Period();
        period.setTitle(namePeriod.getText().toString());
        long currentTime = System.currentTimeMillis();
        Random random = new Random(currentTime);

        int periodId = MainActivity.currentID.intValue() + 1;
        period.setId(periodId);
        FirebaseDatabase.getInstance().getReference().child("UserID").setValue(periodId);
        //databaseReference.push().setValue(period);
        databaseReference.child(String.valueOf(periodId)).setValue(period)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("YourTag", "Data added successfully");
                        } else {
                            Log.e("YourTag", "Error adding data", task.getException());
                        }
                    }
                });

        initPeriod(periodId);
        Toast.makeText(this, "Период добавлен", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AddingPeriodActivity.this.finish();
            }
        }, 2000);
    }
}