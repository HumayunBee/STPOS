package com.example.stpos.Activity;

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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.stpos.R;
import com.example.stpos.Util.MySharedPreference;
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
        navigationView = findViewById(R.id.navbar);

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
//        logout();


//        toolbar = findViewById(R.id.tv_toolbar_title);
//        toolbar.setText("Dashboard");
    }

    private void logout() {
        MySharedPreference.remove(getApplicationContext());
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        Toast.makeText(HomeActivity.this, "Please LogIn", Toast.LENGTH_SHORT).show();
       // Log.d("tesst", t.toString());
    }


    public void expandable() {

        LinearLayout logout=findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreference.editor(getApplicationContext()).clear().commit();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        ExpandableLayout expandableLayout
                = findViewById(R.id.expandable);
        expandableLayout.setExpandableAnimation(ExpandableAnimation.ACCELERATE);


        ExpandableLayout expandableLayoutdeli
                = findViewById(R.id.expandable_delivery);
        expandableLayoutdeli.setExpandableAnimation(ExpandableAnimation.ACCELERATE);

        ExpandableLayout expandableLayoutadvancecollect
                = findViewById(R.id.expandable_due_advance_collect);
        expandableLayoutadvancecollect.setExpandableAnimation(ExpandableAnimation.ACCELERATE);
        ExpandableLayout expandableLayoutcustomerledger
                = findViewById(R.id.expandable_customer_ledger);
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


        expandableLayoutsupplier.secondLayout.findViewById(R.id.add_new_supplier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_addNewSupplierFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsupplier.secondLayout.findViewById(R.id.make_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_makePaymentFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsupplier.secondLayout.findViewById(R.id.supplier_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_supplierHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsupplier.secondLayout.findViewById(R.id.manage_supplier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageSupplierListFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsupplier.secondLayout.findViewById(R.id.supplier_due_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_dueListSupplierFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutinventory.secondLayout.findViewById(R.id.manage_product).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageProductFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutinventory.secondLayout.findViewById(R.id.manage_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageCategoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutinventory.secondLayout.findViewById(R.id.manage_sub_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageSubCategoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutinventory.secondLayout.findViewById(R.id.manage_brand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageBrandFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutinventory.secondLayout.findViewById(R.id.stock_adjusment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_stockAdjusmentFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });


        //////expense child layout

        expandableLayoutexpense.secondLayout.findViewById(R.id.expense_debit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_expensDebitFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutexpense.secondLayout.findViewById(R.id.expense_manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_expenseManageFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutexpense.secondLayout.findViewById(R.id.expense_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
    ///////fund chile layout
        expandableLayoutfund.secondLayout.findViewById(R.id.manage_fund).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_fundManageFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });

        //////report child layout

        expandableLayoutreport.secondLayout.findViewById(R.id.stock_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_stockReportsFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.stock_expered_date_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_stockExpiredDateReportsFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.customer_list_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_customerListFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.customer_due_list_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_customerDueListFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.vat_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_vatReportFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.report_fund_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_reportFundHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.report_customer_ledger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_customerLedgerFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.purchase_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_purchaseHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.product_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_productReportFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.profit_loss_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_profitLossReportFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutreport.secondLayout.findViewById(R.id.new_profit_loss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_profitLossReportFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });

        //////setting child layout

        expandableLayoutsettings.secondLayout.findViewById(R.id.discount_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_discountSettingsFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsettings.secondLayout.findViewById(R.id.manage_user_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageUserFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsettings.secondLayout.findViewById(R.id.manage_cover_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_manageCoverPicFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsettings.secondLayout.findViewById(R.id.assign_customer_due).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_assignCustomerDueFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsettings.secondLayout.findViewById(R.id.assign_stock_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_assignStockValueFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsettings.secondLayout.findViewById(R.id.adjust_supplier_due).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_adjustSupplierDueFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutsettings.secondLayout.findViewById(R.id.note_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_notesSettingFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });

        expandableLayout.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayout.expand();
                expandableLayout.collapse();
            }
        });

        expandableLayout.secondLayout.findViewById(R.id.sales_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_salesReturnFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayout.secondLayout.findViewById(R.id.easy_sales).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        expandableLayout.secondLayout.findViewById(R.id.categorywise_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_categoryWiseFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayout.secondLayout.findViewById(R.id.counter_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_counterHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayout.secondLayout.findViewById(R.id.invoice_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_invoiceHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayout.secondLayout.findViewById(R.id.cancel_invoice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_cancelInvoiceFragment);
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

        expandableLayoutdeli.secondLayout.findViewById(R.id.order_place).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_orderPlaceFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });

        expandableLayoutdeli.secondLayout.findViewById(R.id.complete_order_place).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_completeOrderPlaceFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });

        expandableLayoutadvancecollect.parentLayout.findViewById(R.id.tv_nav_pickup_parcel_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_dueAndAdvancePaymentFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutpurchase.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayoutpurchase.expand();
                expandableLayoutpurchase.collapse();
            }
        });
        expandableLayoutpurchase.secondLayout.findViewById(R.id.puchase_child).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_global_purchaseFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutpurchase.secondLayout.findViewById(R.id.transfer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_global_transfarFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutpurchase.secondLayout.findViewById(R.id.purchase_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_global_purchaseHistoryFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });
        expandableLayoutpurchase.secondLayout.findViewById(R.id.purchase_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_global_purcahseReturnFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
            }
        });


        expandableLayoutcustomerledger.parentLayout.findViewById(R.id.customer_ledger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_customerLedgerFragment);
                drawerLayout.isOpen();
                drawerLayout.close();
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