package com.example.liquorland.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.Orders;
import com.example.liquorland.R;
import com.example.liquorland.ui.OrdersFragment;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    Context context;
    List<Orders> orders;
    OrdersFragment ordersFragment;

    public OrdersAdapter(Context context, ArrayList<Orders> orders, OrdersFragment ordersFragment) {
        this.context = context;
        this.orders = orders;
        this.ordersFragment = ordersFragment;
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate( R.layout.order_item, parent, false);

        return new OrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        Orders order= orders.get(position);

        holder.created_at.setText(order.getCreated_at());
        holder.ref_number.setText(order.getRef_number());
        holder.delivery_status.setText(order.getDelivery_status());

    }

    @Override
    public int getItemCount() {
        if(orders !=null){
            return orders.size();
        }
          return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView created_at, ref_number,delivery_status;
        Integer position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            created_at=itemView.findViewById(R.id.txt_created_at);
            ref_number=itemView.findViewById(R.id.txt_ref_num);
            delivery_status=itemView.findViewById(R.id.txt_delivery);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ordersFragment.ViewSingleOrder(orders.get(getAbsoluteAdapterPosition()));
                }
            });
        }
    }
}
