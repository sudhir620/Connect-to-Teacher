package com.skcoder.ctcenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    EditText mName, mEmail, mPassword;
    Button signBtn;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("SignUp");


        progressBar =(ProgressBar) findViewById(R.id.progresbar);
        mName = (EditText)findViewById(R.id.mName);
        mEmail = (EditText)findViewById(R.id.mEmail);
        mPassword = (EditText)findViewById(R.id.mPassword);
        signBtn = (Button)findViewById(R.id.signBtn);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }


        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    mName.setError("Name is Required.");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }
                if (password.length() < 6){
                    mPassword.setError("Password Most be >= 6 characters.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // register user to firebase.
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    String userid = firebaseUser.getUid();

                                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("id", userid);
                                    hashMap.put("username", name);
                                    hashMap.put("imageURL", "default");

                                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "User Created.", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Home.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }else {
                                    Toast.makeText(getApplicationContext(), "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });
    }
}