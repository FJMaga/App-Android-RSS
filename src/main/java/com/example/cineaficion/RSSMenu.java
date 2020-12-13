package com.example.cineaficion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RSSMenu extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> rssLinks = new ArrayList<>();
    static String rssSeleccionado,recogerOpcion;
    static Spinner dropdown;
    static String[] numeroItems;
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_menu);

        TextView cartelera = findViewById(R.id.textView6);
        String c = "C\nA\nR\nT\nE\nL\nE\nR\nA";
        cartelera.setText(c);
        TextView estrenos = findViewById(R.id.textView7);
        String e = " \nE\nS\nT\nR\nE\nN\nO\nS";
        estrenos.setText(e);
        TextView proximas = findViewById(R.id.textView8);
        String p = " \nP\nR\nO\nX\nI\nM\nA\nS";
        proximas.setText(p);
        TextView consultas = findViewById(R.id.textView9);
        String con = " \nC\nO\nN\nS\nU\nL\nT\nA\nS";
        consultas.setText(con);
        TextView actores = findViewById(R.id.textView10);
        String a = " \n \nA\nC\nT\nO\nR\nE\nS";
        actores.setText(a);

        //Button btnRediff = findViewById(R.id.btnRediff);
        //Button btnCinemaBlend = findViewById(R.id.btnCinemaBlend);

        ImageButton btnCartelera = findViewById(R.id.imageButton);
        ImageButton btnEstrenos = findViewById(R.id.imageButton2);
        ImageButton btnProximas = findViewById(R.id.imageButton3);
        ImageButton btnConsultas = findViewById(R.id.imageButton4);
        ImageButton btnActores  = findViewById(R.id.imageButton5);

        /*
        dropdown = findViewById(R.id.spinner);

        //creamos lista spinner
        numeroItems = new String[]{"Cartelera", "Estrenos de la semana", "Proximamente en cartelera","Peliculas mas consultadas", "Top actores"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numeroItems);
        dropdown.setAdapter(adapter);
         */

        //btnRediff.setOnClickListener(this);
        //btnCinemaBlend.setOnClickListener(this);

        btnCartelera.setOnClickListener(this);
        btnEstrenos.setOnClickListener(this);
        btnProximas.setOnClickListener(this);
        btnConsultas.setOnClickListener(this);
        btnActores.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        /*
        recogerOpcion = dropdown.getSelectedItem().toString();

        if(recogerOpcion.equals("Cartelera")){
            rssSeleccionado = "cine/encartelera";
        }
        else if(recogerOpcion.equals("Estrenos de la semana")){
            rssSeleccionado = "cine/semana";
        }
        else if(recogerOpcion.equals("Proximamente en cartelera")){
            rssSeleccionado = "cine/proximamente";
        }
        else if(recogerOpcion.equals("Peliculas mas consultadas")) {
            rssSeleccionado = "cine/masconsultadas";
        }
        else if(recogerOpcion.equals("Top actores")){
            rssSeleccionado = "actores/top";
        }


        System.out.println(rssSeleccionado);
        /*
        rssLinks.add("http://rss.sensacine.com/cine/"+rssSeleccionado+"?format=xml");
        rssLinks.add("http://www.cinemablend.com/rss_review.php");

        switch (view.getId()) {
            case R.id.btnRediff:
                startActivity(new Intent(RSSMenu.this, RSSFeedActivity.class).putExtra("rssLink", rssLinks.get(0)));
                break;

            case R.id.btnCinemaBlend:
                startActivity(new Intent(RSSMenu.this, RSSFeedActivity.class).putExtra("rssLink", rssLinks.get(1)));
                break;
        }

         */

        switch (view.getId()) {
            case R.id.imageButton:
                rssSeleccionado = "cine/encartelera";
                break;

            case R.id.imageButton2:
                rssSeleccionado = "cine/semana";
                break;
            case R.id.imageButton3:
                rssSeleccionado = "cine/encartelera";
                break;

            case R.id.imageButton4:
                rssSeleccionado = "cine/proximamente";
                break;
            case R.id.imageButton5:
                rssSeleccionado = "actores/top";
                break;
        }

        startActivity(new Intent(RSSMenu.this, RSSFeedActivity.class).putExtra("rssLink", "http://rss.sensacine.com/"+rssSeleccionado+"?format=xml"));





    }
}

