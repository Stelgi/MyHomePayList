package com.example.myhomepaylist.bottomnavmenu.payments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myhomepaylist.DataCallback;
import com.example.myhomepaylist.LoginActivity;
import com.example.myhomepaylist.PaymentListActivity;
import com.example.myhomepaylist.PeriodActivity;
import com.example.myhomepaylist.R;
import com.example.myhomepaylist.adapters.CustomListAdapter;
import com.example.myhomepaylist.databinding.FragmentChatBinding;
import com.example.myhomepaylist.databinding.FragmentPaymentListBinding;
import com.example.myhomepaylist.simple.Period;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentListFragment extends Fragment {
    private FragmentPaymentListBinding binding;
    private DatabaseReference databaseReference, databaseReference2;
    public static Long currentID;
    private Button btnAdd;
    public static ListView listView;

    public static ArrayList<Period> arrayList;
    public static CustomListAdapter adapter;

    public PaymentListFragment() {
        // Required empty public constructor
    }

    public static PaymentListFragment newInstance() {
        PaymentListFragment fragment = new PaymentListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentListBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView(){
        btnAdd = binding.btnAddPeriod;
        //logoutBtn = findViewById(R.id.logoutBtn);
        listView = binding.tl;
        arrayList = new ArrayList();
        adapter = new CustomListAdapter(getContext(), arrayList);

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
                Log.d("info", period.getTitle());
/*                Intent newIntent = new Intent(PaymentListActivity.this, PeriodActivity.class);
                newIntent.putExtra("Period", period);
                startActivity(newIntent);*/
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent newIntent = new Intent(MainActivity.this, AddingPeriodActivity.class);
//                Intent newIntent = new Intent(PaymentListActivity.this, LoginActivity.class);
//
//                startActivity(newIntent);
                Log.d("info", "Attbtn");
            }
        });

/*        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });*/
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

}