package main.client;

import main.MyServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private MyServer myServer;

    private String clientName;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket socket, MyServer myServer) {
        try {
            this.socket = socket;
            this.myServer = myServer;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    readMessages();
                } catch (IOException e) {
                    System.out.println("The connection has been interrupted");
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create client handler", e);
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String clientMessage = in.readUTF();
            if (clientMessage.startsWith("/auth")) {
                authentication(clientMessage);
            }
            else if (clientMessage.startsWith("/w")) {
                myServer.privateMessage(clientName + " " + clientMessage);
            }
            else if (clientMessage.equals("/end")) {
                return;
            }
            else {
                myServer.broadcastMessage(clientName + ": " + clientMessage);
            }
        }
    }

    private void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMessage(clientName + " is offline");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Failed to close socket!");
            e.printStackTrace();
        }
    }

    private void authentication(String clientMessage) throws IOException {
        String nick;
        do {
            String[] loginAndPasswords = clientMessage.split("\\s+");

            if (loginAndPasswords.length < 3) {
                sendMessage("At least one of the fields is empty");
                return;
            }

            String login = loginAndPasswords[1];
            String password = loginAndPasswords[2];

            nick = myServer.getAuthService().getNickByLoginPass(login, password);
            if (nick == null) {
                sendMessage("Incorrect login and password");
                return;
            }

            if (myServer.isNickBusy(nick)) {
                sendMessage("This nick is already in use");
            }

            sendMessage("/authok" + nick);
            clientName = nick;
            myServer.broadcastMessage(clientName + " is online");
            myServer.subscribe(this);

        } while (nick == null);
    }


    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.err.println("Failed to send message to user " + clientName + " : " + message);
            e.printStackTrace();
        }
    }


    public String getClientName() {
        return clientName;
    }

}
