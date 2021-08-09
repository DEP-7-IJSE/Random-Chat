/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {
        ArrayList<Socket> connectedSockets = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            System.out.println("Server has been started");
            while (true) {
                Socket localSocket = serverSocket.accept();
                connectedSockets.add(localSocket);
                System.out.println("Listening for incoming connections...");
                new Thread(() -> {
                    try {
                        DataInputStream dis;
                        DataOutputStream dos;
                        String message;

                        dis = new DataInputStream(localSocket.getInputStream());

                        while (true) {
                            System.out.println("Connected");
                            message = dis.readUTF();
                            for (Socket connectedSocket : connectedSockets) {
                                dos = new DataOutputStream(connectedSocket.getOutputStream());
                                dos.writeUTF(message);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
