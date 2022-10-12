package com.example.liquorland.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liquorland.Models.Drink;
import com.example.liquorland.R;
import com.example.liquorland.ui.ListOfDrinksFragment;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.ViewHolder> {

    List<Drink> drinks;
    Context context;
    ListOfDrinksFragment listOfDrinksFragment;


    public BrandsAdapter(List<Drink> drinks, Context context, ListOfDrinksFragment listOfDrinksFragment) {
        this.drinks = drinks;
        this.context = context;
        this.listOfDrinksFragment = listOfDrinksFragment;

    }

    @NonNull
    @Override
    public BrandsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate( R.layout.list_product_item, parent, false);


        return new BrandsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BrandsAdapter.ViewHolder holder, int position) {
        Drink drink= drinks.get(position);
        holder.productname.setText(drink.getDrinkname());
        holder.productvolume.setText(drink.getDrinkvolume());
        holder.productprice.setText(drink.getDrinkprice());
        Glide.with(holder.itemView.getContext()).load(drinks.get(position).getImageUrl()).into(holder.productimage);



    }

    @Override
    public int getItemCount() {

      return drinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       ImageView productimage, addtocart;
       TextView productname, productvolume, productprice;
       Integer id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.ic_list_image);
            productname= itemView.findViewById(R.id.txt_product_name);
            productvolume= itemView.findViewById(R.id.txt_product_volume);
            productprice= itemView.findViewById(R.id.txt_product_price);
            addtocart= itemView.findViewById(R.id.ic_product_to_cart);

            addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listOfDrinksFragment.AddDrinksToCart(drinks.get(getAbsoluteAdapterPosition()));




                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfDrinksFragment.goToDetailsfromlists(drinks.get(getAbsoluteAdapterPosition()));

                }
            });
        }
    }
}
