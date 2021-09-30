package com.example.stpos.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.stpos.Adaptar.CustomerLedgerSpinner.CustomerLedgerSpinnerAdaptar;
import com.example.stpos.DataModel.Customer.Customer_Model_Container;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.databinding.FragmentSalesReturnBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SalesReturnFragment extends Fragment {


    public FragmentSalesReturnBinding binding;
    Api api;
    ProgressDialog progressDialog;
    Calendar myCalendar;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay;

    public SalesReturnFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SalesReturnFragment newInstance(String param1, String param2) {
        SalesReturnFragment fragment = new SalesReturnFragment();
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
       // return inflater.inflate(R.layout.fragment_sales_return, container, false);

        binding = FragmentSalesReturnBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        binding.startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar = Calendar.getInstance();
                mYear = myCalendar.get(Calendar.YEAR);
                mMonth = myCalendar.get(Calendar.MONTH);
                mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                int month = i1 + 1;
                                binding.startDate.setText(i + "-" + month + "-" + i2);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        api.getcustomer().enqueue(new Callback<Customer_Model_Container>() {
            @Override
            public void onResponse(Call<Customer_Model_Container> call, Response<Customer_Model_Container> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()&& response.body()!=null){
                    //customername.add(new Customer("","Select A Customer"));

                    CustomerLedgerSpinnerAdaptar adaptar=new CustomerLedgerSpinnerAdaptar(response.body().getCustomers(),
                            getLayoutInflater());
                    binding.customerNameSpinner.setAdapter(adaptar);
                    Log.d("spinner",response.body().getCustomers().toString());
                }
//                else {
//                    try {
//                        Log.e("spinner",response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
            }

            @Override
            public void onFailure(Call<Customer_Model_Container> call, Throwable t) {

            }
        });
    }
}