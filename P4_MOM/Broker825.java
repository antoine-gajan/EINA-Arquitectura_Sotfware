import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.Vector;

public class Broker825 implements BrokerInterface825 {
    // Atributos privados
    private Vector<Cola> colas = new Vector<>();

    //nombre o IP del host donde reside el objeto servidor
    static String hostName = "155.210.154.201:32001"; //se puede usar "IPhostremoto:puerto"
    //Por defecto RMI usa el puerto 1099

    // Constructor
    Broker825() throws RemoteException {
        super();
    }

    public void declarar_cola(String nombre_cola, String consumidor) throws RemoteException {
        // If Cola is already declared
        for (Cola cola : this.colas) {
            if (Objects.equals(cola.getNombre(), nombre_cola)) {
                // Add consumidor to cola
                if (consumidor != null) {
                    cola.addConsumidor(consumidor);
                    System.out.println(consumidor + "añadido a cola" + nombre_cola);
                }
                return;
            }
        }
        // Else, add cola to colas
        Cola new_cola = new Cola(nombre_cola);
        // Add consumer if not null
        if (consumidor != null) {
            new_cola.addConsumidor(consumidor);
            System.out.println("Cola " + nombre_cola + " declarada con consumidor " + consumidor);
        }
        else{
            System.out.println("Cola " + nombre_cola + " declarada sin consumidor");
        }
        // Store
        colas.add(new_cola);
    }
    public void publicar(String nombre_cola, String mensaje) throws RemoteException {
        // Verify if Cola is declared
        for (Cola cola : this.colas) {
            if (Objects.equals(cola.getNombre(), nombre_cola)) {
                // Try to add message if consumer and try to consume directly
                System.out.println("Mensaje " + mensaje + " publicado en cola " + nombre_cola);
                if (cola.getNbConsumidores() > 0) {
                    cola.addMensaje(mensaje);
                    consumir(nombre_cola);
                }
            }
        }
    }

    public void consumir(String nombre_cola) throws RemoteException {
        // Verify if Cola is declared
        for (Cola cola : this.colas) {
            if (Objects.equals(cola.getNombre(), nombre_cola)) {
                String message = cola.popMessage();
                // Invoke callback method of current consumer
                try {
                    // Obtener referencia al objeto remoto
                    ConsumidorInterface stub = (ConsumidorInterface) Naming.lookup("//" + hostName + "/" + cola.getCurrentConsumidor());
                    // Change to next consumer
                    cola.nextConsumer();
                    // Invocar al metodo remoto
                    stub.callback(message);
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
    }

    public String registrar_consumidor(String host_remoto_IP_puerto) throws MalformedURLException, RemoteException, NotBoundException {
        // Get the remote object
        ConsumidorInterface cons = (ConsumidorInterface) Naming.lookup("//" + host_remoto_IP_puerto + "/Consumidor");
        // Register server in the broker registry
        String name = "Consumidor" + nb_consumidores();
        Naming.rebind("//" + hostName + "/" + name, cons);
        System.out.println("Consumidor registrado in Broker: " + name);
        return name;
    }

    public int nb_consumidores() throws MalformedURLException, RemoteException {
        return Naming.list("//" + hostName + "/").length;
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
