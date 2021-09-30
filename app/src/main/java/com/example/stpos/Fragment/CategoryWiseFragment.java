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
import com.example.stpos.Adaptar.SalesCategorywiseAdaptar.CategorywiseAdaptar;
import com.example.stpos.DataModel.Category.CategoryContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentCategoryWiseBinding;
import com.example.stpos.databinding.FragmentSalesHistoryBinding;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryWiseFragment extends Fragment {

    public FragmentCategoryWiseBinding binding;
    Api api;
    ProgressDialog progressDialog;
    Calendar myCalendar;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay;


    public CategoryWiseFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CategoryWiseFragment newInstance(String param1, String param2) {
        CategoryWiseFragment fragment = new CategoryWiseFragment();

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
       // return inflater.inflate(R.layout.fragment_category_wise, container, false);

        binding = FragmentCategoryWiseBinding.inflate(inflater, container, false);
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

        binding.endDate.setOnClickListener(new View.OnClickListener() {
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
                                binding.endDate.setText(i + "-" + month + "-" + i2);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        api.categorylist().enqueue(new Callback<CategoryContainer>() {
            @Override
            public void onResponse(Call<CategoryContainer> call, Response<CategoryContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()&& response.body()!=null){
                    CategorywiseAdaptar adaptar=new CategorywiseAdaptar(response.body().getCategoryList(),
                            getLayoutInflater());
                    binding.categorySpinner.setAdapter(adaptar);
                }
                else {
                    try {
                        Log.e("categorylistspinner",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryContainer> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}