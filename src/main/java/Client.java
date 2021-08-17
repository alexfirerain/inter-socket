import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket clientSocket = new Socket("localhost", Server.S_PORT);
                BufferedReader downfromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter uptoServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Scanner userInput = new Scanner(System.in)
        )
        {
            System.out.println("Connected to the LocalServer:" + Server.S_PORT);
            System.out.println(downfromServer.readLine());
            String ourResponse = userInput.nextLine();
            uptoServer.write(ourResponse);
            uptoServer.newLine();
            uptoServer.flush();

            String msg;
            while ((msg = downfromServer.readLine()) != null)
                System.out.println(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
