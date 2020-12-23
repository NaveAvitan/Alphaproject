package com.example.alphaproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.UUID;

public class Storage extends AppCompatActivity {
    private ImageView Picture;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        Picture = findViewById(R.id.Picture);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    /**
     * @since 18/11/2020
     * This method let the user upload a picture to the firebase storage
     * @param view
     */
    public void Upload_Picture(View view) {
        choosePicture();
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            Picture.setImageURI(imageUri);
            UploadToFirebase();

        }
    }

    private void UploadToFirebase() {
        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/"+randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(Storage.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(Storage.this, "Failed to upload.", Toast.LENGTH_SHORT).show();
                    }
                });
    }










    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * switch activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("sms"))) {
            Intent si = new Intent(this, Sms.class);
            startActivity(si);
        }
        if ((st.equals("main"))) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if ((st.equals("location"))) {
            Intent si = new Intent(this, UserLocation.class);
            startActivity(si);
        }
        if ((st.equals("auth"))) {
            Intent si = new Intent(this, Auth.class);
            startActivity(si);
        }



        return true;
    }

}