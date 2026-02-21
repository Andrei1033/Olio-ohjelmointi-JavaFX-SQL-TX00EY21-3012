package module6_4.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotebookView  extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/notebook.fxml"));

        Scene scene = new Scene(loader.load());

        stage.setTitle("Notebook App");
        stage.setScene(scene);
        stage.show();
    }
}
