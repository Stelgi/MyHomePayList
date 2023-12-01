package com.example.myhomepaylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textView, paidInfoAmount, unpaidInfoAmount;
    private Period period;
    private DatabaseReference databaseReference;
    private ArrayList<Payment> payments, payments2;
    private ListView menuPaidPosition, menuUnPaidPosition;
    private CustomPaymentAdapter adapter, adapter2;
    private Button btnDeleteActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.period_activity);
        init();//Инициализируем период

        System.out.println("1");
        btnDeleteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Period");
                System.out.println("Period is " + period.getId());
                databaseReference.child("-NkVeT_b0NDc1qtssBez").removeValue();
            }
        });
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
        unpaidInfoAmount = findViewById(R.id.unpaidInfoAmount);
        paidInfoAmount = findViewById(R.id.paidInfoAmount);
        payments = new ArrayList<>();
        payments2 = new ArrayList<>();
        menuPaidPosition = findViewById(R.id.menuPaidPosition);
        menuUnPaidPosition = findViewById(R.id.menuUnPaidPosition);
        //Инициализируем адаптер
        adapter = new CustomPaymentAdapter(this, payments);
        adapter2 = new CustomPaymentAdapter(this, payments2);
        menuPaidPosition.setAdapter(adapter);
        menuUnPaidPosition.setAdapter(adapter2);
        btnDeleteActivity = findViewById(R.id.btnDeletePeriod);
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
                int a = payments.size() + payments2.size();
                paidInfoAmount.setText(payments.size() + " из " + a);
                unpaidInfoAmount.setText(payments2.size() + " из " + a);
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


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("2");
    }

    @Override
    public void onBackPressed() {
        System.out.println("3");
        super.onBackPressed();
        System.out.println("3");
    }
}