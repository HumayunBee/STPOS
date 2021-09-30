package com.example.stpos.Adaptar.ProductListAdaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stpos.Adaptar.CategoryListAdaptar.CategoryListAdaptar;
import com.example.stpos.DataModel.Product.Product;
import com.example.stpos.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdaptarProduct extends RecyclerView.Adapter<AdaptarProduct.Viewholders> {

    List<Product> productlist = new ArrayList<>();
    Context context;
    public AdaptarProduct.OnItemClickListener listener;

    public interface OnItemClickListener {

        void onItemClick(int position, String id);
    }

    public AdaptarProduct(List<Product> productlist, Context context, AdaptarProduct.OnItemClickListener listener) {
        this.productlist = productlist;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_product_model_deisgn, parent, false);
        return new AdaptarProduct.Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {
        holder.productname.setText(productlist.get(position).getProductName());
        holder.productid.setText(productlist.get(position).getProductId());
        holder.categoryname.setText(productlist.get(position).getCategoryName());
        holder.suppliername.setText(productlist.get(position).getSupplierName());
        holder.unit.setText(productlist.get(position).getProductUnit());
        holder.purchaserate.setText(productlist.get(position).getPurchaseRate());
        holder.salesrate.setText(productlist.get(position).getProductRate());

        Glide.with(context).load(productlist.get(position).getMainImage()).into(holder.productimage);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position,productlist.get(position).getProductId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productlist.size();
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
}
