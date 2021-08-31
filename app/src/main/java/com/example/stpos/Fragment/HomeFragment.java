package com.example.stpos.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stpos.R;
import com.skydoves.expandablelayout.ExpandableAnimation;
import com.skydoves.expandablelayout.ExpandableLayout;

public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


//    public void expandable() {
//        ExpandableLayout expandableLayout
//                = findViewById(R.id.expandable);
//        expandableLayout.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//
//        ExpandableLayout expandableLayoutdeli
//                = findViewById(R.id.expandable_delivery);
//        expandableLayoutdeli.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//        ExpandableLayout expandableLayoutadvancecollect
//                = findViewById(R.id.expandable_due_advance_collect);
//        expandableLayoutadvancecollect.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//        ExpandableLayout expandableLayoutpurchase
//                = findViewById(R.id.expandable_purchase);
//        expandableLayoutpurchase.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//        ExpandableLayout expandableLayoutsupplier
//                = findViewById(R.id.expandable_supplier);
//        expandableLayoutsupplier.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//        ExpandableLayout expandableLayoutinventory
//                = findViewById(R.id.expandable_product_inventory);
//        expandableLayoutinventory.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//        ExpandableLayout expandableLayoutexpense
//                = findViewById(R.id.expandable_expense);
//        expandableLayoutexpense.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//        ExpandableLayout expandableLayoutfund
//                = findViewById(R.id.expandable_fund);
//        expandableLayoutfund.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//        ExpandableLayout expandableLayoutreport
//                = findViewById(R.id.expandable_report);
//        expandableLayoutreport.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//        ExpandableLayout expandableLayoutsettings
//                = findViewById(R.id.expandable_settings);
//        expandableLayoutsettings.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
//
//
//        expandableLayout.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayout.expand();
//                expandableLayout.collapse();
//            }
//        });
//
//        expandableLayout.secondLayout.findViewById(R.id.easy_sales).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigation..navigate(R.id.action_global_easySalesCustomerFragment);
//                navController.navigate(R.id.action_global_easySalesCustomerFragment);
//            }
//        });
//
//        expandableLayoutdeli.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutdeli.expand();
//                expandableLayoutdeli.collapse();
//            }
//        });
//
//        expandableLayoutadvancecollect.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutadvancecollect.expand();
//                expandableLayoutadvancecollect.collapse();
//            }
//        });
//        expandableLayoutpurchase.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutpurchase.expand();
//                expandableLayoutpurchase.collapse();
//            }
//        });
//        expandableLayoutsupplier.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutsupplier.expand();
//                expandableLayoutsupplier.collapse();
//            }
//        });
//        expandableLayoutinventory.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutinventory.expand();
//                expandableLayoutinventory.collapse();
//            }
//        });
//        expandableLayoutexpense.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutexpense.expand();
//                expandableLayoutexpense.collapse();
//            }
//        });
//        expandableLayoutfund.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutfund.expand();
//                expandableLayoutfund.collapse();
//            }
//        });
//        expandableLayoutreport.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutreport.expand();
//                expandableLayoutreport.collapse();
//            }
//        });
//        expandableLayoutsettings.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                expandableLayoutsettings.expand();
//                expandableLayoutsettings.collapse();
//            }
//        });
//    }
}