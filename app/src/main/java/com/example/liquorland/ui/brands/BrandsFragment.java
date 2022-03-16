package com.example.liquorland.ui.brands;


import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liquorland.Adapter.DrinksAdapter;

import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.databinding.BrandsFragmentBinding;

import java.util.ArrayList;

public class BrandsFragment extends Fragment {

    private BrandsViewModel brandsViewModel;
    private BrandsFragmentBinding binding;

    Context context= getContext();

    ArrayList<Drink> drinks= new ArrayList<>();
    DrinksAdapter drinksAdapter;
    RecyclerView drinkrecyclerview;

    TextView brands;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ArrayList<Drink> drinks= sampledrinks();

        brandsViewModel= new ViewModelProvider(this).get(BrandsViewModel.class);

        binding= BrandsFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();

        drinkrecyclerview= root.findViewById(R.id.drink_recyclerview);
        drinkrecyclerview.setNestedScrollingEnabled(true);
        drinkrecyclerview.setLayoutManager(new LinearLayoutManager(context));

        drinksAdapter= new DrinksAdapter(context, drinks);
        drinkrecyclerview.setAdapter(drinksAdapter);




        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        binding= null;
    }


    private ArrayList<Drink> sampledrinks(){
        ArrayList<Drink> samples= new ArrayList<>();

        samples.add(new Drink(" Jack Daniels", "1 litre", "ksh6,000", ""));
        samples.add(new Drink("Glen Grant", "750ml", "ksh4,800", ""));
        samples.add(new Drink("Caprice Dry White", "750ml", "ksh1,000", ""));
        samples.add(new Drink("Mamma Mia Red Sweet", "750ml", "ksh1,800", ""));
        samples.add(new Drink("Heineken Can", "500ml", "ksh275", ""));
        samples.add(new Drink("Bavaria Black", "350ml", "ksh500", ""));
        samples.add(new Drink("Smirnoff Double Black Ice", "250ml", "ksh200", ""));
        samples.add(new Drink("Remy Martin Vsop", "750ml", "ksh7,400", ""));
        samples.add(new Drink("Bisquit Classique", "700ml", "ksh4,000", ""));
        samples.add(new Drink("Martell Xo", "750ml", "ksh23,400", ""));
        samples.add(new Drink("Hennessy Vsop", "1 litre", "ksh11,900", ""));
        samples.add(new Drink("Baron otard Gold", "700ml", "ksh23,199", ""));
        samples.add(new Drink("Belvedere Blood Mary", "750ml", "ksh5,300", ""));
        samples.add(new Drink("Skyy Dragon Fruit", "750ml", "ksh1,800", ""));
        samples.add(new Drink("Absolut Kurant", "750ml", "ksh2,200", ""));
        samples.add(new Drink("White Mischief Vodka", "500ml", "ksh1,500", ""));
        samples.add(new Drink("KGB Vodka Caramel", "1 litre", "ksh4,000", ""));
        samples.add(new Drink("Raspberry Vodka", "500ml", "ksh3,500", ""));
        samples.add(new Drink("Bacardi Oro", "750ml", "ksh2,000", ""));
        samples.add(new Drink("Old Nick White Rum", "750ml", "ksh3,000", ""));
        samples.add(new Drink("Don Julio 1942", "750ml", "ksh4,000", ""));
        samples.add(new Drink("Olmeca Blanco", "750ml", "ksh4,000", ""));
        samples.add(new Drink("La Tilica Reposado", "750ml", "ksh6,300", ""));
        samples.add(new Drink("Laurent Pierre", "750ml", "ksh8,100", ""));
        samples.add(new Drink("Atec Tequila", "1 litre", "ksh3,489", ""));
        samples.add(new Drink("Taittinger Brunt", "750ml", "ksh8,000", ""));
        samples.add(new Drink("Hendricks Gin", "750ml", "ksh3,800", ""));
        samples.add(new Drink("Kwv Cruxland", "700ml", "ksh8,000", ""));
        samples.add(new Drink("Butlers Expresso", "750ml", "ksh2,000", ""));
        samples.add(new Drink("Bardinet De Cissis", "750ml", "ksh1,700", ""));
        samples.add(new Drink("Richot", "750ml", "ksh1,950", ""));
        samples.add(new Drink("Arak Taouma", "750ml", "ksh4,350", ""));

        return samples;
    }
}