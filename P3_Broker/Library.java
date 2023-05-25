import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//TODO: otros imports
public interface Library extends Remote
{
    //metodos de la interface
    int number_of_books() throws RemoteException;
    ArrayList<String> list_books() throws RemoteException;
    void add_book(String _new_value) throws RemoteException;
}
