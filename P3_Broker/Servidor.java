import java.util.ArrayList;

public class Servidor implements ServidorInterface {
    static String broker_ip = "155.210.154.199:32001";
    static String server_ip = "155.210.154.200:32001";
    private String nombre_servidor;
    private ArrayList<Servicio> list_servicios;
    Servidor(String nombre_servidor){
        this.nombre_servidor = nombre_servidor;
        this.list_servicios = new ArrayList<>();
    }

    public void add_servicio(String nombre_servicio, String tipo_return, ArrayList<String> params){
        Servicio servicio = new Servicio(nombre_servicio, tipo_return, params);
        this.list_servicios.add(servicio);
    }

    public String get_server_ip(){
        return server_ip;
    }

    public String get_nombre_servidor(){
        return nombre_servidor;
    }

    public ArrayList<Servicio> get_list_servicios(){
        return list_servicios;
    }
}
