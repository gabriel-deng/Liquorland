package com.example.liquorland.ui.brands;


import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liquorland.Adapter.CategoriesAdapter;
import com.example.liquorland.Adapter.DrinksAdapter;

import com.example.liquorland.Models.Category;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.databinding.BrandsFragmentBinding;

import java.util.ArrayList;

public class BrandsFragment extends Fragment {

    private BrandsViewModel brandsViewModel;
    private BrandsFragmentBinding binding;

    Context context= getContext();

    ArrayList<Drink> drinks= new ArrayList<>();
    ArrayList<Category> categories= new ArrayList<>();
    CategoriesAdapter categoriesAdapter;
    RecyclerView categoryrecyclerview;
    DrinksAdapter drinksAdapter;
    RecyclerView drinkrecyclerview;

    TextView brands;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

    //    ArrayList<Drink> drinks= sampledrinks();
        ArrayList<Category> categories= actualcategories();

        brandsViewModel= new ViewModelProvider(this).get(BrandsViewModel.class);

        binding= BrandsFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();


        categoryrecyclerview=root.findViewById(R.id.category_recyclerview);
        categoryrecyclerview.setNestedScrollingEnabled(true);
        categoryrecyclerview.setLayoutManager(new GridLayoutManager(context,2));

        categoriesAdapter= new CategoriesAdapter(categories,context);
        categoryrecyclerview.setAdapter(categoriesAdapter);


        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        binding= null;
    }



    private  ArrayList<Category> actualcategories(){
        ArrayList<Category> names= new ArrayList<>();

        names.add(new Category("CHAMPAGNE"));
        names.add(new Category("BRANDY"));
        names.add(new Category("BEER"));
        names.add(new Category("CIDER"));
        names.add(new Category("WINE"));
        names.add(new Category("SPIRITS"));
        names.add(new Category("WHISKY"));
        names.add(new Category("COGNAC"));
        names.add(new Category("VODKA"));
        names.add(new Category("RUM"));
        names.add(new Category("GIN"));
        names.add(new Category("TEQUILA"));
        return  names;
    }
}