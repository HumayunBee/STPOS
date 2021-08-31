package com.example.stpos;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.stpos.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.skydoves.expandablelayout.ExpandableAnimation;
import com.skydoves.expandablelayout.ExpandableLayout;

public class HomeActivity extends AppCompatActivity {

    //public ActivityHomeBinding binding;
    private AppBarConfiguration appBarConfiguration;
    NavController navController;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    ImageView imageView;
    NavigationView navigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerlayout);
        imageView = findViewById(R.id.tv_back);
        navigationView= findViewById(R.id.navbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        NavigationUI.setupWithNavController(navigationView, navController);
        appBarConfiguration = new AppBarConfiguration.Builder(new int[]

                {
                        R.id.homepage,}).

                setOpenableLayout(drawerLayout).

                build();
       // NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);


//        imageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });




//        easysales.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_global_easySalesCustomerFragment);
//            }
//        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        expandable();


//        toolbar = findViewById(R.id.tv_toolbar_title);
//        toolbar.setText("Dashboard");
    }

    public void expandable() {
        ExpandableLayout expandableLayout
                = findViewById(R.id.expandable);
        expandableLayout.setExpandableAnimation(ExpandableAnimation.ACCELERATE);


        ExpandableLayout expandableLayoutdeli
                = findViewById(R.id.expandable_delivery);
        expandableLayoutdeli.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutadvancecollect
                = findViewById(R.id.expandable_due_advance_collect);
        expandableLayoutadvancecollect.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutpurchase
                = findViewById(R.id.expandable_purchase);
        expandableLayoutpurchase.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutsupplier
                = findViewById(R.id.expandable_supplier);
        expandableLayoutsupplier.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutinventory
                = findViewById(R.id.expandable_product_inventory);
        expandableLayoutinventory.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutexpense
                = findViewById(R.id.expandable_expense);
        expandableLayoutexpense.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutfund
                = findViewById(R.id.expandable_fund);
        expandableLayoutfund.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutreport
                = findViewById(R.id.expandable_report);
        expandableLayoutreport.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
        ExpandableLayout expandableLayoutsettings
                = findViewById(R.id.expandable_settings);
        expandableLayoutsettings.setExpandableAnimation(ExpandableAnimation.ACCELERATE);


        expandableLayout.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayout.expand();
                expandableLayout.collapse();
            }
        });

        expandableLayout.secondLayout.findViewById(R.id.easy_sales).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigation..navigate(R.id.action_global_easySalesCustomerFragment);
                navController.navigate(R.id.action_global_easySalesCustomerFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });

        expandableLayout.secondLayout.findViewById(R.id.sales_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_salesHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();

            }
        });

        expandableLayout.secondLayout.findViewById(R.id.sales_payment_summary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.salesAndPaymentSummaryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });

        expandableLayoutdeli.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutdeli.expand();
                expandableLayoutdeli.collapse();
            }
        });

        expandableLayoutadvancecollect.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutadvancecollect.expand();
                expandableLayoutadvancecollect.collapse();
            }
        });
        expandableLayoutpurchase.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutpurchase.expand();
                expandableLayoutpurchase.collapse();
            }
        });
        expandableLayoutsupplier.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutsupplier.expand();
                expandableLayoutsupplier.collapse();
            }
        });
        expandableLayoutinventory.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutinventory.expand();
                expandableLayoutinventory.collapse();
            }
        });
        expandableLayoutexpense.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutexpense.expand();
                expandableLayoutexpense.collapse();
            }
        });
        expandableLayoutfund.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutfund.expand();
                expandableLayoutfund.collapse();
            }
        });
        expandableLayoutreport.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutreport.expand();
                expandableLayoutreport.collapse();
            }
        });
        expandableLayoutsettings.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutsettings.expand();
                expandableLayoutsettings.collapse();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}