package com.example.liquorland.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.Category;
import com.example.liquorland.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    List<Category> categories;
    Context context;

    public CategoriesAdapter() {
    }

    public CategoriesAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate( R.layout.category_item, parent, false);


        return new CategoriesAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        Category category= categories.get(position);
        holder.category_name.setText(categories.get(position).getCategory_name());
        holder.position=holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
       return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      Chip category_name;
        Integer position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name=itemView.findViewById(R.id.txt_category_name);

        }
    }
}
