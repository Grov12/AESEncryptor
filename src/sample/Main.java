package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String css = Main.class.getResource("style.css").toExternalForm();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getStylesheets().add(css);
        primaryStage.setTitle("AESencryptor");
        primaryStage.setScene(new Scene(root, 540, 410));
        primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
