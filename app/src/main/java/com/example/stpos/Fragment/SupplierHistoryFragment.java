package com.example.stpos.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stpos.R;


public class SupplierHistoryFragment extends Fragment {



    public SupplierHistoryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SupplierHistoryFragment newInstance(String param1, String param2) {
        SupplierHistoryFragment fragment = new SupplierHistoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supplier_history, container, false);
    }
}