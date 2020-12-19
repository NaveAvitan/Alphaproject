package com.example.alphaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Auth extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email;
    EditText passw;
    Button login;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mAuth = FirebaseAuth.getInstance();
        email= findViewById(R.id.edittextmail);
        passw= findViewById(R.id.edittextpassw);
        login= findViewById(R.id.button9999);
        signup = findViewById(R.id.buton999);

}
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser); }

    private void updateUI(FirebaseUser currentUser) {
    }

    public void login(View view) {
        String emailchack= email.getText().toString();
        String passwordcheck = passw.getText().toString();
        mAuth.signInWithEmailAndPassword(emailchack, passwordcheck)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Auth.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void signup(View view) {
        String emailnew= email.getText().toString();
        String password = passw.getText().toString();
        mAuth.createUserWithEmailAndPassword(emailnew, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Auth.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
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
        if ((st.equals("storage"))) {
            Intent si = new Intent(this, Storage.class);
            startActivity(si);
        }
        if ((st.equals("main"))) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if ((st.equals("location"))) {
            Intent si = new Intent(this, Location2.class);
            startActivity(si);
        }


        return true;
    }



}
