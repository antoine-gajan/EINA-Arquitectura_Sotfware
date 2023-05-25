import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConsumidorInterface extends Remote {
    void callback(String message) throws RemoteException;
}

