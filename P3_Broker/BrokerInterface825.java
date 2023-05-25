import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.ArrayList;
import java.util.Vector;

public interface BrokerInterface825 extends Remote {
    // API Servidores
    void registrar_servidor(String name, String host_remoto_IP_puerto) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException;

    public String[] list_servidores() throws MalformedURLException, RemoteException;

    // API Clientes
    String ejecutar_servicio(String nom_servicio, String parametro_servicio) throws NotBoundException, RemoteException, MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    public ArrayList<String> list_servicios() throws MalformedURLException, RemoteException, NotBoundException;

    public ArrayList<ArrayList<String>> get_parametros_servicios() throws RemoteException, MalformedURLException, NotBoundException;
}