package com.example.liquorland.ui.cart;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.liquorland.AuthenticationActivity;
import com.example.liquorland.Checkout;
import com.example.liquorland.ItemDetail;
import com.example.liquorland.R;
import com.example.liquorland.databinding.BrandsFragmentBinding;
import com.example.liquorland.databinding.CartFragmentBinding;
import com.example.liquorland.ui.brands.BrandsFragment;
import com.example.liquorland.ui.brands.BrandsViewModel;

public class CartFragment extends Fragment implements View.OnClickListener {


    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;
    Context context;

    Button button;

    View view;


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             Bundle savedInstanceState) {

        view= getView();

        cartViewModel= new ViewModelProvider(this).get(CartViewModel.class);

        binding= CartFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();

        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button= view.findViewById(R.id.button12);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
//                    Fragment itemdetail = new ItemDetail();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.replace(R.id.nav_host_fragment_activity_main, itemdetail);
//

                    Intent intent= new Intent(getActivity(), AuthenticationActivity.class);
                    startActivity(intent);



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
}