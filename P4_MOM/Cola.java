import java.util.LinkedList;
import java.util.Vector;

public class Cola {
    private String nombre;
    private LinkedList<String> mensajes;
    private Vector<String> consumidores;
    private int currentConsumer = 0;

    Cola(String nombre) {
        this.nombre = nombre;
        this.mensajes = new LinkedList<>();
        this.consumidores = new Vector<>();
    }

    String getNombre() {
        return this.nombre;
    }

    void addMensaje(String mensaje) {
        this.mensajes.add(mensaje);
    }

    String popMessage() {
        return this.mensajes.poll();
    }

    void addConsumidor(String consumidor) {
        this.consumidores.add(consumidor);
    }

    int getNbMensajes() {
        return this.mensajes.size();
    }

    int getNbConsumidores() {
        return this.consumidores.size();
    }

    String getCurrentConsumidor(){
        return this.consumidores.get(this.currentConsumer);
    }

    void nextConsumer(){
        this.currentConsumer = (this.currentConsumer + 1) % this.consumidores.size();
    }

}
