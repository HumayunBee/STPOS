package com.example.stpos.Adaptar.SupllierList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stpos.Adaptar.CategoryListAdaptar.CategoryListAdaptar;
import com.example.stpos.DataModel.Category.Category;
import com.example.stpos.DataModel.Supplier.Supplier;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SupplierListAdaptar extends RecyclerView.Adapter<SupplierListAdaptar.ViewHolders> {

    List<Supplier> suppliersshow = new ArrayList<>();
    Context context;
    public SupplierListAdaptar.OnItemClickListener listener;

    public interface OnItemClickListener {

        void onItemClick(int position, String id);
    }

    public SupplierListAdaptar(List<Supplier> suppliersshow, Context context, OnItemClickListener listener) {
        this.suppliersshow = suppliersshow;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_supplier_model_design, parent, false);
        return new SupplierListAdaptar.ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolders holder, int position) {
        holder.supplier_name.setText(suppliersshow.get(position).getName());
        holder.company_name.setText(suppliersshow.get(position).getCompanyName());
        holder.cell_no.setText(String.valueOf(suppliersshow.get(position).getCellNo()));
        holder.supplier_address.setText(suppliersshow.get(position).getAddress());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.CATEGORYNAME, suppliersshow.get(position).getName());
                bundle.putString(Constant.COMPANY_NAME, suppliersshow.get(position).getCompanyName());
                bundle.putString(Constant.ADDRESS, suppliersshow.get(position).getAddress());
                bundle.putString(Constant.CELL_NO, suppliersshow.get(position).getCellNo());
                bundle.putString(Constant.ID, suppliersshow.get(position).getSupplierId());
                Navigation.findNavController(v).navigate(R.id.action_global_updateSupplierFragment, bundle);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, suppliersshow.get(position).getSupplierId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return suppliersshow.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {

        TextView supplier_name, company_name, cell_no, supplier_address;
        ImageView edit, delete;

        public ViewHolders(@NonNull @NotNull View itemView) {
            super(itemView);
            supplier_name = itemView.findViewById(R.id.supplier_name);
            company_name = itemView.findViewById(R.id.company_name);
            cell_no = itemView.findViewById(R.id.cell_no_supplier);
            supplier_address = itemView.findViewById(R.id.supplier_address);

            edit = itemView.findViewById(R.id.edit_supplier);
            delete = itemView.findViewById(R.id.delete_supplier);


        }
    }
}
