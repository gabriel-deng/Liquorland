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
import com.example.liquorland.onItemClickedListener;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.ViewHolder> {
    List<Drink> drinks;
    Context context;
    onItemClickedListener listener;
    View root;

    public BrandsAdapter(List<Drink> drinks, Context context, onItemClickedListener listener, View root) {
        this.drinks = drinks;
        this.context = context;
        this.listener = listener;
        this.root = root;
    }

    public BrandsAdapter() {
    }

    public BrandsAdapter(List<Drink> drinks, Context context) {
        this.drinks = drinks;
        this.context = context;
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
                long drink= getItemId(position);
                try {
                    listener.onitemclick(drink);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

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

    public void ListToItemDrink(){

    }
}
