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

import com.example.liquorland.Adapter.BrandsAdapter;
import com.example.liquorland.Models.CartItem;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
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
import java.util.List;


public class ListOfDrinksFragment extends Fragment {

// In this fragment a wanted to display items based on their category but was unable
// Now once you clicked a certain category from brands fragment you will get all the items irregardless of category


    Context context= getContext();

    List<Drink> drinks= new ArrayList<>();

    RecyclerView product_recyclerview;
    BrandsAdapter brandsAdapter;

    String name, drinkid;




    TextView categoryname;

    View root;

    DatabaseReference databaseReference, dataref;
    FirebaseAuth auth;

    public ListOfDrinksFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();

        name= bundle.getString("category name");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_list_of_drinks, container, false);
        categoryname=root.findViewById(R.id.txt_cat_name);
        categoryname.setText(name);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Drinks");


        product_recyclerview=root.findViewById(R.id.productlist_recyclerview);
        product_recyclerview.setNestedScrollingEnabled(true);
        product_recyclerview.setLayoutManager(new LinearLayoutManager(context));




        drinksincategory();


        return root;
    }

    public void goToDetailsfromlists(Drink drink){
        Bundle bundle= new Bundle();
        bundle.putString("Drink name", drink.getDrinkname());
        bundle.putString("Drink price",(drink.getDrinkprice()));
        bundle.putString("Drink volume",drink.getDrinkvolume());
        bundle.putString("Drink image",drink.getImageUrl());
        bundle.putString("Drink Id", drink.getId());

        ItemDetailFragment detailFragment = new ItemDetailFragment();
        detailFragment.setArguments(bundle);
        Navigation.findNavController(requireView()).navigate(R.id.itemDetailFragment, bundle);

    }


    public void drinksincategory(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                drinks.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                        Drink drink= dataSnapshot.getValue(Drink.class);
                        drinks.add(drink);
                }
                brandsAdapter= new BrandsAdapter(drinks, context, ListOfDrinksFragment.this);
                product_recyclerview.setAdapter(brandsAdapter);
                brandsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void AddDrinksToCart(Drink drink){
        auth= FirebaseAuth.getInstance();
        FirebaseUser mAuth= auth.getCurrentUser();

        dataref=  FirebaseDatabase.getInstance().getReference().child("Cart").child(mAuth.getUid());
        drinkid= drink.getId();

        String savecurrentTime,savecurrentDate;


        Calendar calForDate= Calendar.getInstance();

        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yy");
        savecurrentDate= currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss");
        savecurrentTime= currentTime.format(calForDate.getTime());


        CartItem cartItem= new CartItem();
        cartItem.setCartdrinkname(drink.getDrinkname());
        cartItem.setCartdrinkvolume(drink.getDrinkvolume());
        cartItem.setCartdrinkprice(drink.getDrinkprice());
        cartItem.setCartdrinkimage(drink.getImageUrl());
        cartItem.setDate(savecurrentDate);
        cartItem.setTime(savecurrentTime);
        cartItem.setCart_itemid(drinkid);

        dataref.child(drinkid).setValue(cartItem).addOnSuccessListener(new OnSuccessListener<Void>() {
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