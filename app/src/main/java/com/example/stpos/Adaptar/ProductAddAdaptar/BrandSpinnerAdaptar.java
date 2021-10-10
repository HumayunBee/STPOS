package com.example.stpos.Adaptar.ProductAddAdaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stpos.DataModel.Brand.Brand;
import com.example.stpos.R;

import java.util.ArrayList;
import java.util.List;

public class BrandSpinnerAdaptar extends BaseAdapter {

    List<Brand> brandpro = new ArrayList<>();
    LayoutInflater inflater;

    public BrandSpinnerAdaptar(List<Brand> brandpro, LayoutInflater inflater) {
        this.brandpro = brandpro;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return brandpro.size();
    }

    @Override
    public Object getItem(int position) {
        return brandpro.get(position).getBrandName();
    }

    @Override
    public long getItemId(int position) {
        Long brands = Long.parseLong(brandpro.get(position).getBrandId());
        return brands;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.customer_spinner_sample, null);
        TextView name = convertView.findViewById(R.id.textView);
        name.setText(brandpro.get(position).getBrandName());

        return convertView;
    }
}
