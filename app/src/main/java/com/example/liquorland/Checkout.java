package com.example.liquorland;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liquorland.Adapter.DrinksAdapter;
import com.example.liquorland.Models.Drink;

import java.util.ArrayList;
import java.util.List;


public class Checkout extends Fragment {



    Context context= getContext();
    List<Drink> drinks;
    DrinksAdapter drinksAdapter;
    RecyclerView drinkrecyclerview;

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

    //  ArrayList<Drink> drinks= sampledrinks();

        drinkrecyclerview=root.findViewById(R.id.checkout_recyclerview);
        drinkrecyclerview.setNestedScrollingEnabled(true);
        drinkrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));
        drinksAdapter= new DrinksAdapter(drinks, context);
        drinkrecyclerview.setAdapter(drinksAdapter);
        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private ArrayList<Drink> sampledrinks(){
        ArrayList<Drink> samples= new ArrayList<>();

        samples.add(new Drink(" Jack Daniels", "1 litre", "ksh6,000", "", "",""));
        samples.add(new Drink("Glen Grant", "750ml", "ksh4,800", "", "",""));
        samples.add(new Drink("Caprice Dry White", "750ml", "ksh1,000", "","",""));
        samples.add(new Drink("Mamma Mia Red Sweet", "750ml", "ksh1,800", "", "",""));
        samples.add(new Drink("Heineken Can", "500ml", "ksh275", "", "",""));
        samples.add(new Drink("Bavaria Black", "350ml", "ksh500", "", "",""));
        samples.add(new Drink("Smirnoff Double Black Ice", "250ml", "ksh200", "", "",""));
        samples.add(new Drink("Remy Martin Vsop", "750ml", "ksh7,400", "", "",""));
        samples.add(new Drink("Bisquit Classique", "700ml", "ksh4,000", "", "",""));
        samples.add(new Drink("Martell Xo", "750ml", "ksh23,400", "", "",""));
        samples.add(new Drink("Hennessy Vsop", "1 litre", "ksh11,900", "", "",""));
        samples.add(new Drink("Baron otard Gold", "700ml", "ksh23,199", "", "",""));
        samples.add(new Drink("Belvedere Blood Mary", "750ml", "ksh5,300", "", "",""));
        samples.add(new Drink("Skyy Dragon Fruit", "750ml", "ksh1,800", "", "",""));
        samples.add(new Drink("Absolut Kurant", "750ml", "ksh2,200", "", "",""));
        samples.add(new Drink("White Mischief Vodka", "500ml", "ksh1,500", "", "",""));
        samples.add(new Drink("KGB Vodka Caramel", "1 litre", "ksh4,000", "", "",""));
        samples.add(new Drink("Raspberry Vodka", "500ml", "ksh3,500", "", "",""));
        samples.add(new Drink("Bacardi Oro", "750ml", "ksh2,000", "", "",""));
        samples.add(new Drink("Old Nick White Rum", "750ml", "ksh3,000", "", "",""));
        samples.add(new Drink("Don Julio 1942", "750ml", "ksh4,000", "", "",""));
        samples.add(new Drink("Olmeca Blanco", "750ml", "ksh4,000", "", "",""));
        samples.add(new Drink("La Tilica Reposado", "750ml", "ksh6,300", "", "",""));
        samples.add(new Drink("Laurent Pierre", "750ml", "ksh8,100", "", "",""));
        samples.add(new Drink("Atec Tequila", "1 litre", "ksh3,489", "", "",""));

        return samples;
    }



}