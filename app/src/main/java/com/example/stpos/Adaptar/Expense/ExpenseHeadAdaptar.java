package com.example.stpos.Adaptar.Expense;

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
import com.example.stpos.DataModel.Expense.ExHead;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExpenseHeadAdaptar extends RecyclerView.Adapter<ExpenseHeadAdaptar.Viewholders> {

    List<ExHead> exheadshow = new ArrayList<>();
    Context context;
    public ExpenseHeadAdaptar.OnItemClickListener listener;

    public interface OnItemClickListener {

        void onItemClick(int position,String id);
    }

    public ExpenseHeadAdaptar(List<ExHead> exheadshow, Context context,OnItemClickListener listener) {
        this.exheadshow = exheadshow;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_manage_model_design, parent, false);
        return new ExpenseHeadAdaptar.Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {

        holder.exhead.setText(exheadshow.get(position).getExHeadName());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Constant.CATEGORYNAME,exheadshow.get(position).getExHeadName());
                bundle.putString(Constant.CATEGORYID,exheadshow.get(position).getExHeadId());
                Navigation.findNavController(v).navigate(R.id.action_global_updateExpenseHeadFragment,bundle);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position,exheadshow.get(position).getExHeadId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return exheadshow.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {

        TextView exhead;

        ImageView edit,delete;
        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);

            exhead=itemView.findViewById(R.id.expensehead);
            edit=itemView.findViewById(R.id.edit_expense);
            delete=itemView.findViewById(R.id.delete_expense);
        }
    }
}
