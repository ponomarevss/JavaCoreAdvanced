package main;

import main.auth.AuthService;
import main.auth.BaseAuthService;
import main.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private static final int PORT = 8189;

    private final AuthService authService= new BaseAuthService();

    private List<ClientHandler> clients = new ArrayList<>();

    public MyServer() {
        System.out.println("Server is running");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            authService.start();
            while (true) {
                System.out.println("Awaiting client connection ...");
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                System.out.println("Client has connected");
            }

        } catch (IOException e) {
            System.err.println("Server running error. Cause: " + e.getMessage());
            e.printStackTrace();
        } finally {
            authService.stop();
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
        System.out.println(message);
    }

    public void privateMessage(String message) {
        String[] nicksAndMessage = message.split("\\s++", 4);
        String senderNick;
        String receiverNick;
        String privateMessage;
        if (nicksAndMessage.length == 4) {
            senderNick = nicksAndMessage[0];
            receiverNick = nicksAndMessage[2];
            privateMessage = nicksAndMessage[3];
        }
        else {
            return;
        }
        ClientHandler sender = null;
        ClientHandler receiver = null;
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(senderNick)) {
                sender = client;
                client.sendMessage("to " + receiverNick + ": " + privateMessage);
            }
            else if (client.getClientName().equals(receiverNick)) {
                receiver = client;
                client.sendMessage("from " + senderNick + ": " + privateMessage);
            }
        }
        if (receiver == null) sender.sendMessage("Failed to send private message to " + receiverNick);
    }
}
