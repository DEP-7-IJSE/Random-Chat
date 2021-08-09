/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import service.UserService;

import java.io.IOException;

import static util.ValidationUtil.isValidPassword;
import static util.ValidationUtil.isValidUser;

public class SignUpFormController {
    private final UserService USER_SERVICE = new UserService();
    public JFXTextField txtUser;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtRePassword;

    public void btnSignUp_OnAction(ActionEvent actionEvent) throws IOException {

        if (!isValidated()) return;

        User user = new User(
                txtUser.getText(),
                txtPassword.getText()
        );

        if (USER_SERVICE.userIsExists(user)) {
            new Alert(Alert.AlertType.ERROR, "User is Already exists").show();
        } else {
            boolean saved = USER_SERVICE.writeDataFile(user);
            if (saved) {
                Stage stage = new Stage();
                stage.setScene(FXMLLoader.load(this.getClass().getResource("/view/ChatRoom.fxml")));
                System.setProperty("app.user", txtUser.getText());
            } else {
                new Alert(Alert.AlertType.ERROR, "Saving Failed").show();

            }
        }

    }


    private boolean isValidated() {
        String user = txtUser.getText();
        String password = txtPassword.getText();
        String rePassword = txtRePassword.getText();

        if (user.trim().isEmpty() || !isValidUser(user)) {
            new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
            txtUser.requestFocus();
            return false;
        } else if (!isValidPassword(password)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassword.requestFocus();
            return false;
        } else if (!password.equals(rePassword)) {
            new Alert(Alert.AlertType.ERROR, "Password doesn't match").show();
            txtRePassword.requestFocus();
            return false;
        }
        return true;
    }
}
