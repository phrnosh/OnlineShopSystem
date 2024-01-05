package mft;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
@Log4j
public class App extends Application {

    @Override
    public void start(Stage primaryStage)  {
      try {
          log.info("Main");
          Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("view/loginFrame.fxml"));
          Scene scene = new Scene(root);
          primaryStage.setScene(scene);
          primaryStage.setTitle("Register Information");
          primaryStage.show();
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
