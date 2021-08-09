/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;

public class ChatRoomController {
    public JFXTextField txtMessage;
    public JFXButton btnSend;
    public JFXTextArea txtAreaMsg;

    public void txtMessage_OnAction(ActionEvent actionEvent) {
        btnSend.fire();
    }

    public void btnSend_OnAction(ActionEvent actionEvent) {

    }
}
