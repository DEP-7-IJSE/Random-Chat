/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static Socket socket;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    private Client() {
        try {
            socket = new Socket("localhost", 151);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage() {

    }
}
