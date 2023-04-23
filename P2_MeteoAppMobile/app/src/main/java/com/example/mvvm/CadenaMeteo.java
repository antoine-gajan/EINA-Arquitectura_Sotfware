package com.example.mvvm;

import java.util.ArrayList;
import java.util.List;

public class CadenaMeteo {
    private List<Usuario> usuarios;
    private MeteoInformation info;

    // Constructor y destructor
    public CadenaMeteo(String ciudad, double temp, double prec, double vient) {
        this.info = new MeteoInformation(ciudad, temp, vient, prec);
        this.usuarios = new ArrayList<>();
    };
    // Gestion de los observadores
    public void notify_users() {for (Usuario u : usuarios) u.update(info);};
    public void attach(Usuario u) {usuarios.add(u);};
    public void detach (Usuario u) {usuarios.remove(u);};
    // Getter
    public MeteoInformation get_state() {return info;}
    public List<Usuario> get_usuarios() {return usuarios;}
    // Setter
    public void set_state(MeteoInformation new_meteo) {info = new_meteo; notify_users();}
}
