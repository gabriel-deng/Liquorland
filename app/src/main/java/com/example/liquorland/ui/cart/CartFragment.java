package com.example.liquorland.ui.cart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liquorland.Checkout;
import com.example.liquorland.Models.CartItem;
import com.example.liquorland.Models.CartViewHolder;
import com.example.liquorland.R;
import com.example.liquorland.databinding.CartFragmentBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartFragment extends Fragment{


    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;
    Context context = getContext();

    String drinkid;
    int totalprice= 0;


    RecyclerView cartrecyclerview;

    Button button;
    TextView amount, zero_drinks;

    View root;

    FirebaseAuth auth;
    DatabaseReference databaseReference, mydataref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = CartFragmentBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        mydataref = FirebaseDatabase.getInstance().getReference("Drinks");

        auth = FirebaseAuth.getInstance();
        FirebaseUser mAuth = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(mAuth.getUid());

        amount=root.findViewById(R.id.txt_amount_to_pay);
        zero_drinks=root.findViewById(R.id.txt_no_orders);
        cartrecyclerview = root.findViewById(R.id.cart_recyclerview);
        cartrecyclerview.setNestedScrollingEnabled(true);
        cartrecyclerview.setLayoutManager(new LinearLayoutManager(context));



        return root;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<CartItem> options = new FirebaseRecyclerOptions.Builder<CartItem>()
                .setQuery(databaseReference, CartItem.class)
                .build();
        FirebaseRecyclerAdapter<CartItem, CartViewHolder> adapter= new FirebaseRecyclerAdapter<CartItem, CartViewHolder>(options) {

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
                CartViewHolder holder= new CartViewHolder(view);
                return  holder;

            }

            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull CartItem model) {

                if(model != null){
                    holder.drinkname.setText(model.getCartdrinkname());
                    holder.drinkvolume.setText(model.getCartdrinkvolume());
                    holder.drinkprice.setText(model.getCartdrinkprice());
                    Glide.with(holder.itemView.getContext()).load(model.getCartdrinkimage()).into(holder.drinkimage);

                    holder.remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            auth = FirebaseAuth.getInstance();
                            FirebaseUser mAuth = auth.getCurrentUser();
                            drinkid=model.getCart_itemid();

                            databaseReference = FirebaseDatabase.getInstance().getReference()
                                    .child("Cart")
                                    .child(mAuth.getUid())
                                    .child(drinkid);

                            databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "Drink successfully removed from your Cart", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });


                        totalprice += (Integer.valueOf(model.getCartdrinkprice())) ;


                }
                else {
                    cartrecyclerview.setVisibility(View.GONE);
                    zero_drinks.setVisibility(View.VISIBLE);
                }

            }

        };
        cartrecyclerview.setAdapter(adapter);
        adapter.startListening();
        amount.setText(String.valueOf(totalprice));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = root.findViewById(R.id.button12);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    Bundle bundle= new Bundle();
                    bundle.putString("AmountToPay", (String.valueOf(totalprice)));
                    Checkout checkout= new Checkout();
                    checkout.setArguments(bundle);
                    Navigation.findNavController(root).navigate(R.id.navigation_checkout, bundle);


                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        binding = null;
    }

}