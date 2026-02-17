package module6_1.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

import javafx.geometry.Insets;

import module6_1.Control.DictionaryController;

public class DictionaryView extends Application {
    private DictionaryController controller = new DictionaryController();

    @Override
    public void start(Stage stage){
        //--- Hakukomponentit ---
        Label searchLabel = new Label("Haettava:");
        TextField wordField = new TextField();
        wordField.setPromptText("Enter a word");
        Button searchButton = new Button("Search");

        HBox searchBox = new HBox(10);
        searchBox.getChildren().addAll(wordField, searchButton);

        Label searchResultLabel = new Label();

        // --- Lisää sana -komponentit ---
        Label addLabel = new Label("Lisää sana:");
        TextField newWordField = new TextField();
        newWordField.setPromptText("New word");
        TextField newMeaningField = new TextField();
        newMeaningField.setPromptText("New meaning");
        Button addButton = new Button("Add");

        VBox addVBox = new VBox(5);
        addVBox.getChildren().addAll(newWordField, newMeaningField, addButton);

        Label addNewLabel = new Label();

        //Layout
        VBox resultPane = new VBox(15);
        resultPane.setPadding(new Insets(15));
        resultPane.getChildren().addAll(
                searchLabel,
                searchBox,
                searchResultLabel,
                addLabel,
                addVBox,
                addNewLabel
        );


        // Tapahtumankäsittelijä
        searchButton.setOnAction(e -> {
            String word = wordField.getText();
            String result = controller.searchWord(word);
            if (result.equals("Word cannot be empty.")) {
                searchResultLabel.setText("❌ " + result);
            }
            else {
                searchResultLabel.setText("📖 Meaning of \"" + word + "\": " + result);
            }
        });

        addButton.setOnAction(e -> {
            String word = newWordField.getText();
            String meaning = newMeaningField.getText();

            String message = controller.addWord(word, meaning);

            if (message.equals("Word added successfully!")) {
                addNewLabel.setText("✅ Word \"" + word + "\" added.");
            }
            else {
                addNewLabel.setText("❌ " + message);
            }
        });

        //Scene ja Stage
        Scene scene = new Scene(resultPane,500,500);
        stage.setTitle("Virtual Dictionary");
        stage.setScene(scene);
        stage.show();
    }
}
