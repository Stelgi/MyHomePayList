package com.example.myhomepaylist.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.myhomepaylist.R;
import com.example.myhomepaylist.simple.Payment;
import com.example.myhomepaylist.simple.Period;

import java.util.ArrayList;

public class CustomPaymentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Payment> dataList;

    public CustomPaymentAdapter(Context context, ArrayList<Payment> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_line_payments, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textView123);
        Button button = convertView.findViewById(R.id.newBTN);

        String listItemData = dataList.get(position).getTitle();
        if(dataList.get(position).isReady()){
            button.setText(R.string.statusPaidText);
            int color = ContextCompat.getColor(this.context, R.color.purple_500);
            ColorStateList colorStateList = ColorStateList.valueOf(color);
            button.setBackgroundTintList(colorStateList);
        }else{
            button.setText(R.string.statusUnPaidText);
            int color = ContextCompat.getColor(this.context, R.color.c2);
            ColorStateList colorStateList = ColorStateList.valueOf(color);
            button.setBackgroundTintList(colorStateList);
        }
        textViewTitle.setText(listItemData);

        return convertView;
    }
}
