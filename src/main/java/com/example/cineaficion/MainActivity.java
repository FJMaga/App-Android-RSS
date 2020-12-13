package com.example.cineaficion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static EditText usuario,password;
    static String stringUsuario,stringPassword;
    static POJOusuarios nuevoUsuario = new POJOusuarios(stringUsuario,stringPassword);
    static ArrayList<POJOusuarios> listadoUsuarios = new ArrayList<>();
    static int i=0;
    static boolean NoUsuario = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.textUsuario);
        password = findViewById(R.id.textPassword);



        // Read from the database
        ValueEventListener eventListener;
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("usuariosRSS");
        System.out.println(database);
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    listadoUsuarios.add(new POJOusuarios(ds.child("usuario").getValue().toString(),ds.child("password").getValue().toString()));

                    System.out.println(listadoUsuarios);

                    //System.out.println(ds.child("usuario").getValue().toString());
                    //System.out.println(ds.child("password").getValue().toString());

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "Failed to read value.", error.toException());
            }
        };
        database.addValueEventListener(eventListener);

        //listadoUsuarios.add(new POJOusuarios("admin","admin"));

        //Recoger información
/*
        Bundle datosUsuario = getIntent().getExtras();
        Bundle datosPassword = getIntent().getExtras();// recupera el paquete con los extras

        if(datosUsuario!=null && datosPassword!=null) {
            String usuarioRegistrado = datosUsuario.getString("usuarioR"); //cargamos en una nueva variable cada valor obtenido del extra
            String passwordRegistrado = datosPassword.getString("passwordR");

            listadoUsuarios.add(new POJOusuarios(usuarioRegistrado,passwordRegistrado));
        }
        */

    }

    public void entrar(View view){

        stringUsuario = usuario.getText().toString();
        stringPassword = password.getText().toString();


        if(stringUsuario.isEmpty() || stringPassword.isEmpty()){
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }
        else{

            System.out.println(listadoUsuarios);


            for (i=0;i<listadoUsuarios.size();i++){

                if(stringUsuario.equals(listadoUsuarios.get(i).getUsuario()) && stringPassword.equals(listadoUsuarios.get(i).getPassword())){

                    Toast.makeText(this, "Acceso permitido", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(this,RSSMenu.class);

                    startActivity(i);

                    finish();

                }

                if(stringUsuario.equals(listadoUsuarios.get(i).getUsuario())){

                    NoUsuario = false;

                }
                if(stringUsuario.equals(listadoUsuarios.get(i).getUsuario()) && !stringPassword.equals(listadoUsuarios.get(i).getPassword())){
                    Toast.makeText(this, "password incorrecto", Toast.LENGTH_SHORT).show();
                }

            }

            if(NoUsuario){
                Toast.makeText(this, "El usuario no esta registrado", Toast.LENGTH_SHORT).show();

            }

        }
        NoUsuario=true;

    }

    public void registro(View view){

        Intent i = new Intent(this,Registro.class);

        startActivity(i);

        finish();


    }
}
