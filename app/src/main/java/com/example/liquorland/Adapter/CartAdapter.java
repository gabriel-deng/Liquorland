package com.example.liquorland.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorland.Models.CartItem;
import com.example.liquorland.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<CartItem> items;
    private final Context context;

    public CartAdapter(ArrayList<CartItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate( R.layout.cart_item, parent, false);

        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartItem item= items.get(position);

        holder.drinkname.setText(items.get(position).getCartdrinkname());
        holder.drinkvolume.setText(items.get(position).getCartdrinkvolume());
        holder.drinkprice.setText(items.get(position).getCartdrinkprice());
//        if(item.getCartdrinkimage()!= null){
//            holder.drinkimage.setImageURI(Uri.parse(item.getCartdrinkimage()));
//        }
//        holder.position = holder.getAdapterPosition();

    }

    @Override
    public int getItemCount() {
if (items != null){
    return items.size();
}
return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drinkname, drinkvolume, drinkprice;
        ImageView drinkimage, remove;
        Integer position;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkname=itemView.findViewById(R.id.txt_cart_drink_name);
            drinkvolume=itemView.findViewById(R.id.txt_cart_drink_volume);
            drinkprice=itemView.findViewById(R.id.txt_cart_drink_price);
            drinkimage=itemView.findViewById(R.id.ic_cart_drink_image);
            remove=itemView.findViewById(R.id.ic_remove_fromcart);

            remove.setOnClickListener(view -> {
                Snackbar.make(view, "Item successfully removed from cart", Snackbar.LENGTH_LONG).show();

            });

        }
    }
}
