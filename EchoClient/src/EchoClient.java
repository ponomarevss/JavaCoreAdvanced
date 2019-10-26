import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public EchoClient() {
        openConnection();
        retrieveMessage();
        sendMessage();
    }

    public void openConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("failed to connect to " + SERVER_ADDRESS);
                }
                System.out.println("connected to " + SERVER_ADDRESS + ": " + socket.isConnected());
            }
        }).start();
    }

    public void retrieveMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    while (true) {
                        String messageFromServer = in.readUTF();
                        System.out.println(messageFromServer);
                        if (messageFromServer.contains("/end")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("can't readUTF any more");
//                    e.printStackTrace();
                }
                closeConnection();
            }
        }).start();
    }

    public void sendMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                String message;
                try {
                    do {
                        message = scanner.nextLine();
                        out.writeUTF(message);
                    } while (!message.equalsIgnoreCase("/end"));
                    closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
        new EchoClient();
    }
}
