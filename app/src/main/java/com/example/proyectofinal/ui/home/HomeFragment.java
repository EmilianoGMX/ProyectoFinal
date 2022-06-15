package com.example.proyectofinal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HomeFragment extends Fragment {
    List<ListElement> elements;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initComponents();

        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;

    }

    private void initComponents() {
        elements = new ArrayList<>();
        elements.add(new ListElement("#C0392B", "25% de Descuento", "   Activo"));
        elements.add(new ListElement("#7B241C", "75% de Descuento", "   Activo"));
        elements.add(new ListElement("#DAF7A6", "15% de Descuento", "   Activo"));
        elements.add(new ListElement("#FFC300", "25% de Descuento", "   Activo"));
        elements.add(new ListElement("#FF5733", "45% de Descuento", "   Activo"));
        elements.add(new ListElement("#7FB3D5", "5% de Descuento ", "   Activo"));
        elements.add(new ListElement("#BB8FCE", "25% de Descuento", "   Activo"));elements.add(new ListElement("#C0392B", "25% de Descuento", "   Activo"));
        elements.add(new ListElement("#7B241C", "75% de Descuento", "   Activo"));
        elements.add(new ListElement("#DAF7A6", "15% de Descuento", "   Activo"));
        elements.add(new ListElement("#FFC300", "25% de Descuento", "   Activo"));
        elements.add(new ListElement("#FF5733", "45% de Descuento", "   Activo"));
        elements.add(new ListElement("#7FB3D5", "5% de Descuento ", "   Activo"));
        elements.add(new ListElement("#BB8FCE", "25% de Descuento", "   Activo"));


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
}