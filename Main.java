package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import page.UploadPage;

public class Main extends Application {
    private Scene sc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sc = new Scene(new UploadPage(), 600, 400);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Upload Image");
        primaryStage.show();
    }
}
