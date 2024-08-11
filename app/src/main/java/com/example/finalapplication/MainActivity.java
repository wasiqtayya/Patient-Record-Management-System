package com.example.finalapplication;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email,password;


    FirebaseAuth firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebase = FirebaseAuth.getInstance();
    }







    public void signUpFunc(View view){

        startActivity(new Intent(this,signUpActivity.class));

    }

    public void BtnLogin(View view) {

        try {


            email = (EditText) findViewById(R.id.emailLogin);
            password = (EditText) findViewById(R.id.passwordLogin);


            String e = email.getText().toString();
            String p = password.getText().toString();


            if(e.isEmpty()){
                email.setError("Please enter email");
                email.requestFocus();


            }else if(p.isEmpty()){

                password.setError("Please enter the password");
                password.requestFocus();

            }else if(p.length() < 8){
                Toast.makeText(MainActivity.this, "Password must greater than 6 chracters", Toast.LENGTH_SHORT).show();

            }else {

                // LOGIN


                firebase.signInWithEmailAndPassword(e, p).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if (task.isSuccessful()) {

                            FirebaseUser user = firebase.getCurrentUser();
                            Intent id = new Intent(MainActivity.this,patientData.class);
                            id.putExtra("userID",user.getUid().toString());
                            startActivity(id);

                        } else {
                            Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }

                });

            }
        }catch(Exception s){
            Toast.makeText(MainActivity.this, s.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }

    }
}