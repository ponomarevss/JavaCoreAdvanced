import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;


    public EchoServer() {
        new Thread(this::runServer).start();
        new Thread(this::sendMessage).start();
    }

    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            do {
                System.out.println("server is running, waiting for connection...");
                socket = serverSocket.accept();
                System.out.println("client is connected: " + socket.isConnected());
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    String str;
                    try {
                        str = in.readUTF();
                    } catch (IOException e) {
                        System.out.println("in.readUTF is shut down");
                        break;
                    }
                    if (str.equals("/end")) {
                        break;
                    }
                    System.out.println("client: " + str);
                    out.writeUTF("echo: " + str);
                }
                System.out.println("client is disconnected" + System.lineSeparator());

            } while (!socket.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        Scanner scanner = new Scanner(System.in);
        String message;
        try {
            while (true) {
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("/end")) {
                    break;
                }
                out.writeUTF("server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EchoServer();
    }
}
