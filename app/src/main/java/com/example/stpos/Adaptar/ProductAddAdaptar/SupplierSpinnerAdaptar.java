package com.example.stpos.Adaptar.ProductAddAdaptar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stpos.DataModel.Supplier.Supplier;
import com.example.stpos.R;

import java.util.ArrayList;
import java.util.List;

public class SupplierSpinnerAdaptar extends BaseAdapter {

    List<Supplier> supplierpro=new ArrayList<>();
    LayoutInflater inflater;

    public SupplierSpinnerAdaptar(List<Supplier> supplierpro, LayoutInflater inflater) {
        this.supplierpro = supplierpro;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return supplierpro.size();
    }

    @Override
    public Object getItem(int position) {
        return supplierpro.get(position);
    }

    @Override
    public long getItemId(int position) {

        Long suppli=Long.parseLong(supplierpro.get(position).getName());
        return suppli;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.customer_spinner_sample, null);
        TextView name = convertView.findViewById(R.id.textView);
        name.setText(supplierpro.get(position).getName());
        return convertView;
    }
}
