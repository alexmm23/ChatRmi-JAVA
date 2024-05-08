import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class implementacionClienteChat extends UnicastRemoteObject implements chatCliente, Runnable{
    public chatServidor servidor;
    public String nombre  = null;
    implementacionClienteChat(String nombre, chatServidor servidor) throws java.rmi.RemoteException {
        this.nombre = nombre;
        this.servidor = servidor;
        servidor.registro(this);
    }
    @Override
    public void mensajeCliente(String mensaje) throws RemoteException{
        System.out.println(mensaje);
    }
    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        String mensaje;
        while (true) {
            mensaje = s.nextLine();
            try {
                servidor.mensaje(nombre + " dice: " + mensaje);
            } catch (RemoteException e) {
                System.out.println("Excepcion en implementacionClienteChat: " + e);
                e.printStackTrace();
            }
        }
    }
}
