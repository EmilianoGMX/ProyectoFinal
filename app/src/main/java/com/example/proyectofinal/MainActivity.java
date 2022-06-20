package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        correo=findViewById(R.id.txtMail);
        contrasena=findViewById(R.id.txtPassword);

    }//oncreate

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void iniciarSesion(View view){

        mAuth.signInWithEmailAndPassword(correo.getText().toString(),contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i =new Intent(getApplicationContext(),DrawerActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Authentication correcta.", Toast.LENGTH_SHORT).show();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }//iniciarSesion


    //Método el botón crearCuenta
    public void crearCuenta(View view) {
        Intent crearCuenta = new Intent(this, CrearCuentaActivity.class);
        startActivity(crearCuenta);
        finish();
    }

    //Método el botón login
    public void login(View view) {
        Intent login = new Intent(this, DrawerActivity.class);
        startActivity(login);
        finish();
    }


}//class