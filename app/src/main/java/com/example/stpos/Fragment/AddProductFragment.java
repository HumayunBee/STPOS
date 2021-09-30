package com.example.stpos.Fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stpos.DataModel.Brand.Brand;
import com.example.stpos.DataModel.Category.Category;
import com.example.stpos.DataModel.SubCategory.SubCategory;
import com.example.stpos.DataModel.Supplier.Supplier;
import com.example.stpos.Network.Api;
import com.example.stpos.Network.RetrofitClient;
import com.example.stpos.R;
import com.example.stpos.databinding.FragmentAddBrandBinding;
import com.example.stpos.databinding.FragmentAddProductBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AddProductFragment extends Fragment {

    public FragmentAddProductBinding binding;
    Api api;
    ProgressDialog progressDialog;
    List<Brand> brandpro;
    List<Category> categoryspin;
    List<SubCategory> subsp;
    List<Supplier> supplierpro;
    Bitmap bitmap;
    boolean mainimage = false, detailsimage = false;
    Uri imageUrimain = null, imageUridetails = null;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    public AddProductFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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
        // return inflater.inflate(R.layout.fragment_add_product, container, false);
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        api = RetrofitClient.get(getContext()).create(Api.class);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);


    }
}