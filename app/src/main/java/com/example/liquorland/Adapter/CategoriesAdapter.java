package com.example.liquorland.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.Category;
import com.example.liquorland.R;
import com.example.liquorland.ui.brands.BrandsFragment;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    ArrayList<Category> categories;
    Context context;
    BrandsFragment brandsFragment;

    public CategoriesAdapter(ArrayList<Category> categories, Context context, BrandsFragment brandsFragment) {
        this.categories = categories;
        this.context = context;
        this.brandsFragment = brandsFragment;
    }


    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate( R.layout.category_item, parent, false);

        return new  CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {

        Category category= categories.get(position);

        holder.categoryName.setText(category.getCategory_name());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName=itemView.findViewById(R.id.txt_category_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    brandsFragment.gotocategories(categories.get(getAbsoluteAdapterPosition()));
                }
            });
        }
    }
}

