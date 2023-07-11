package com.example.myhomepaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Bundle arguments = getIntent().getExtras();
        String nameMonth = arguments.get("nameMonth").toString();

        SpannableString con = new SpannableString(nameMonth);
        con.setSpan(new UnderlineSpan(), 0, nameMonth.length(), 0);
        textView = findViewById(R.id.monthNameOpenMenu);
        textView.setText(con);

    }
}