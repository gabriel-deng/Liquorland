package com.example.liquorland;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.liquorland.Adapter.CartAdapter;
import com.example.liquorland.Models.CartItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class Checkout extends Fragment {



    Context context= getContext();
    ArrayList<CartItem> items= new ArrayList<>();
    RecyclerView cartrecyclerview;
    CartAdapter cartAdapter;

    ImageView back;



    View  view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Checkout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static Checkout newInstance(String param1, String param2) {
//        Checkout fragment = new Checkout();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View  root=inflater.inflate(R.layout.fragment_checkout, container, false);

        BottomNavigationView navView =root.findViewById(R.id.nav_view);
        back=root.findViewById(R.id.ic_back_cart);


        ArrayList<CartItem> items= samples();
        cartrecyclerview=root.findViewById(R.id.checkout_recyclerview);
        cartrecyclerview.setHasFixedSize(false);
        cartrecyclerview.setNestedScrollingEnabled(true);
        cartrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayoutManager.HORIZONTAL));
        cartAdapter= new CartAdapter(items, context);
        cartrecyclerview.setAdapter(cartAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.navigation_cart);
            }
        });

        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    private ArrayList<CartItem> samples(){
        ArrayList<CartItem> items= new ArrayList<>();
        items.add(new CartItem("xdvdfgg", "dkkefkef","ksefjelsd;", "ncjsdknvr", ""));
        items.add(new CartItem("xddddvdfgg", "dkkefkef","ksefjelsd;", "ncjsdknvr", ""));
        items.add(new CartItem("xddddvdfgg", "dkkefkef","ksefjelsd;", "ncjsdknvr", ""));
        items.add(new CartItem("xddddvdfgg", "dkkefkef","ksefjelsd;", "ncjsdknvr", ""));
        items.add(new CartItem("xddddvdfgg", "dkkefkef","ksefjelsd;", "ncjsdknvr", ""));
        return items;
    }

}