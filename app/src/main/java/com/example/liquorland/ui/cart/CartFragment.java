package com.example.liquorland.ui.cart;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.liquorland.R;
import com.example.liquorland.databinding.CartFragmentBinding;

public class CartFragment extends Fragment implements View.OnClickListener {


    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;
    Context context;

    Button button;

    //View view;
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             Bundle savedInstanceState) {

        //view= getView();

        cartViewModel= new ViewModelProvider(this).get(CartViewModel.class);

        binding= CartFragmentBinding.inflate(inflater, container, false);
        root= binding.getRoot();

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


                   Navigation.findNavController(root).navigate(R.id.authenticationActivity);

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
}