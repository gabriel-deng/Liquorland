package com.example.liquorland.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.liquorland.Models.Drink;
import com.example.liquorland.ObjectBox;
import com.example.liquorland.R;

import java.util.ArrayList;

import io.objectbox.Box;


public class ItemDetailFragment extends Fragment {

    Context context = getContext();
    String name, volume, price, image;
    View root;
    TextView drinkname, drinkprice, drinkvolume;
    ImageView drinkimage, addcart;
    ArrayList<Drink> drinks;
    Box<Drink> detailsbox;
    Drink drink= new Drink();
    Uri uri;

    public ItemDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsbox = ObjectBox.get().boxFor(Drink.class);
        Bundle bundle=getArguments();

        name= bundle.getString("Drink name");
        volume= bundle.getString("Drink volume");
        price= bundle.getString("Drink price");
        image=bundle.getString("Drink image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_item_detail, container, false);

        drinkname = root.findViewById(R.id.txt_drinkname);
        drinkprice = root.findViewById(R.id.txt_drinkprice);
        drinkvolume = root.findViewById(R.id.drinkvolume);
        drinkimage= root.findViewById(R.id.ic_item_image);
        addcart= root.findViewById(R.id.ic_add_cart);

        drinkname.setText(name);
        drinkvolume.setText(volume);
        drinkprice.setText(price);
       drinkimage.setImageURI(Uri.parse(image));

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });

     return root;
    }

}