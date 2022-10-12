package com.example.liquorland.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.liquorland.Adapter.DrinksAdapter;
import com.example.liquorland.Models.CartItem;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.databinding.FragmentHomeBinding;
import com.example.liquorland.ui.ItemDetailFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    Context context;

    String drinkid;

    TextView see_all;
    ImageView offers;

    ArrayList<Drink> drinks= new ArrayList<>();
    DrinksAdapter drinksAdapter;
    RecyclerView drinkrecyclerview;


    DatabaseReference mbase, mydb;
    FirebaseAuth auth;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        root = inflater.inflate(R.layout.fragment_home, container, false);


        mbase = FirebaseDatabase.getInstance().getReference("Drinks");


        drinkrecyclerview= root.findViewById(R.id.drinks_recyclerview);
        drinkrecyclerview.setNestedScrollingEnabled(true);
        drinkrecyclerview.setHasFixedSize(true);
        drinkrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));


        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                drinks.clear();
                 for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                     Drink drink = dataSnapshot.getValue(Drink.class);
                     drinks.add(drink);
                 }
                drinksAdapter= new DrinksAdapter(drinks, context, HomeFragment.this);
                drinkrecyclerview.setAdapter(drinksAdapter);
                drinksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
    }

@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {                                    
        super.onViewCreated(view, savedInstanceState);

        see_all=view.findViewById(R.id.txt_see_all);
        see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Navigation.findNavController(requireView()).navigate(R.id.navigation_brands);

            }
        });
        offers=view.findViewById(R.id.img_offer);
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(requireView()).navigate(R.id.navigation_brands);

            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    public void goToDetails(Drink drink){

        Bundle bundle= new Bundle();
        bundle.putString("Drink name", drink.getDrinkname());
        bundle.putString("Drink price",drink.getDrinkprice());
        bundle.putString("Drink volume",drink.getDrinkvolume());
        bundle.putString("Drink image",drink.getImageUrl());
        bundle.putString("Drink Id", drink.getId());

        ItemDetailFragment detailFragment = new ItemDetailFragment();
        detailFragment.setArguments(bundle);
        Navigation.findNavController(requireView()).navigate(R.id.itemDetailFragment, bundle);

    }





    public void AddToCart(Drink drink){

        auth= FirebaseAuth.getInstance();
        FirebaseUser mAuth= auth.getCurrentUser();


        String savecurrentTime,savecurrentDate;

        Calendar calForDate= Calendar.getInstance();

        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yy");
        savecurrentDate= currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss");
        savecurrentTime= currentTime.format(calForDate.getTime());

        mydb= FirebaseDatabase.getInstance().getReference().child("Cart").child(mAuth.getUid());
        drinkid= drink.getId();

        CartItem cartItem= new CartItem();
        cartItem.setCartdrinkname(drink.getDrinkname());
        cartItem.setCartdrinkvolume(drink.getDrinkvolume());
        cartItem.setCartdrinkprice(drink.getDrinkprice());
        cartItem.setCartdrinkimage(drink.getImageUrl());
        cartItem.setDate(savecurrentDate);
        cartItem.setTime(savecurrentTime);
        cartItem.setCart_itemid(drinkid);

        mydb.child(drinkid).setValue(cartItem).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(getContext(), "Drink successfully added to your cart", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed because  "+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}