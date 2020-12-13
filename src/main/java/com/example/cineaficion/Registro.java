package com.example.cineaficion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {


    static EditText usuario,password,password2,email,edad;
    static RadioButton generoH,generoM;
    static String stringUsuario,stringPassword,stringGenero,stringGeneroH,stringGeneroM,stringPassword2,stringEmail,stringEdad;
    static POJOusuarios value;
    static ArrayList<POJOusuarios> listadoUsuarios = new ArrayList<>();
    static DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = findViewById(R.id.textUsuario);
        password = findViewById(R.id.textPassword);
        password2 = findViewById(R.id.textPassword2);
        email = findViewById(R.id.textEmail);
        edad = findViewById(R.id.textEdad);
        generoH = findViewById(R.id.radioButtonH);
        generoM = findViewById(R.id.radioButtonM);

    }

    public void registrar(View view){

        stringUsuario = usuario.getText().toString();
        stringPassword = password.getText().toString();
        stringPassword2 = password2.getText().toString();
        stringEmail = email.getText().toString();
        stringGeneroH = generoH.getText().toString();
        stringGeneroM = generoM.getText().toString();
        stringEdad = edad.getText().toString();


        if(stringUsuario.isEmpty() && stringPassword.isEmpty() && stringPassword2.isEmpty() && stringEmail.isEmpty() && stringEdad.isEmpty()){
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }
        else{

            if(stringPassword.equals(stringPassword2)){

                if(generoH.isChecked()){
                    stringGenero = stringGeneroH;
                }
                else{
                    stringGenero = stringGeneroM;
                }

                // Patrón para validar el email
                Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                Matcher mather = pattern.matcher(stringEmail);

                if (mather.find() == true) {

                    //listadoUsuarios.add(new POJOusuarios(stringUsuario, stringPassword, stringEmail, stringGenero, Integer.parseInt(stringEdad)));

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    dbRef = database.getReference("usuariosRSS");

                    dbRef.push().setValue(new POJOusuarios(stringUsuario, stringPassword,stringEmail,stringGenero, Integer.parseInt(stringEdad)));

                    Intent i = new Intent(this,MainActivity.class);

                    //Enviar información
                    //i.putExtra("usuarioR",value.getUsuario()); // devuelve Usuario
                    //i.putExtra("passwordR",value.getPassword()); // devuelve password

                    startActivity(i);

                    finish();
                } else {
                    Toast.makeText(this, "El email ingresado es inválido", Toast.LENGTH_SHORT).show();

                }

            }
            else{
                Toast.makeText(this, "Los password no coinciden", Toast.LENGTH_SHORT).show();
            }


        }

    }
}
