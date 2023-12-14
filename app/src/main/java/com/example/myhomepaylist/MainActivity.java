package com.example.myhomepaylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myhomepaylist.bottomnavmenu.chats.ChatFragment;
import com.example.myhomepaylist.bottomnavmenu.payments.PaymentListFragment;
import com.example.myhomepaylist.bottomnavmenu.profile.ProfileFragment;
import com.example.myhomepaylist.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(binding.frameContainer.getId(), new PaymentListFragment()).commit();
        Map<Integer, Fragment> map = new HashMap<>();
        map.put(R.id.chat, new ChatFragment());
        map.put(R.id.payment_list, new PaymentListFragment());
        map.put(R.id.profile, new ProfileFragment());
        System.out.println("1234");
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = map.get(item.getItemId());
                getSupportFragmentManager().beginTransaction().replace(binding.frameContainer.getId(), fragment).commit();
                Log.d("info", String.valueOf(item.getItemId()));
                return true;
            }
        });

    }
}