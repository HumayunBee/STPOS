package com.example.stpos.Adaptar.ProductAddAdaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stpos.DataModel.SubCategory.SubCategory;
import com.example.stpos.R;

import java.util.ArrayList;
import java.util.List;

public class SubcategorySpinnerAdaptar extends BaseAdapter {

    List<SubCategory> subsp = new ArrayList<>();
    LayoutInflater inflter;

    public SubcategorySpinnerAdaptar(List<SubCategory> subsp, LayoutInflater inflter) {
        this.subsp = subsp;
        this.inflter = inflter;
    }

    @Override
    public int getCount() {
        return subsp.size();
    }

    @Override
    public Object getItem(int position) {
        return subsp.get(position);
    }

    @Override
    public long getItemId(int position) {
        Long subc = Long.parseLong(subsp.get(position).getSubCategoryName());
        return subc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.customer_spinner_sample, null);
        TextView name=convertView.findViewById(R.id.textView);
        name.setText(subsp.get(position).getSubCategoryName());

        return convertView;
    }
}
