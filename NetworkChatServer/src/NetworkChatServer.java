import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetworkChatServer {
    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String serverMessage = scanner.toString();
                String clientMessage = in.readUTF();
                if (clientMessage.equals("/end") || serverMessage.equals("/end")) {
                    break;
                }
                System.out.println("Клиент: " + clientMessage);
                System.out.println("Сервер: " + serverMessage);
                out.writeUTF("Сервер: " + serverMessage);
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
