package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initComponents();
        setListeners();


    }

    private void initComponents() {

    }

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

    private void setListeners() {
        binding.btnCreateAccount.setOnClickListener(this::crearCuenta);
        binding.btnLogin.setOnClickListener(this::login);

    }
}