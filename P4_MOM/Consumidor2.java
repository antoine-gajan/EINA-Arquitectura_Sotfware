import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Consumidor2 implements ConsumidorInterface {
    static String broker_ip = "155.210.154.201:32001";
    static String consumidor_ip = "155.210.154.203:32001";

    public void callback(String mensaje) throws RemoteException {
        System.out.println("Mensaje recibido: " + mensaje);
    }

    public static void main(String[] args){
        if (args.length < 1) {
            System.out.println("Usage: java Consumidor <queue_name> <queue_name> ...");
            return;
        }
        //Fijar el directorio donde se encuentra el java.policy
        //El segundo argumento es la ruta al java.policy
        System.setProperty("java.security.policy", "./java.policy");

        //Crear administrador de seguridad
        if (System.getSecurityManager() == null) {
            //Crear administrador de seguridad
            System.setSecurityManager(new SecurityManager());
        }
        try
        {
            // Create a Consumidor
            Consumidor2 consumidor = new Consumidor2();
            // Create stub for the server
            ConsumidorInterface stub = (ConsumidorInterface) UnicastRemoteObject.exportObject(consumidor, 0);
            Naming.rebind("//" + consumidor_ip + "/Consumidor", stub);
            // Get Broker reference
            BrokerInterface825 broker = (BrokerInterface825) Naming.lookup("//" + broker_ip + "/Broker");
            // Register server in the broker registry
            String name = broker.registrar_consumidor(consumidor_ip);
            // Declare queue
            for (int i = 0; i < args.length; i++) {
                broker.declarar_cola(args[i], name);
            }
            System.out.println("Press CTRL+C to exit");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}