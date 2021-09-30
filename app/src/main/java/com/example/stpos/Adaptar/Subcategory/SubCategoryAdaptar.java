package com.example.stpos.Adaptar.Subcategory;

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
import com.example.stpos.DataModel.SubCategory.SubCategory;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdaptar extends RecyclerView.Adapter<SubCategoryAdaptar.Viewholders> {

    List<SubCategory> subcategoryshow = new ArrayList<>();
    Context context;
    public SubCategoryAdaptar.OnItemClickListener listener;

    public interface OnItemClickListener {

        void onItemClick(int position, String id);
    }

    public SubCategoryAdaptar(List<SubCategory> subcategoryshow,Context context,OnItemClickListener listener) {
        this.subcategoryshow = subcategoryshow;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_list_model_deisgn, parent, false);
        return new SubCategoryAdaptar.Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {

        holder.subname.setText(subcategoryshow.get(position).getSubCategoryName());
        holder.categoryname.setText(subcategoryshow.get(position).getCategory_name());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Constant.CATEGORYNAME,subcategoryshow.get(position).getSubCategoryName());
                bundle.putString(Constant.CATEGORYID,subcategoryshow.get(position).getCategoryId());
                bundle.putString(Constant.SUBCATEGORYID,subcategoryshow.get(position).getSubCategoryId());
                Navigation.findNavController(v).navigate(R.id.action_global_updateSubCategoryFragment,bundle);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position,subcategoryshow.get(position).getSubCategoryId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return subcategoryshow.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {

        TextView categoryname, subname;
        ImageView delete,edit;

        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);

            categoryname = itemView.findViewById(R.id.c_name);
            subname = itemView.findViewById(R.id.sub_name);
            delete=itemView.findViewById(R.id.delete_subcategory);
            edit=itemView.findViewById(R.id.edit_sub);

        }
    }
}
