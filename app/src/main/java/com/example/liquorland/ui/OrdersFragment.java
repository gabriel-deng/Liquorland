package com.example.liquorland.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Adapter.OrdersAdapter;
import com.example.liquorland.Models.Orders;
import com.example.liquorland.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {


    View root;

    TextView zero_orders;
    ArrayList<Orders> orders= new ArrayList<>();
    Context context=getContext();
    RecyclerView OrdersRecyclerview;
    OrdersAdapter ordersAdapter;
    FirebaseAuth auth;
    DatabaseReference ordersdb;


    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root =inflater.inflate(R.layout.fragment_orders, container, false);

        zero_orders= root.findViewById(R.id.txt_no_orders);
        OrdersRecyclerview= root.findViewById(R.id.orders_recyclerview);
        OrdersRecyclerview.setNestedScrollingEnabled(true);
        OrdersRecyclerview.setLayoutManager(new LinearLayoutManager(context));

        auth = FirebaseAuth.getInstance();
        FirebaseUser mAuth = auth.getCurrentUser();

        ordersdb= FirebaseDatabase.getInstance().getReference().child("Orders").child(mAuth.getUid());


        ordersdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(orders !=null){
                    orders.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Orders order= dataSnapshot.getValue(Orders.class);
                        orders.add(order);
                    }
                    ordersAdapter= new OrdersAdapter(context, orders, OrdersFragment.this);
                    OrdersRecyclerview.setAdapter(ordersAdapter);
                    ordersAdapter.notifyDataSetChanged();
                   }
                else {
                    OrdersRecyclerview.setVisibility(View.GONE);
                    zero_orders.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Canceled because of "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

    public void ViewSingleOrder(Orders orders){
        Bundle bundle= new Bundle();
        bundle.putString("Date", orders.getCreated_at());
        bundle.putString("Ref Number", orders.getRef_number());
        bundle.putString("Total Amount", orders.getPrice());
        bundle.putString("Delivery Status", orders.getDelivery_status());

        SingleOrderFragment singleOrderFragment= new SingleOrderFragment();
        singleOrderFragment.setArguments(bundle);
        Navigation.findNavController(requireView()).navigate(R.id.singleOrderFragment, bundle);
    }

}