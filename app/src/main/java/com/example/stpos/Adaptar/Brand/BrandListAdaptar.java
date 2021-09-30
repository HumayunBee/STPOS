package com.example.stpos.Adaptar.Brand;

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
import com.example.stpos.DataModel.Brand.Brand;
import com.example.stpos.DataModel.Category.Category;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BrandListAdaptar extends RecyclerView.Adapter<BrandListAdaptar.Viewholders> {
    List<Brand> brandshow = new ArrayList<>();
    Context context;

    public BrandListAdaptar.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position,String id);
    }

    public BrandListAdaptar(List<Brand> brandshow, Context context,OnItemClickListener listener) {
        this.brandshow = brandshow;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_name_model_deisgn, parent, false);
        return new BrandListAdaptar.Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {
        holder.brand.setText(brandshow.get(position).getBrandName());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Constant.CATEGORYNAME,brandshow.get(position).getBrandName());
                bundle.putString(Constant.CATEGORYID,brandshow.get(position).getBrandId());
                Navigation.findNavController(v).navigate(R.id.action_global_updateBrandFragment,bundle);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position,brandshow.get(position).getBrandId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandshow.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {

        TextView brand;

        ImageView edit,delete;

        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.b_name);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);

        }
    }
}
