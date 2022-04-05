package com.example.liquorland.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.liquorland.Models.Drink;
import com.example.liquorland.ObjectBox;
import com.example.liquorland.R;
import com.example.liquorland.onItemClickedListener;

import io.objectbox.Box;


public class ItemDetailFragment extends Fragment{
    View root;
    TextView drinkname, drinkprice, drinkvolume;
    Box<Drink> detailsbox;


    public ItemDetailFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsbox= ObjectBox.get().boxFor(Drink.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_item_detail, container, false);
        drinkname=root.findViewById(R.id.txt_drinkname);
        drinkprice=root.findViewById(R.id.txt_drinkprice);
        drinkvolume=root.findViewById(R.id.drinkvolume);


        return root;

    }

}