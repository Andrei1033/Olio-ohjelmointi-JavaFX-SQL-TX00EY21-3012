package module6_1.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import module6_1.Control.DictionaryController;

public class DictionaryView extends Application {
    private DictionaryController controller = new DictionaryController();

    @Override
    public void start(Stage stage){
        //komponentit
        TextField wordField = new TextField();
        wordField.setPromptText("Enter a word");

        Button searchButton = new Button("Search");

        Label resultLabel = new Label();

        //Layout
        FlowPane resultPane = new FlowPane();
        resultPane.setHgap(10);
        resultPane.setVgap(10);
        resultPane.setPadding(new Insets(10,10,10,10));

        resultPane.getChildren().addAll(resultLabel,wordField,searchButton);

        // Tapahtumankäsittelijä
        searchButton.setOnAction(e -> {
            String word = wordField.getText();
            String result = controller.searchWord(word);
            resultLabel.setText(result);
        });

        //Scene ja Stage
        Scene scene = new Scene(resultPane,300,200);
        stage.setTitle("Virtual Dictionary");
        stage.setScene(scene);
        stage.show();
    }
}
