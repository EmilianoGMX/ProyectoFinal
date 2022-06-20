package com.example.proyectofinal.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.ListAdapter;
import com.example.proyectofinal.ListElement;
import com.example.proyectofinal.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class HomeFragment extends Fragment {
    List<ListElement> elements;
    Button btnRecargar;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btnRecargar = binding.btnRecargar;

        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                initComponents();
            }
        });

        initComponents();

        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;

    }

    private void initComponents() {
        elements = new ArrayList<>();
        elements.add(new ListElement("#C0392B", "25% de Descuento", "   Activo"));
        elements.add(new ListElement("#C4393B", "50% de Descuento", "   Activo"));

        SharedPreferences preferences = getActivity().getSharedPreferences("cupones", Context.MODE_PRIVATE);
        int cantidad = preferences.getInt("cantidad", 0);

        for(int i = 1; i<cantidad; i++)
        {
            String producto = preferences.getString("Producto"+i, "Manzanas");
            String descuento = preferences.getString("Descuento"+i, "15%");
            String color = preferences.getString("Color"+i, "#C0392B");
            elements.add(new ListElement(color, producto, descuento+ "%"));
        }

        ListAdapter listAdapter = new ListAdapter(elements, getContext());
        RecyclerView recyclerView = binding.RecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Método para mostrar toast
    //Depuracion
    private void MostrarToast(String texto)
    {
        Context context = getActivity().getApplicationContext();
        CharSequence text = texto;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}