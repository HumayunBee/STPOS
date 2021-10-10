package com.example.stpos.Adaptar.ProductAddAdaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stpos.DataModel.Category.Category;

import com.example.stpos.R;

import java.util.ArrayList;
import java.util.List;

public class CategorySpinnerAdaptar extends BaseAdapter {

    List<Category> categoryspin = new ArrayList<>();
    LayoutInflater inflter;

    public CategorySpinnerAdaptar(List<Category> categoryspin, LayoutInflater inflter) {
        this.categoryspin = categoryspin;
        this.inflter = inflter;
    }

    @Override
    public int getCount() {
        return categoryspin.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryspin.get(position);
    }

    @Override
    public long getItemId(int position) {
        Long cat=Long.parseLong(categoryspin.get(position).getCategoryId());
        return cat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.customer_spinner_sample, null);
        TextView name=convertView.findViewById(R.id.textView);
        name.setText(categoryspin.get(position).getCategoryName());

        return convertView;
    }
}
