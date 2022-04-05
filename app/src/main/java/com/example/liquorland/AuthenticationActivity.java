package com.example.liquorland;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liquorland.utils.PreferenceStorage;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

public class AuthenticationActivity extends AppCompatActivity {
    TextView signin;
    TextInputEditText firstname, lastname,password,confirm ,emailaddress;
    Button login;
    PreferenceStorage preferenceStorage;
    Context context;

    CheckBox status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        final   BottomSheetDialog bottomsheetdialog = new BottomSheetDialog(AuthenticationActivity.this);
        bottomsheetdialog.setContentView(R.layout.sign_in);

        firstname= findViewById(R.id.txt_firstname);
        lastname= findViewById(R.id.txt_lastname);
        emailaddress= findViewById(R.id.txt_email);
        password=findViewById(R.id.txt_password);
        confirm= findViewById(R.id.txt_confirm);
        signin= findViewById(R.id.txt_sign_in);
        status=findViewById(R.id.txt_status);
        login= findViewById(R.id.btn_login);


        preferenceStorage= new PreferenceStorage(this);



        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               openLogInDialog();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(validateInputs()){
                    firstname.getText().toString().trim();
                    lastname.getText().toString().trim().trim();
                    emailaddress.getText().toString().trim();
                    password.getText().toString().trim();


                    preferenceStorage.SaveUserData(
                        firstname.getText().toString().trim(),
                        lastname.getText().toString().trim().trim(),
                        emailaddress.getText().toString().trim(),
                        password.getText().toString().trim()
                    );


                        preferenceStorage.setLoggingInStatus(true);

                        Intent intent= new Intent(AuthenticationActivity.this, MainActivity.class);
                        Toast.makeText(AuthenticationActivity.this, "Successfully created your account", Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                        finish();

                    }

            }
        });


    }

    public  boolean validateInputs(){

        boolean response= false;

        if(firstname.getText().toString().trim().isEmpty()){

            firstname.setError("Please enter your first name");
        }
        else if(lastname.getText().toString().trim().isEmpty()){

            lastname.setError("Please enter your last name");
        }
        else if(emailaddress.getText().toString().trim().isEmpty()){

            emailaddress.setError("Please enter your email address");
        }
        else if(!emailaddress.getText().toString().contains("@")){

            emailaddress.setError("Your email should have @ character");
        }
        else if(password.getText().toString().trim().isEmpty()){

            password.setError("Please enter a valid password");
        }
        else if(confirm.getText().toString().trim().isEmpty()){

            confirm.setError("Please confirm the password you have entered");
        }
        else if(!confirm.getText().toString().trim().contentEquals(password.getText().toString().trim())){

            confirm.setError("Passwords do not match");
        }
        else{
            response= true;
        }

        return response;
    }


    private void openLogInDialog(){
        final   BottomSheetDialog bottomsheetdialog = new BottomSheetDialog(AuthenticationActivity.this);
        bottomsheetdialog.setContentView(R.layout.sign_in);

        Button sign_in= bottomsheetdialog.findViewById(R.id.btn_sign_in);

        TextInputEditText sign_in_email = bottomsheetdialog.findViewById(R.id.txt_signin_email);
        TextInputEditText sign_in_password = bottomsheetdialog.findViewById(R.id.txt_signin_password);

        TextView singup= bottomsheetdialog.findViewById(R.id.txt_signup);


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sign_in_email.getText().toString().trim().isEmpty()){

                    sign_in_email.setError("Please enter your email address");
                }
                else if(sign_in_password.getText().toString().trim().isEmpty()){

                    sign_in_password.setError("Please enter password");
                }
                else{
                    if(preferenceStorage.authenticate(sign_in_email.getText().toString().trim(), sign_in_password.getText().toString().trim())) {

                        preferenceStorage.setLoggingInStatus(true);
                        Intent intent = new Intent(AuthenticationActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(AuthenticationActivity.this, "Your credentials do not match ", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        bottomsheetdialog.show();

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomsheetdialog.dismiss();
            }
        });

    }

}