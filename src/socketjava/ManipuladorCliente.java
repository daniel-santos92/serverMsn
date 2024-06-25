
package socketjava;

/**
 *
 * @author danielsantos
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ManipuladorCliente implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private List<ManipuladorCliente> clientes;
    private String nomeAnimal;

    public ManipuladorCliente(Socket socket, List<ManipuladorCliente> clientes, String nomeAnimal) throws IOException {
        this.socket = socket;
        this.clientes = clientes;
        this.nomeAnimal = nomeAnimal;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            out.println("Você está conectado como: " + nomeAnimal);

            String mensagem;
            while ((mensagem = in.readLine()) != null) {
                System.out.println("Recebido de " + nomeAnimal + ": " + mensagem);
                broadcastMensagem(nomeAnimal + ": " + mensagem);
            }
        } catch (IOException e) {
            System.err.println("Erro ao lidar com cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o socket: " + e.getMessage());
            }
            clientes.remove(this);
            System.out.println("Cliente desconectado: " + nomeAnimal);
        }
    }

    private void broadcastMensagem(String mensagem) {
        for (ManipuladorCliente cliente : clientes) {
            if (cliente != this) {
                cliente.out.println(mensagem);
            }
        }
    }
}


