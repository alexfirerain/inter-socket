import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static final int S_PORT = 8111;

    public static void main(String[] args) throws IOException {

        serverSocket = new ServerSocket(S_PORT);

        try (Socket clientSocket = serverSocket.accept();
             PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
             )
        {
            System.out.println("Establish a new connection accepted from: " +
                    clientSocket.getRemoteSocketAddress().toString());

            toClient.println("Hi, enter your name:");
            final String clientName = fromClient.readLine();

            toClient.println(String.format("Hi %s, you've connected via port #%d",
                    clientName, clientSocket.getPort()));

        }

    }

}

