package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stpos.Adaptar.CustomerListAdaptar.CustomerListAdaptar;
import com.example.stpos.DataModel.Customer.Customer_Model_Container;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentCustomerListBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerListFragment extends Fragment {

    public FragmentCustomerListBinding binding;
    Api api;
    ProgressDialog progressDialog;


    public CustomerListFragment() {
        // Required empty public constructor
    }


    public static CustomerListFragment newInstance(String param1, String param2) {
        CustomerListFragment fragment = new CustomerListFragment();
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
//        return inflater.inflate(R.layout.fragment_customer_list, container, false);
        binding = FragmentCustomerListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  super.onViewCreated(view, savedInstanceState);
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        binding.addcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_addNewCustomerFragment);
            }
        });

//        observable();
        datainitialize();
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datainitialize();
                binding.swipe.setRefreshing(false);
            }
        });


    }

    private void datainitialize() {

        progressDialog.show();
        api.getcustomer().enqueue(new Callback<Customer_Model_Container>() {
            @Override
            public void onResponse(Call<Customer_Model_Container> call, Response<Customer_Model_Container> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()&&response.body()!=null){
                    binding.customerList.setHasFixedSize(true);
                    binding.customerList.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    CustomerListAdaptar adaptar= new CustomerListAdaptar(response.body().getCustomers(),getContext());
                    binding.customerList.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<Customer_Model_Container> call, Throwable t) {

            }
        });
    }
}