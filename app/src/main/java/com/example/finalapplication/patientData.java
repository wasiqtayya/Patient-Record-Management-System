package com.example.finalapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class patientData extends AppCompatActivity {

    ImageButton gfgImageButton;
    String imageUrl;
    public EditText stname,stadress,stprovince,stcontact;

    Button btnsaveDB;


    private DatabaseReference DbRef;
    private FirebaseDatabase FDB;
    private String STId;

    String Name,adress,provin,contac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);


        FDB = FirebaseDatabase.getInstance();
        DbRef= FDB.getReference("Patient-Record");

        // Initializing the variable for image button
        gfgImageButton =  (ImageButton) findViewById(R.id.imageView);

        // Below code is for setting a click listener on the image
        gfgImageButton.setOnClickListener(view -> {
            // Intnet with action for opening Camera
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id



            activityResultLauncher.launch(camera_intent);
        });
    }


    ActivityResultLauncher<Intent> activityResultLauncher= registerForActivityResult( new ActivityResultContracts.StartActivityForResult(),result -> {

        if (result.getResultCode() == RESULT_OK &&  result.getData() != null) {
            Bundle bundle = result.getData().getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            gfgImageButton.setImageBitmap(bitmap);

            System.out.println("Test " + bundle.get("data"));

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();

// Create a reference to where you want to store the image
            StorageReference imageReference = storageReference.child("images/image.jpg");

// Convert the bitmap to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

// Upload the byte array to the storage reference
            UploadTask uploadTask = imageReference.putBytes(data);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Get the download URL
                imageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Store the URL in the imageUrl variable
                    imageUrl = uri.toString();
                    // Save the URL to the Realtime Database
                });
            });


        }
    });

    public void SaveDB(View view) {

        stname= (EditText)  findViewById(R.id.editTextTextPersonName);
        stadress= (EditText) findViewById(R.id.editTextTextPersonName2);
        stprovince= (EditText) findViewById(R.id.editTextTextPersonName4);
        stcontact=(EditText)findViewById(R.id.editTextTextPersonName5) ;

        Name = stname.getText().toString();
        adress =stadress.getText().toString();
        provin =stprovince.getText().toString();
        contac =stcontact.getText().toString();

        if ( TextUtils.isEmpty(STId))
        {
            createUser();

        }
        else
        {
            //updateUser();
            Toast.makeText(patientData.this, "Update Data", Toast.LENGTH_SHORT).show();

        }

    }

//    private void updateUser()
//    {
//        // updating the user via child nodes
//        try {
//            if (!TextUtils.isEmpty(Name)) {
//
//                DbRef.child(STId).child("Name").setValue(Name);
//            }
//            if (!TextUtils.isEmpty(stadress)) {
//                DbRef.child(STId).child("STClass").setValue(STClass);
//            }
//            if (!TextUtils.isEmpty(stprovince)) {
//                DbRef.child(STId).child("CGP").setValue(CGP);
//            }
//            if (!TextUtils.isEmpty(stcontact)) {
//                DbRef.child(STId).child("AGNO").setValue(AGNO);
//            }
//
//            Toast.makeText(patientData.this, "Record updated sucessfully", Toast.LENGTH_SHORT).show();
//
//
//            //name.setText("");
//            // email.setText("");
//        }
//        catch (Exception exception)
//        {
//
//            Toast.makeText(patientData.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//        }
//
//    }


    private void createUser ()
    {

        int i = 0;
        try {
            if (TextUtils.isEmpty(STId)) {
                STId = DbRef.push().getKey();
            }
            Students st = new Students(Name, adress,provin,contac,imageUrl,i++);

            DbRef.child(STId).setValue(st);

            Toast.makeText(patientData.this, "Record Save sucessfully", Toast.LENGTH_SHORT).show();

        }
        catch (Exception exception)
        {
            Toast.makeText(patientData.this, exception.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }



}