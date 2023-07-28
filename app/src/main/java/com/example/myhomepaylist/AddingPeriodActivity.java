package com.example.myhomepaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myhomepaylist.simple.Payment;
import com.example.myhomepaylist.simple.Period;
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
        Payment pay1 = new Payment(random.nextInt(1000000), "Электричество + ТКО", id);
        Payment pay2 = new Payment(random.nextInt(1000000), "Ростелеком (Телефон)", id);
        Payment pay3 = new Payment(random.nextInt(1000000), "Интернет", id);
        Payment pay4 = new Payment(random.nextInt(1000000), "Вода (РАЦ)", id);
        Payment pay5 = new Payment(random.nextInt(1000000), "Кабельное", id);
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
        int periodId = random.nextInt(1000000);
        period.setId(periodId);
        databaseReference.push().setValue(period);

        initPeriod(periodId);

    }
}