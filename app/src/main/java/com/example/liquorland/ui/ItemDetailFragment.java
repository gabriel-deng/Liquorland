package com.example.liquorland.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.liquorland.Models.CartItem;
import com.example.liquorland.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ItemDetailFragment extends Fragment {

    Context context = getContext();
    String name, volume, price, image;
    View root;
    TextView drinkname, drinkprice, drinkvolume;
    ImageView drinkimage, addcart;

    String drinkid;


    Integer id;
    FirebaseAuth auth;
    DatabaseReference mydb;



    public ItemDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    detailsbox = ObjectBox.get().boxFor(Drink.class);
        Bundle bundle = getArguments();

        name = bundle.getString("Drink name");
        volume = bundle.getString("Drink volume");
        price = bundle.getString("Drink price");
        image = bundle.getString("Drink image");
        drinkid= bundle.getString("Drink Id");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_item_detail, container, false);


        drinkname = root.findViewById(R.id.txt_drinkname);
        drinkprice = root.findViewById(R.id.txt_drinkprice);
        drinkvolume = root.findViewById(R.id.drinkvolume);
        drinkimage = root.findViewById(R.id.ic_drink_image_upload);
        addcart = root.findViewById(R.id.ic_add_tocart);

        drinkname.setText(name);
        drinkvolume.setText(volume);
        drinkprice.setText(price);
        Glide.with(root).load(image).into(drinkimage);

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddtocartfromItemDetail();

            }
        });


        return root;
    }
    public void AddtocartfromItemDetail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            String uid = user.getUid();


        String savecurrentTime,savecurrentDate;

        Calendar calForDate= Calendar.getInstance();

        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yy");
        savecurrentDate= currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss");
        savecurrentTime= currentTime.format(calForDate.getTime());

        mydb= FirebaseDatabase.getInstance().getReference().child("Cart").child(uid);

        CartItem cartItem= new CartItem();
        cartItem.setCartdrinkname(name);
        cartItem.setCartdrinkvolume(volume);
        cartItem.setCartdrinkprice(price);
        cartItem.setCartdrinkimage(image);
        cartItem.setDate(savecurrentDate);
        cartItem.setTime(savecurrentTime);
        cartItem.setCart_itemid(drinkid);

      mydb.child(drinkid).setValue(cartItem).addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void unused) {

         //     Navigation.findNavController(requireView()).navigate(R.id.navigation_cart, bundle);

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

