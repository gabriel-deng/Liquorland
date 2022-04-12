package com.example.liquorland.ui.cart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Adapter.CartAdapter;
import com.example.liquorland.Models.CartItem;
import com.example.liquorland.R;
import com.example.liquorland.databinding.CartFragmentBinding;

import java.util.ArrayList;

public class CartFragment extends Fragment implements View.OnClickListener {


    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;
    Context context= getContext();

    ArrayList<CartItem> items= new ArrayList<>();
    RecyclerView cartrecyclerview;
    CartAdapter cartAdapter;

    Button button;

    //View view;
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             Bundle savedInstanceState) {
        cartViewModel= new ViewModelProvider(this).get(CartViewModel.class);
        binding= CartFragmentBinding.inflate(inflater, container, false);
        root= binding.getRoot();
        ArrayList<CartItem> items= samples();

        cartrecyclerview=root.findViewById(R.id.cart_recyclerview);
        cartrecyclerview.setNestedScrollingEnabled(true);
        cartrecyclerview.setLayoutManager(new LinearLayoutManager(context));
        cartAdapter= new CartAdapter(items, context);
        cartrecyclerview.setAdapter(cartAdapter);


        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button= root.findViewById(R.id.button12);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               {

//                   ItemDetail itemDetail= new ItemDetail();
//
//                   FragmentManager fragmentManager= getFragmentManager();
//                   FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
//                   fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, itemDetail);
//                   fragmentTransaction.commit();


                   Navigation.findNavController(root).navigate(R.id.navigation_checkout);

//                    Intent intent= new Intent(getActivity(), AuthenticationActivity.class);
//                    startActivity(intent);
//


                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        binding= null;
    }

    @Override
    public void onClick(View v) {

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