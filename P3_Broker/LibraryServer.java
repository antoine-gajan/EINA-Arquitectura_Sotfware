import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LibraryServer extends Servidor implements Library
{
    //Private member variables
    private ArrayList<String> books;


    //Constructor
    public LibraryServer() throws RemoteException
    {
        // Llama al constructor de UnicastRemoteObject
        super("LibraryServer");
        // Inicializar las variables privadas
        books = new ArrayList<String>();
    }
    // Implementar todos los metodos de la interface remota
    public int number_of_books() throws RemoteException
    {
        return books.size();
    }

    public ArrayList<String> list_books() throws RemoteException
    {
        return books;
    }
    public void add_book(String _new_value) throws RemoteException {
        books.add(_new_value);
    }

    public static void main(String args[])
    {
        //Fijar el directorio donde se encuentra el java.policy
        //El segundo argumento es la ruta al java.policy
        System.setProperty("java.security.policy", "./java.policy");
        //Crear administrador de seguridad
        System.setSecurityManager(new SecurityManager());

        try
        {
            // Crear objeto remoto
            LibraryServer serv = new LibraryServer();
            // Registrar los servicios en el broker
            serv.add_servicio("list_books", "ArrayList<String>", null);
            serv.add_servicio("number_of_books", "int", null);
            ArrayList<String> param_add_book = new ArrayList<>();
            param_add_book.add("name_of_book");
            serv.add_servicio("add_book", "void", param_add_book);
            System.out.println("Services registered in Broker!");
            Library stub = (Library) UnicastRemoteObject.exportObject(serv, 0);
            // Registrar como servicio
            Naming.rebind("//" + server_ip + "/LibraryServer", stub);
            // Registrar en el broker
            BrokerInterface825 broker = (BrokerInterface825) Naming.lookup("//" + Broker825.hostName + "/Broker");
            System.out.println("Broker found!");
            broker.registrar_servidor("LibraryServer", server_ip);
            System.out.println("Servidor registrado en el Broker!");

        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
