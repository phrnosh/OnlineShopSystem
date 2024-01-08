package mft.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
@Log4j
public class App  extends Application {

    public void start(Stage primaryStage) {
        log.info("main");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginFrame.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Log in");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}