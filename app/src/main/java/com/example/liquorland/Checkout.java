package com.example.liquorland;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.liquorland.Models.Orders;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Checkout extends Fragment {



    Context context= getContext();
    String amount;

    ImageView back;
    TextView amountdisplay, mpesa, equity, standardchartered;
    TextInputEditText address;
    Button paybutton;

    FirebaseAuth auth;
    DatabaseReference ordersdb;

    View  view;


    public Checkout() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getArguments();
        amount= bundle.getString("AmountToPay");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View  root=inflater.inflate(R.layout.fragment_checkout, container, false);

        BottomNavigationView navView =root.findViewById(R.id.nav_view);
        back=root.findViewById(R.id.ic_back_cart);
        paybutton=root.findViewById(R.id.btn_PayButton);
        address= root.findViewById(R.id.txt_address);
        mpesa=root.findViewById(R.id.txt_mpesa);
        equity=root.findViewById(R.id.txt_equity);
        standardchartered=root.findViewById(R.id.txt_standardchartered);

        amountdisplay=root.findViewById(R.id.txt_amont_display);
            amountdisplay.setText(amount);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.navigation_cart);
            }
        });

        equity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(root,"Service is currently unavailable \n"+ "Please use M-pesa for the meantime", Snackbar.LENGTH_LONG).show();
            }
        });

        standardchartered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(root,"Service is currently unavailable \n"+ "Please use M-pesa for the meantime", Snackbar.LENGTH_LONG).show();
            }
        });

        DisplayPaymentDialog();


        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        paybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment();
    }

    public void Payment(){
        String useraddress= address.getText().toString();
        String dev_status= "Not Delivered";

        if(useraddress.isEmpty()){
            address.setError("Please enter your shipping address");
        }else{
            auth= FirebaseAuth.getInstance();
            FirebaseUser mAuth= auth.getCurrentUser();

            ordersdb= FirebaseDatabase.getInstance().getReference().child("Orders").child(mAuth.getUid());
            String refnumber= ordersdb.push().getKey();
            String savecurrentDate;

            Calendar calForDate= Calendar.getInstance();

            SimpleDateFormat currentDate= new SimpleDateFormat("MM/ dd/ yy");
            savecurrentDate= currentDate.format(calForDate.getTime());


            Orders order= new Orders();
            order.setCreated_at(savecurrentDate);
            order.setRef_number(refnumber);
            order.setPrice(amount);
            order.setDelivery_status(dev_status);

            ordersdb.child(refnumber).setValue(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Snackbar.make(requireView(),"Order has been places successfully \n"+ "Now wait to receive it on your front door", Snackbar.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Failed because "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
        });

        auth = FirebaseAuth.getInstance();
        FirebaseUser mAuth = auth.getCurrentUser();

        DatabaseReference odersref;
        odersref= FirebaseDatabase.getInstance().getReference().child("Cart").child(mAuth.getUid());

        odersref.removeValue();
    }

    public void DisplayPaymentDialog(){
        final BottomSheetDialog bottomsheetdialog = new BottomSheetDialog(getContext());
        bottomsheetdialog.setContentView(R.layout.payment_dialog);

        TextInputEditText amount_paid = bottomsheetdialog.findViewById(R.id.txt_amount_paid);

        TextView proceed= bottomsheetdialog.findViewById(R.id.txt_continue);

        mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomsheetdialog.show();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount_entered= amount_paid.getText().toString();

                if(amount_entered.isEmpty()){
                    Toast.makeText(getContext(), "Please enter the amount you wish to pay", Toast.LENGTH_SHORT).show();
                }else if(!(amount_entered.equals(amount))){
                    Toast.makeText(getContext(), "You have entered an incorrect amount \n"+ "Try again", Toast.LENGTH_SHORT).show();
                }else {

                    bottomsheetdialog.dismiss();
                }
            }
        });
    }
}