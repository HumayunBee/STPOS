package com.example.stpos.Adaptar.ProductListAdaptar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stpos.DataModel.Product.Product;
import com.example.stpos.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductListAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Product> productlist = new ArrayList<>();
    Context context;
    public ProductListAdaptar.OnItemClickListener listener;
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;

    public interface OnItemClickListener {

        void onItemClick(int position, String id);
    }

    public ProductListAdaptar( Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.productlist=new LinkedList<>();
    }
    public void setProductList(List<Product> productlist) {
        this.productlist = productlist;
    }
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_product_model_deisgn, parent, false);


        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.manage_product_model_deisgn, parent, false);
                viewHolder = new Viewholders(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(viewLoading);
                break;
        }


        return viewHolder;



    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Log.e("tesst","from view holder1");
        Product product = productlist.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                Log.e("tesst","from view holder");
                Viewholders viewholders = (Viewholders) holder;
                viewholders.productname.setText(productlist.get(position).getProductName());
                viewholders.productid.setText(productlist.get(position).getProductId());
                viewholders.categoryname.setText(productlist.get(position).getCategoryName());
                viewholders.suppliername.setText(productlist.get(position).getSupplierName());
                viewholders.unit.setText(productlist.get(position).getProductUnit());
                viewholders.purchaserate.setText(productlist.get(position).getPurchaseRate());
                viewholders.salesrate.setText(productlist.get(position).getProductRate());

                Glide.with(context).load(productlist.get(position).getMainImage()).into(viewholders.productimage);
                break;

            case LOADING:
                Log.e("tesst","from view holder2");
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                break;
        }

    }

//    @Override
//    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {
//
//        holder.productname.setText(productlist.get(position).getProductName());
//        holder.productid.setText(productlist.get(position).getProductId());
//        holder.categoryname.setText(productlist.get(position).getCategoryName());
//        holder.suppliername.setText(productlist.get(position).getSupplierName());
//        holder.unit.setText(productlist.get(position).getProductUnit());
//        holder.purchaserate.setText(productlist.get(position).getPurchaseRate());
//        holder.salesrate.setText(productlist.get(position).getProductRate());
//
//        Glide.with(context).load(productlist.get(position).getMainImage()).into(holder.productimage);
//
//
//
//    }

    @Override
    public int getItemCount() {
        return productlist == null ? 0 : productlist.size();

    }

    @Override
    public int getItemViewType(int position) {
        return (position == productlist.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Product());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = productlist.size() - 1;
        Product result = getItem(position);

        if (result != null) {
            productlist.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(Product product) {
        productlist.add(product);
        notifyItemInserted(productlist.size() - 1);
    }

    public void addAll(List<Product> moveResults) {
        Log.e("tesst","from view holder23");
        for (Product result : moveResults) {
            Log.e("tesst","from view holder23");
            add(result);
        }
    }

    public Product getItem(int position) {
        return productlist.get(position);
    }



    public class Viewholders extends RecyclerView.ViewHolder {

        TextView productname, productid, categoryname, suppliername, unit, purchaserate, salesrate;

        ImageView productimage, edit, delete;

        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.pro_name);
            productid = itemView.findViewById(R.id.pro_id);
            categoryname = itemView.findViewById(R.id.pro_cat_name);
            suppliername = itemView.findViewById(R.id.pro_suppli_name);
            unit = itemView.findViewById(R.id.pro_unit);
            purchaserate = itemView.findViewById(R.id.pro_purchase_rate);
            salesrate = itemView.findViewById(R.id.pro_sale_rate);

            productimage = itemView.findViewById(R.id.product_image);
            edit = itemView.findViewById(R.id.pro_edit);
            delete = itemView.findViewById(R.id.pro_delete);


        }
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);

        }
    }
}
