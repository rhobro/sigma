package tech.neurobyte.dev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import tech.neurobyte.dev.utils.R;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sigma");
        stage.getIcons().clear();
        stage.getIcons().add(new Image("images/logo.png", 0, 0, true, true, false));
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("Press Esc");
        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination(KeyCode.ESCAPE.toString()));
        stage.setScene(new Scene(FXMLLoader.load(R.getResource("fxml/main.fxml"))));

        Cortex.stage = stage;
        Cortex.stage.show();
    }
}
