package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        
        setListeners();

    }

    //Método el botón crearCuenta
    public void crearCuenta(View view){
        Intent crearCuenta = new Intent(this, CrearCuentaActivity.class);
        startActivity(crearCuenta);
        finish();
    }
    //Método el botón login
    public void login(View view){
        Intent login = new Intent(this, DrawerActivity.class);
        startActivity(login);
        finish();
    }

    private void setListeners() {
        binding.btnCreateAccount.setOnClickListener(this::crearCuenta);
        binding.btnLogin.setOnClickListener(this::login);

    }
}