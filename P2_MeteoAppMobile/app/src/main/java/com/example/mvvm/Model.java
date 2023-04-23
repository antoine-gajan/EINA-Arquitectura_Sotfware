package com.example.mvvm;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Model {

    @Nullable
    private ArrayList<CadenaMeteo> list_meteo_station;
    private ArrayList<Usuario> list_users;
    private static Model instance;
    private Model(){
        list_meteo_station = new ArrayList<>();
        list_users = new ArrayList<>();
    }

    public static Model get_instance(){
        // Design pattern singleton
        if (instance == null) {
            // If not exists, create
            instance = new Model();
        }
        // Return instance
        return instance;
    }

    public List<Usuario> get_list_users(){
        return list_users;
    }

    public List<CadenaMeteo> get_list_meteo_station(){
        return list_meteo_station;
    }

    public void add_user(Usuario u){
        list_users.add(u);
    }

    public void add_meteo_station(CadenaMeteo cadena){
        list_meteo_station.add(cadena);
    }
}
