package com.example.liquorland.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.ui.brands.BrandsFragment;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.ViewHolder> {
    List<Drink> drinks;
    Context context;
    BrandsFragment brandsFragment;




    public BrandsAdapter(List<Drink> drinks, Context context, BrandsFragment brandsFragment) {
        this.drinks = drinks;
        this.context = context;
        this.brandsFragment = brandsFragment;
    }



    @NonNull
    @Override
    public BrandsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate( R.layout.brands_item, parent, false);


        return new BrandsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BrandsAdapter.ViewHolder holder, int position) {
        Drink drink=drinks.get(position);

        holder.drinkname.setText(drinks.get(position).getDrinkname());
        holder.position = holder.getAdapterPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brandsFragment.loaddrinksdetails(drinks.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        if(drinks != null){
            return drinks.size();
        }
       return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drinkname;
        Integer position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkname=itemView.findViewById(R.id.txt_brands_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

}
