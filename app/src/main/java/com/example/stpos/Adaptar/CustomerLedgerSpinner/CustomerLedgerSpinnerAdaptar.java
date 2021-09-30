package com.example.stpos.Adaptar.CustomerLedgerSpinner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stpos.DataModel.Customer.Customer;
import com.example.stpos.R;

import java.util.ArrayList;
import java.util.List;


public class CustomerLedgerSpinnerAdaptar extends BaseAdapter {

    List<Customer> customername = new ArrayList<>();
    LayoutInflater inflter;

    public CustomerLedgerSpinnerAdaptar(List<Customer> customername, LayoutInflater inflter) {
        this.customername = customername;
        this.inflter = inflter;
    }

    @Override
    public int getCount() {
        return customername.size();
    }

    @Override
    public Object getItem(int position) {
        return customername.get(position);
    }

    @Override
    public long getItemId(int position) {
        Long customer=Long.parseLong(customername.get(position).getCustomerId());
        return customer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.customer_spinner_sample, null);
        TextView name=convertView.findViewById(R.id.textView);
        name.setText(customername.get(position).getCustomerName());

        return convertView;
    }
}
