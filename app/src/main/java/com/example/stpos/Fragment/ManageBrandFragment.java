package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stpos.Adaptar.Brand.BrandListAdaptar;
import com.example.stpos.Adaptar.CategoryListAdaptar.CategoryListAdaptar;
import com.example.stpos.DataModel.Brand.BrandContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentManageBrandBinding;
import com.example.stpos.databinding.FragmentManageCategoryBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManageBrandFragment extends Fragment {

    public FragmentManageBrandBinding binding;
    Api api;
    ProgressDialog progressDialog;

    public ManageBrandFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ManageBrandFragment newInstance(String param1, String param2) {
        ManageBrandFragment fragment = new ManageBrandFragment();
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
        // return inflater.inflate(R.layout.fragment_manage_brand, container, false);
        binding = FragmentManageBrandBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        binding.addBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_addBrandFragment);
            }
        });

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datainitialize();
                binding.swipe.setRefreshing(false);
            }
        });
        datainitialize();
    }

    private void datainitialize() {
        progressDialog.show();
        api.brandlist().enqueue(new Callback<BrandContainer>() {
            @Override
            public void onResponse(Call<BrandContainer> call, Response<BrandContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    binding.brandl.setHasFixedSize(true);
                    binding.brandl.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    BrandListAdaptar adaptar = new BrandListAdaptar(response.body().getBrandList(), getContext(), new BrandListAdaptar.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String id) {
                            api.deletebrand(id).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    progressDialog.dismiss();
                                    if (response.isSuccessful() && response.body() != null) {

                                        datainitialize();
                                        Toast.makeText(getContext(), "Brand Delete", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });

                        }
                    });
                    binding.brandl.setAdapter(adaptar);
                } else {
                    try {
                        Log.e("brand", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BrandContainer> call, Throwable t) {

            }
        });
    }
}