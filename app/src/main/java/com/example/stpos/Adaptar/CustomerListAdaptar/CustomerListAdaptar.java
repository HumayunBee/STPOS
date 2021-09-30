package com.example.stpos.Adaptar.CustomerListAdaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stpos.DataModel.Customer.Customer;
import com.example.stpos.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdaptar extends RecyclerView.Adapter<CustomerListAdaptar.Viewholders> {

    List<Customer> customershow = new ArrayList<>();
    Context context;

    public CustomerListAdaptar(List<Customer> customershow, Context context) {
        this.customershow = customershow;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_model_design, parent, false);
        return new CustomerListAdaptar.Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholders holder, int position) {
       // holder.textView.setText(noticeshow.get(position).getTitle());

        holder.CustomerName.setText(customershow.get(position).getCustomerName());
        holder.MembershipNo.setText(customershow.get(position).getMembershipNo());
        holder.CellNo.setText(customershow.get(position).getCellNo());
        holder.CustomerAddress.setText(customershow.get(position).getAddress());
        holder.CustomerPoint.setText(customershow.get(position).getPoint());

    }

    @Override
    public int getItemCount() {
        return customershow.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {

        TextView CustomerName,MembershipNo,CellNo,CustomerAddress,CustomerPoint;


        public Viewholders(@NonNull @NotNull View itemView) {
            super(itemView);

            CustomerName=itemView.findViewById(R.id.customer_name);
            MembershipNo=itemView.findViewById(R.id.membership_no);
            CellNo=itemView.findViewById(R.id.cell_no);
            CustomerAddress=itemView.findViewById(R.id.customer_address);
            CustomerPoint=itemView.findViewById(R.id.customer_point);

        }
    }
}
