package com.example.stpos.Adaptar.CategoryListAdaptar;

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

import com.example.stpos.DataModel.Category.Category;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdaptar extends RecyclerView.Adapter<CategoryListAdaptar.Viewholders> {

    List<Category> categoryshow = new ArrayList<>();
    Context context;
    public CategoryListAdaptar.OnItemClickListener listener;

    public interface OnItemClickListener {

        void onItemClick(int position,String id);
    }

    public CategoryListAdaptar(List<Category> categoryshow, Context context,OnItemClickListener listener) {
        this.categoryshow = categoryshow;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_list_model_deisgn, parent, false);
        return new CategoryListAdaptar.Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {

        holder.categoryname.setText(categoryshow.get(position).getCategoryName());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Constant.CATEGORYNAME,categoryshow.get(position).getCategoryName());
                bundle.putString(Constant.CATEGORYID,categoryshow.get(position).getCategoryId());
                Navigation.findNavController(v).navigate(R.id.action_global_updateCategoryFragment,bundle);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position,categoryshow.get(position).getCategoryId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryshow.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {

        TextView categoryname;
        ImageView edit;
        ImageView delete;
        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);

            categoryname=itemView.findViewById(R.id.category_list_show);
            edit=itemView.findViewById(R.id.edit_category);
            delete=itemView.findViewById(R.id.delete_category);

        }
//        public ImageView getdelete(){
//            return this.delete;
//        }
    }
}
