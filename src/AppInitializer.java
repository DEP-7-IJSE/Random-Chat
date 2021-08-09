/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.UserService;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/SignInForm.fxml"))));
        primaryStage.centerOnScreen();
        primaryStage.show();
        UserService.readDataFromFile();
        primaryStage.setResizable(false);
    }
}
