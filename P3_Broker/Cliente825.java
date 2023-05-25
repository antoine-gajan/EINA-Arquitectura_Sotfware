import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Cliente825 {
    static String broker_ip = "155.210.154.199:32001";
    public static void main(String[] args){
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
            //Paso 1 - Obtener una referencia al objeto servidor creado anteriormente
            BrokerInterface825 broker = (BrokerInterface825) Naming.lookup("//" + Broker825.hostName + "/Broker");
            System.out.println("Broker found!");

            boolean continuer = true;
            while (continuer) {
                //Paso 2 - Ver todas las metodas de los servidores y pedir al usuario lo que quiere
                ArrayList<String> list_servicios = broker.list_servicios();
                //ArrayList<ArrayList<String>> list_params = broker.get_parametros_servicios();
                System.out.println("Servicios disponibles:");
                int i = 1, num_choice = 0;
                for (String service : list_servicios) {
                    System.out.println("\t" + i++ + ". " + service);
                }
                // Pedir hasta que la respuesta sea correcta
                while (num_choice < 1 || num_choice > list_servicios.size()) {
                    System.out.println("Eleccion : ");
                    Scanner in = new Scanner(System.in);
                    String choice = in.nextLine();
                    num_choice = Integer.parseInt(choice);
                }
                // Paso 3 - Invokar el metodo
                //ArrayList<String> params_to_fill = list_params.get(num_choice - 1);
                Vector<String> params_given = new Vector<>();
                /*if (params_to_fill.size() > 0) {
                    for (String param : params_to_fill) {
                        System.out.println("Parametro " + param + ": ");
                        Scanner in = new Scanner(System.in);
                        String choice = in.nextLine();
                        params_given.add(choice);
                    }
                }*/
                String respuesta = "";
                if (list_servicios.get(num_choice - 1).equals("add_book")) {
                    System.out.println("Parametro name_of_book: ");
                    Scanner in = new Scanner(System.in);
                    String choice = in.nextLine();
                    params_given.add(choice);
                    respuesta = broker.ejecutar_servicio(list_servicios.get(num_choice - 1), params_given.get(0));
                }
                else{
                    respuesta = broker.ejecutar_servicio(list_servicios.get(num_choice - 1), null);
                }
                System.out.println(respuesta);
                // Paso 4 - Preguntar si quiere continuar
                System.out.println("Desea continuar? (y/n)");
                Scanner in = new Scanner(System.in);
                String choice = in.nextLine();
                if (choice.equals("n")) {
                    continuer = false;
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}