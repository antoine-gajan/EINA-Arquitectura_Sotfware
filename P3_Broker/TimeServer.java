import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TimeServer extends Servidor implements Time{
    //Private member variables

    //Constructor
    public TimeServer() throws RemoteException
    {
        // Llama al constructor de Servidor
        super("TimeServer");
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
            TimeServer serv = new TimeServer();
            serv.add_servicio("get_hour", "int", null);
            serv.add_servicio("get_month", "int", null);
            serv.add_servicio("get_year", "int", null);
            serv.add_servicio("get_day", "int", null);
            serv.add_servicio("get_date", "LocalDate", null);
            System.out.println("Services registered in Broker!");
            Time stub = (Time) UnicastRemoteObject.exportObject(serv, 0);
            // Registrar como servicio
            Naming.rebind("//" + serv.server_ip + "/TimeServer", stub);
            // Registrar en el broker
            BrokerInterface825 broker = (BrokerInterface825) Naming.lookup("//" + Broker825.hostName + "/Broker");
            System.out.println("Broker found!");
            broker.registrar_servidor("TimeServer", serv.server_ip);
            System.out.println("TimeServer registrado en el Broker!");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
