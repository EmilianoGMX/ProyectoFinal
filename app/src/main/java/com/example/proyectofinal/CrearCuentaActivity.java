package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.databinding.ActivityCrearCuentaBinding;

public class CrearCuentaActivity extends AppCompatActivity {

    private ActivityCrearCuentaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearCuentaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setListeners();

    }

    //Método el botón login
    public void login(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
        finish();
    }

    private void setListeners() {
        binding.toolbar.setNavigationOnClickListener(this::login );
    }
}