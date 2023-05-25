import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class Broker825 implements BrokerInterface825 {
    // Atributos privados

    //nombre o IP del host donde reside el objeto servidor
    static String hostName = "155.210.154.199:32001"; //se puede usar "IPhostremoto:puerto"
    //Por defecto RMI usa el puerto 1099

    // Constructor
    Broker825() throws RemoteException {
        super();
    }

    //API para los servidores:
    public void registrar_servidor(String name, String host_remoto_IP_puerto) throws MalformedURLException, RemoteException, NotBoundException {
        // Get the remote object
        ServidorInterface server = (ServidorInterface) Naming.lookup("//" + host_remoto_IP_puerto + "/" + name);
        // Register server in the broker registry
        Naming.rebind("//" + hostName + "/" + name, server);
    }

    //API para los clientes:
    public String ejecutar_servicio(String nom_servicio, String parametro_servicio) throws NotBoundException, RemoteException, MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Run over servers to know which one owns the service
        ServidorInterface server_found = null;
        Servicio service_found = null;
        for (String serv : list_servidores()){
            // If server is not the broker
            if (Objects.equals(serv, "//" + hostName + "/Broker")){
                continue;
            }
            // Get server
            ServidorInterface server = (ServidorInterface) Naming.lookup(serv);
            // Run over services
            for (Servicio service : server.get_list_servicios()){
                // If it's the good service
                if (Objects.equals(service.get_nom_servicio(), nom_servicio)){
                    // Update variables
                    server_found = server;
                    service_found = service;
                }
            }
        }
        // If method exists
        String result;
        if (server_found != null && service_found != null){
            // Invoke service
            Method method;
            if (Objects.equals(nom_servicio, "add_book")){
                method = server_found.getClass().getMethod(nom_servicio, String.class);
                result = method.invoke(server_found, parametro_servicio).toString();
            }
            else {
                method = server_found.getClass().getMethod(nom_servicio);
                result = method.invoke(server_found).toString();
            }
            // Return a concatenated string of result
            return String.join(" ", result);
        }
        // If no service found, return ""
        return "";
    }

    // List of servers
    public String[] list_servidores() throws MalformedURLException, RemoteException {
        return Naming.list("//" + hostName);
    }

    // List of services
    public ArrayList<String> list_servicios() throws MalformedURLException, RemoteException, NotBoundException {
        // Variable for listing services
        ArrayList<String> list_services = new ArrayList<>();
        // For each server
        for (String servidor : list_servidores()){
            // If server is not the broker
            if (Objects.equals(servidor, "//" + hostName + "/Broker")){
                continue;
            }
            // Get server and associated methods
            ServidorInterface server = (ServidorInterface) Naming.lookup(servidor);

            // Add all services provided
            for (Servicio service : server.get_list_servicios()){
                list_services.add(service.get_nom_servicio());
            }
        }
        // Return list of services
        return list_services;
    }

    public ArrayList<ArrayList<String>> get_parametros_servicios() throws RemoteException, MalformedURLException, NotBoundException {
        // Variable for listing parameters
        ArrayList<ArrayList<String>> list_params = new ArrayList<>();
        // For each server
        for (String servidor : list_servidores()){
            // If server is not the broker
            if (Objects.equals(servidor, "//" + hostName + "/Broker")){
                continue;
            }
            // Get server and associated methods
            ServidorInterface server = (ServidorInterface) Naming.lookup(servidor);

            // Add all services provided
            for (Servicio service : server.get_list_servicios()){
                ArrayList<String> param_list = new ArrayList<>();
                for (String param : service.get_parametros_servicio()){
                    param_list.add(param);
                }
                list_params.add(param_list);
            }
        }
        // Return list of services
        return list_params;
    }

    public static void main (String args[])
    {
        //Fijar el directorio donde se encuentra el java.policy
        //El segundo argumento es la ruta al java.policy
        System.setProperty("java.security.policy", "./java.policy");
        //Crear administrador de seguridad
        System.setSecurityManager(new SecurityManager());
        try
        {
            // Creacion del Broker
            Broker825 broker = new Broker825();
            BrokerInterface825 stub = (BrokerInterface825) UnicastRemoteObject.exportObject(broker, 0);
            // Registrarse en RMI
            Naming.rebind("//" + hostName + "/Broker", stub);

        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
