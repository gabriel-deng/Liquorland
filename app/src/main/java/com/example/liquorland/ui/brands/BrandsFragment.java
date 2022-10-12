package com.example.liquorland.ui.brands;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.liquorland.Adapter.CategoriesAdapter;
import com.example.liquorland.Models.Category;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.databinding.BrandsFragmentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.objectbox.Box;

public class BrandsFragment extends Fragment {

    private BrandsViewModel brandsViewModel;
    private BrandsFragmentBinding binding;


    Context context = getContext();

    TextView show;

    RecyclerView categoryrecyclerview;
    CategoriesAdapter categoriesAdapter;
    ArrayList<Category> categories= new ArrayList<>();



    Box<Drink> productname;


    DatabaseReference databaseReference;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {


        brandsViewModel = new ViewModelProvider(this).get(BrandsViewModel.class);

        binding = BrandsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

    //    productname = ObjectBox.get().boxFor(Drink.class);
        show= root.findViewById(R.id.txt_drinks);

        databaseReference = FirebaseDatabase.getInstance().getReference("category");



        categoryrecyclerview = root.findViewById(R.id.category_recyclerview);
        categoryrecyclerview.setNestedScrollingEnabled(true);
        categoryrecyclerview.setHasFixedSize(true);
        categoryrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categories.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Category category = dataSnapshot.getValue(Category.class);
                    categories.add(category);
                }
                categoriesAdapter = new CategoriesAdapter(categories, context, BrandsFragment.this);
                categoryrecyclerview.setAdapter(categoriesAdapter);
                categoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
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



   public void gotocategories(Category category){
        Bundle bundle= new Bundle();
        bundle.putString("category name", category.getCategory_name());
       Navigation.findNavController(requireView()).navigate(R.id.listOfDrinksFragment, bundle);

   }
}