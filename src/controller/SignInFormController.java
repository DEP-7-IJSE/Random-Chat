/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import service.UserService;

import java.io.IOException;

public class SignInFormController {
    public JFXTextField txtUser;
    public JFXPasswordField txtPassword;

    private final UserService USER_SERVICE = new UserService();

    public void signIn_OnAction(ActionEvent actionEvent) throws IOException {
        User user = new User(
                txtUser.getText(),
                txtPassword.getText()
        );
        if (USER_SERVICE.userIsExists(user.getUser())) {
            if (USER_SERVICE.authentication(user)) {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/ChatRoom.fxml"))));
                stage.show();
                stage.setResizable(false);
                stage.setTitle("Random Chat Room");
                ((Stage) (txtUser.getScene().getWindow())).close();
                System.setProperty("app.user", txtUser.getText());
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid userName or Password").show();
                txtUser.requestFocus();
            }
        }
    }

    public void signup_OnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) txtUser.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/SignUpForm.fxml"))));
    }
}
