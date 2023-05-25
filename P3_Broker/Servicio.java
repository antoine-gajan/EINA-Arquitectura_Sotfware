import java.io.Serializable;
import java.util.ArrayList;

public class Servicio implements Serializable {
    // Private attributes
    private String nom_servicio;
    private ArrayList<String> parametros_servicio;
    private String tipo_return;

    // Constructor
    Servicio(String nom, String tipo_return, ArrayList<String> param){
        this.nom_servicio = nom;
        this.tipo_return = tipo_return;
        this.parametros_servicio = param;
    }

    // Getters
    public String get_nom_servicio(){
        return nom_servicio;
    }

    public ArrayList<String> get_parametros_servicio(){
        return parametros_servicio;
    }

    public String get_tipo_return(){
        return tipo_return;
    }

    @Override
    public String toString() {
        String str = this.tipo_return + " " + this.nom_servicio + "(";
        if (this.parametros_servicio.size() > 0) {
            str = str + String.join(", ", this.parametros_servicio);
        }
        str = str + ")";
        return str;
    }
}
