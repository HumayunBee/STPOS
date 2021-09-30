package com.example.stpos.Adaptar.SalesCategorywiseAdaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stpos.DataModel.Category.Category;
import com.example.stpos.DataModel.Customer.Customer;
import com.example.stpos.R;

import java.util.ArrayList;
import java.util.List;

public class CategorywiseAdaptar extends BaseAdapter {

    List<Category> categoryname = new ArrayList<>();
    LayoutInflater inflter;

    public CategorywiseAdaptar(List<Category> categoryname, LayoutInflater inflter) {
        this.categoryname = categoryname;
        this.inflter = inflter;
    }

    @Override
    public int getCount() {
        return categoryname.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryname.get(position);
    }

    @Override
    public long getItemId(int position) {
        Long category=Long.parseLong(categoryname.get(position).getCategoryId());
        return category;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.customer_spinner_sample, null);
        TextView name=convertView.findViewById(R.id.textView);
        name.setText(categoryname.get(position).getCategoryName());

        return convertView;
    }
}
