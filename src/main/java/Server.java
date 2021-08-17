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
        System.out.println("Hello, the Server gets running");
        String clientsName;
        try (
                Socket clientSocket = serverSocket.accept();
                PrintWriter sendToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader getFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("Establish a new connection accepted from: " +
                    clientSocket.getRemoteSocketAddress().toString());

            sendToClient.println("Hi, enter your name:");
            clientsName = getFromClient.readLine();
            sendToClient.printf(String.format(
                    "Hi %s, you've connected to the LocalServer:%d via port #%d%nHave your luck!",
                    clientsName, S_PORT, clientSocket.getPort()));
        }
        System.out.printf("Client's name was %s. Connection closed.%n", clientsName);
    }

}