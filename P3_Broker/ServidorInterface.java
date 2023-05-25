import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServidorInterface extends Remote {
    public ArrayList<Servicio> get_list_servicios() throws RemoteException;
}
