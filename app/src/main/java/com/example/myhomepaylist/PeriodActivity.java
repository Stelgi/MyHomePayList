package com.example.myhomepaylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myhomepaylist.adapters.CustomPaymentAdapter;
import com.example.myhomepaylist.simple.Payment;
import com.example.myhomepaylist.simple.Period;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PeriodActivity extends AppCompatActivity {
    private TextView textView;
    private Period period;
    private DatabaseReference databaseReference;
    private ArrayList<Payment> payments, payments2;
    private ListView menuPaidPosition, menuUnPaidPosition;
    private CustomPaymentAdapter adapter, adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.period_activity);
        init();//Инициализируем период



//        Bundle arguments = getIntent().getExtras();
//        String nameMonth = arguments.get("nameMonth").toString();
//
//        SpannableString con = new SpannableString(nameMonth);
//        con.setSpan(new UnderlineSpan(), 0, nameMonth.length(), 0);
//        textView = findViewById(R.id.monthNameOpenMenu);
//        textView.setText(con);

    }

    private void init(){
        period = (Period) getIntent().getSerializableExtra("Period");
        textView = findViewById(R.id.monthNameOpenMenu);
        textView.setText(period.getTitle());
        payments = new ArrayList<>();
        payments2 = new ArrayList<>();
        menuPaidPosition = findViewById(R.id.menuPaidPosition);
        menuUnPaidPosition = findViewById(R.id.menuUnPaidPosition);
        //Инициализируем адаптер
        adapter = new CustomPaymentAdapter(this, payments);
        adapter2 = new CustomPaymentAdapter(this, payments2);
        menuPaidPosition.setAdapter(adapter);
        menuUnPaidPosition.setAdapter(adapter2);

        initDatabase();
    }

    private void initDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Payments");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(payments.size() > 0) payments.clear();
                if(payments2.size() > 0) payments2.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    Payment payment = ds.getValue(Payment.class);
                    if(payment.getPeriodId() == period.getId()){
                        if(payment.isReady()){
                            payments.add(payment);
                        }else{
                            payments2.add(payment);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}