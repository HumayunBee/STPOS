package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stpos.Adaptar.Brand.BrandListAdaptar;
import com.example.stpos.Adaptar.Fund.FundListAdaptar;
import com.example.stpos.DataModel.Fund.FundContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentFundManageBinding;
import com.example.stpos.databinding.FragmentManageBrandBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FundManageFragment extends Fragment {

    public FragmentFundManageBinding binding;
    Api api;
    ProgressDialog progressDialog;

    public FundManageFragment() {
        // Required empty public constructor
    }


    public static FundManageFragment newInstance(String param1, String param2) {
        FundManageFragment fragment = new FundManageFragment();
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
        // return inflater.inflate(R.layout.fragment_fund_manage, container, false);
        binding = FragmentFundManageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        binding.addfund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_addFundFragment);
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
        api.fundlistshow().enqueue(new Callback<FundContainer>() {
            @Override
            public void onResponse(Call<FundContainer> call, Response<FundContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    binding.fundList.setHasFixedSize(true);
                    binding.fundList.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    FundListAdaptar adaptar= new FundListAdaptar(response.body().getFundList(), getContext(), new FundListAdaptar.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String id) {
                            api.deletefund(id).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    progressDialog.dismiss();
                                    if (response.isSuccessful()&& response.body()!=null){
                                        datainitialize();
                                        Toast.makeText(getContext(), "Fund Delete Successfull", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });

                        }
                    });
                    binding.fundList.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<FundContainer> call, Throwable t) {

            }
        });
    }
}