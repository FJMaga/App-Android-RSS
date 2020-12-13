package com.example.cineaficion;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class POJOusuarios {

    String usuario, password, email, genero;
    int edad;

    public POJOusuarios(){

    }

    public POJOusuarios(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public POJOusuarios(String usuario, String password, String email, String genero, int edad) {
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.genero = genero;
        this.edad = edad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "POJOusuarios{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
