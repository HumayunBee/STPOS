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

import com.example.stpos.Adaptar.Expense.ExpenseHeadAdaptar;
import com.example.stpos.Adaptar.Fund.FundListAdaptar;
import com.example.stpos.DataModel.Expense.ExpenseContainer;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentExpenseManageBinding;
import com.example.stpos.databinding.FragmentFundManageBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseManageFragment extends Fragment {

    public FragmentExpenseManageBinding binding;
    Api api;
    ProgressDialog progressDialog;


    public ExpenseManageFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ExpenseManageFragment newInstance(String param1, String param2) {
        ExpenseManageFragment fragment = new ExpenseManageFragment();

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
        //  return inflater.inflate(R.layout.fragment_expense_manage, container, false);

        binding = FragmentExpenseManageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        binding.addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_global_addExHeadFragment);
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
        api.exheadlistshow().enqueue(new Callback<ExpenseContainer>() {
            @Override
            public void onResponse(Call<ExpenseContainer> call, Response<ExpenseContainer> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    binding.expenseHead.setHasFixedSize(true);
                    binding.expenseHead.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    ExpenseHeadAdaptar adaptar = new ExpenseHeadAdaptar(response.body().getExHeadList(), getContext(), new ExpenseHeadAdaptar.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String id) {
                            api.deleteexpensehead(id).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    progressDialog.dismiss();
                                    if (response.isSuccessful() && response.body() != null) {
                                        datainitialize();
                                        Toast.makeText(getContext(), "Expense Delete Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });
                        }
                    });
                    binding.expenseHead.setAdapter(adaptar);
                }
            }

            @Override
            public void onFailure(Call<ExpenseContainer> call, Throwable t) {

            }
        });
    }
}