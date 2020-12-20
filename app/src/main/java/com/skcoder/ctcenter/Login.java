package com.skcoder.ctcenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView tv1,tv2, mEmail, mPassword;
    private FirebaseAuth mAuth;
    Button logBtn;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("LogIn");

        mAuth = FirebaseAuth.getInstance();

        progressBar =(ProgressBar) findViewById(R.id.progressbar);
        mEmail = (EditText)findViewById(R.id.mEm);
        mPassword =(EditText)findViewById(R.id.mPass);
        logBtn = (Button)findViewById(R.id.logBtn);
        progressBar.setVisibility(View.GONE);
        tv1 = (TextView)findViewById(R.id.logSignup);
        tv2 = (TextView)findViewById(R.id.forgotPass);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        // forgot password
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email to Received Reset Link.");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString().trim();

                        if (mail.isEmpty()){
                            Toast.makeText(Login.this, "Please Enter Your Email.", Toast.LENGTH_SHORT).show();
                        }else{
                            mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Login.this, "Reset Link Sent to Your Email.", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, "Error : Reset Link is not Sent. " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog.
                    }
                });
                passwordResetDialog.create().show();
            }
        });

        //if user is already login
        if (mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }

        //for login button
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
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

                //for progressbar
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Login Successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });

    }
}