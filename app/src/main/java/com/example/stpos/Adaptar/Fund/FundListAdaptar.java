package com.example.stpos.Adaptar.Fund;

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
import com.example.stpos.DataModel.Fund.Fund;
import com.example.stpos.R;
import com.example.stpos.Util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FundListAdaptar extends RecyclerView.Adapter<FundListAdaptar.Viewholders> {

    List<Fund> fundshow = new ArrayList<>();
    Context context;
    public FundListAdaptar.OnItemClickListener listener;
    public interface OnItemClickListener {

        void onItemClick(int position,String id);
    }

    public FundListAdaptar(List<Fund> fundshow, Context context, OnItemClickListener listener) {
        this.fundshow = fundshow;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_manage_model_design, parent, false);
        return new FundListAdaptar.Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {
        holder.fund.setText(fundshow.get(position).getFundName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Constant.CATEGORYNAME,fundshow.get(position).getFundName());
                bundle.putString(Constant.CATEGORYID,fundshow.get(position).getFundId());
                Navigation.findNavController(v).navigate(R.id.action_global_updateFundFragment,bundle);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position,fundshow.get(position).getFundId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fundshow.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {

        TextView fund;
        ImageView imageView,delete;

        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);

            fund = itemView.findViewById(R.id.fund_name);
            imageView=itemView.findViewById(R.id.edit_fund);
            delete=itemView.findViewById(R.id.delete_fund);
        }
    }
}
