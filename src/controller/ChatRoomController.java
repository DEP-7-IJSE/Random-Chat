/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatRoomController {
    public TextField txtMessage;
    public JFXButton btnSend;
    public JFXTextArea txtAreaMsg;

    private static Socket socket;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    public void initialize() {
        txtMessage.requestFocus();
        new Thread(() -> {
            try {
                socket = new Socket("8.tcp.ngrok.io", 15002);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                try {
                    String message;
                    while (true) {
                        message = dis.readUTF();
                        txtAreaMsg.setText(txtAreaMsg.getText().trim() + "\n" + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void txtMessage_OnAction(ActionEvent actionEvent) {
        btnSend.fire();
    }

    public void btnSend_OnAction(ActionEvent actionEvent) {
        String message = System.getProperty("app.user") + ":\t" + txtMessage.getText();
        try {
            dos.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
