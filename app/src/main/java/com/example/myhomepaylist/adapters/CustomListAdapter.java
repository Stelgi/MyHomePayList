package com.example.myhomepaylist.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myhomepaylist.R;
import com.example.myhomepaylist.simple.Period;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Period> dataList;

    public CustomListAdapter(Context context, ArrayList<Period> dataList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_line_month, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textView123);


        String listItemData = dataList.get(position).getTitle();
        textViewTitle.setText(listItemData);

        return convertView;
    }
}
