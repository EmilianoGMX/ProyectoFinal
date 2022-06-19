package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class Mapa extends AppCompatActivity
{
    private MapView mapa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Context contexto = getApplicationContext();                                                             //Contexto
        Configuration.getInstance().load(contexto, PreferenceManager.getDefaultSharedPreferences(contexto));    //Instancia

        setContentView(R.layout.activity_mapa);

        mapa = (MapView) findViewById(R.id.mapa);                   //Referencia a elemento
        mapa.setTileSource(TileSourceFactory.MAPNIK);               //Fuente de mosaicos
        mapa.setMultiTouchControls(true);                           //Se permite el multitouch
        mapa.setLayerType(View.LAYER_TYPE_SOFTWARE, null);    //Tipo de capas

        IMapController mapController = mapa.getController();        //Controlador
        mapController.setZoom(19.9);                                //Zoom
        GeoPoint startPoint = new GeoPoint(20.6877247,-103.30015);  //Punto inicial
        mapController.setCenter(startPoint);                                           //Centrar el punto en el punto inicial

        List<Marker> listaMarcadores = ListaTiendas();

        for(int i = 0; i<listaMarcadores.size(); i++)
        {
            mapa.getOverlays().add(listaMarcadores.get(i));         //Se añaden los marcadores
        }

    }

    public void onResume(){
        super.onResume();
        mapa.onResume();
    }

    public void onPause(){
        super.onPause();
        mapa.onPause();
    }

    private List<Marker> ListaTiendas() {
        //Se obtiene SharedPreferencies con el tema "tiendas"
        SharedPreferences preferencias = getSharedPreferences("tiendas", Context.MODE_PRIVATE);

        List<Marker> marcadores = new ArrayList<Marker>();

        int cantidadTiendas = preferencias.getInt("cantidad", 0);
        if (cantidadTiendas == 0) {
            SharedPreferences.Editor editor = preferencias.edit();          //Se crea editor en base a las preferencias

            //Creación de tiendas
            editor.putInt("cantidad", 6);
            editor.putString("tienda1Lat", "20.694850");
            editor.putString("tienda1Long", "-103.303241");

            editor.putString("tienda2Lat", "20.698599");
            editor.putString("tienda2Long", "-103.330521");

            editor.putString("tienda3Lat", "20.701078");
            editor.putString("tienda3Long", "-103.331272");

            editor.putString("tienda4Lat", "20.709519");
            editor.putString("tienda4Long", "-103.344034");

            editor.putString("tienda5Lat", "20.642374");
            editor.putString("tienda5Long", "-103.326977");

            editor.putString("tienda6Lat", "20.714127");
            editor.putString("tienda6Long", "-103.410160");

            editor.commit();
        }

        //Inicialización y añade tiendas en lista
        for(int i = 1; i<cantidadTiendas+1; i++)
        {
            double latitud = Double.parseDouble(preferencias.getString("tienda"+i+"Lat", "null"));
            double longitud = Double.parseDouble(preferencias.getString("tienda"+i+"Long", "null"));

            Marker marcador = new Marker(mapa);
            marcador.setPosition(new GeoPoint(latitud, longitud));
            marcador.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            marcadores.add(marcador);
        }
        return marcadores;
    }
}