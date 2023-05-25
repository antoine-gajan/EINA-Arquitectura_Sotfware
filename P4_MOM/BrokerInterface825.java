import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BrokerInterface825 extends Remote {
    void declarar_cola(String nombre_cola, String consumidor) throws RemoteException;
    void publicar(String nombre_cola, String mensaje) throws RemoteException;
    void consumir(String nombre_cola) throws RemoteException;
    public int nb_consumidores() throws MalformedURLException, RemoteException;
    public String registrar_consumidor(String host_remoto_IP_puerto) throws MalformedURLException, RemoteException, NotBoundException;
}