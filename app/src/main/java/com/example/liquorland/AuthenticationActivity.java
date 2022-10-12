package com.example.liquorland;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liquorland.Networking.POJO.UserResponse;
import com.example.liquorland.utils.PreferenceStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {
    TextView signin;
    TextInputEditText name, number, password, confirm, emailAddress;
    Button login;
    PreferenceStorage preferenceStorage;
    String userName;
    Editable userNumber;
    String userEmail;
    String userPassword;
    String login_userName;
    Editable login_password;
    UserResponse userResponse;


    CheckBox status;

    private static final String TAG = "EmailPassword";

    private  FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        final BottomSheetDialog bottomsheetdialog = new BottomSheetDialog(AuthenticationActivity.this);
        bottomsheetdialog.setContentView(R.layout.sign_in);


        name = findViewById(R.id.txt_name);
        number = findViewById(R.id.txt_number);
        emailAddress = findViewById(R.id.txt_email);
        password = findViewById(R.id.txt_password);
        confirm = findViewById(R.id.txt_confirm);
        signin = findViewById(R.id.txt_sign_in);
        status = findViewById(R.id.txt_status);
        login = findViewById(R.id.btn_login);


        mAuth = FirebaseAuth.getInstance();


        preferenceStorage = new PreferenceStorage(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= name.getText().toString();
                String usernumber= number.getText().toString();
                String email= emailAddress.getText().toString();
                String userpassword= password.getText().toString();
                String confirmpass= confirm.getText().toString();

                if(username.isEmpty()){
                    name.setError("Please enter your name");
                }
                else if(usernumber.isEmpty()){
                    number.setError("Please enter your number");
                }
                else if(email.isEmpty()){
                    emailAddress.setError("Enter your email address");
                }
                else if(userpassword.isEmpty()){
                    password.setError("Enter your password");
                }
                else if(!userpassword.equals(confirmpass)){
                    Toast.makeText(AuthenticationActivity.this, "Your passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else {
                  createAccount(username, usernumber ,email, userpassword);
                }
            }
            
        });

       


        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openLogInDialog();
            }
        });




    }


    private void createAccount(String username, String usernumber, String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            Toast.makeText(AuthenticationActivity.this,"Your account register successfully", Toast.LENGTH_LONG).show();

                            Intent intent= new Intent(AuthenticationActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Log.d(TAG, "createUserWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AuthenticationActivity.this, "Authentication failed." + task.getException() ,Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }


    private void openLogInDialog() {
        final BottomSheetDialog bottomsheetdialog = new BottomSheetDialog(AuthenticationActivity.this);
        bottomsheetdialog.setContentView(R.layout.sign_in);

        Button sign_in = bottomsheetdialog.findViewById(R.id.btn_sign_in);

        TextInputEditText sign_in_userEmail = bottomsheetdialog.findViewById(R.id.txt_signin_useremail);
        TextInputEditText sign_in_password = bottomsheetdialog.findViewById(R.id.txt_signin_password);

        TextView singup = bottomsheetdialog.findViewById(R.id.txt_signup);


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sign_email= sign_in_userEmail.getText().toString();
                String sign_password= sign_in_password.getText().toString();

                if(sign_email.isEmpty()){
                    sign_in_userEmail.setError("Please enter your email address");
                }
                else if(sign_password.isEmpty()){
                    sign_in_password.setError("Please enter your password");
                }
                else{
                    signIn(sign_email, sign_password);
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

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(AuthenticationActivity.this,"Welcome back",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(AuthenticationActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthenticationActivity.this, "Authentication failed."+ task.getException(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }



     private void updateUI(FirebaseUser user){

     }

     private void reload(){

     }
}
