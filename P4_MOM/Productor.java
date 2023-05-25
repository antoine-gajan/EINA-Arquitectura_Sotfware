import java.rmi.Naming;

public class Productor {
    static String broker_ip = "155.210.154.201:32001";

    public static void main(String[] args) {
        // Check arguments
        if (args.length < 2) {
            System.out.println("Usage: java Productor <queue_name> <message>");
            return;
        }
        try {
            // Obtener referencia al objeto remoto
            BrokerInterface825 stub = (BrokerInterface825) Naming.lookup("//" + broker_ip + "/Broker");
            // Invocar al metodo remoto
            stub.declarar_cola(args[0], null);
            // Get message
            String message = "";
            for (int i = 1; i < args.length; i++) {
                message += args[i] + " ";
            }
            // Publish message
            stub.publicar(args[0], message);
            // Display message on terminal
            System.out.println("Mensaje publicado: " + message);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

}
