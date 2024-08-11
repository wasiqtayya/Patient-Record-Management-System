package com.example.finalapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUpActivity extends AppCompatActivity {

    EditText emailId, passwd;
    EditText confirmPass;


    FirebaseAuth firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebase = FirebaseAuth.getInstance();
    }

    public void BtnSignup(View view) {

        try {

            emailId = findViewById(R.id.signUpEmail);
            passwd = findViewById(R.id.editTextTextPassword);
            confirmPass = findViewById(R.id.confirmPassword);

            String email = emailId.getText().toString();
            String paswd = passwd.getText().toString();
            String confiPas = confirmPass.getText().toString();
            if (email.isEmpty())
            {
                emailId.setError("Provide your Email first!");
                emailId.requestFocus();
            }
            if (paswd.isEmpty()  ) {
                passwd.setError("Set your password first");
                passwd.requestFocus();
            }
            if (confiPas.isEmpty()  ) {
                confirmPass.setError("Set your confirm password first");
                confirmPass.requestFocus();
            }
            if ( passwd.length() < 8 ) {
                passwd.setError("Set your password first or password too short");
                passwd.requestFocus();
            }
            if ( confiPas.length() < 8 ) {
                confirmPass.setError("Set your confirm password first or password too short");
                confirmPass.requestFocus();
            }

            if (paswd != confiPas) {

                Toast.makeText(signUpActivity.this, "Password and confirm password is not same", Toast.LENGTH_SHORT).show();

            }

            if (!(email.isEmpty() && paswd.isEmpty() && confiPas.isEmpty())) {
                firebase.createUserWithEmailAndPassword(email, paswd).addOnCompleteListener(signUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            Toast.makeText(signUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(signUpActivity.this, "Register Succesfully", Toast.LENGTH_SHORT).show();


                            startActivity(new Intent(signUpActivity.this, MainActivity.class));
                        }

                    }
                });
            }


        }catch (Exception s){
            Toast.makeText(signUpActivity.this, s.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }


    }

}