package socketjava;

/**
 *
 * @author danielsantos
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private static final int PORTA = 12345;
    private static List<ManipuladorCliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado. Aguardando clientes...");

            while (true) {
                Socket socketCliente = serverSocket.accept();
                String nomeAnimal = GeradorNomeAnimal.getNomeAleatorioAnimal();
                System.out.println("Cliente conectado: " + nomeAnimal);

                ManipuladorCliente manipulador = new ManipuladorCliente(socketCliente, clientes, nomeAnimal);
                clientes.add(manipulador);
                new Thread(manipulador).start();
            }
        } catch (IOException e) {
            System.err.println("Não foi possível iniciar o servidor: " + e.getMessage());
        }
    }
}

