package com.example.mvvm;

import androidx.annotation.Nullable;

public class Usuario {
    // Attributes
    @Nullable
    private String nombre;
    private String apellido;
    private String aspect;
    private int edad;
    private MeteoInformation info;

    // Constructor
    public Usuario(String nom, String ape, int eda, String aspect){
        this.nombre = nom;
        this.apellido = ape;
        this.edad = eda;
        this.aspect = aspect;
        this.info = null;
    }
    // Update method
    public void update(MeteoInformation meteo){
        info = meteo;
    }
    // Getter
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String get_aspect() {return aspect;}
    public MeteoInformation get_info() {return info;}
}
