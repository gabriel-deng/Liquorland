package com.example.liquorland.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.liquorland.R;


public class SingleOrderFragment extends Fragment {
  View root;
    String ref_num, date, total, dev_status;

    TextView OrderDate, OrderRefNum, OrderTotal, OrderStatus;

    public SingleOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getArguments();

        date=bundle.getString("Date");
        ref_num= bundle.getString("Ref Number");
        total=bundle.getString("Total Amount");
        dev_status=bundle.getString("Delivery Status");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_single_order, container, false);

        OrderDate= root.findViewById(R.id.txt_date);
        OrderRefNum=root.findViewById(R.id.txt_refnum);
        OrderTotal=root.findViewById(R.id.txt_order_amount);
        OrderStatus=root.findViewById(R.id.txt_delivery_status);

        OrderDate.setText(date);
        OrderRefNum.setText(ref_num);
        OrderTotal.setText(total);
        OrderStatus.setText(dev_status);

        return root;
    }
}