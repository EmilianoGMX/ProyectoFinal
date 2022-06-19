package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.overlay.Marker;

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

        Marker startMarker = new Marker(mapa);                                              //Creación del marcador
        startMarker.setPosition(new GeoPoint(20.6877247d,-103.30015d));  //Posición del marcador
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);                  //Ancho del marcador
        mapa.getOverlays().add(startMarker);                                                //Se agrega el marcador

    }

    public void onResume(){
        super.onResume();
        mapa.onResume();
    }

    public void onPause(){
        super.onPause();
        mapa.onPause();
    }
}