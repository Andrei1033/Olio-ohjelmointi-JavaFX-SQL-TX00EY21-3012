package module7_2.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import module7_2.Control.CurrencyConverterControl2;

public class CurrencyConverterView2 extends Application {
    private CurrencyConverterControl2 currencyConverterControl;

    @Override
    public void start(Stage stage){
        currencyConverterControl = new CurrencyConverterControl2();

        // --- Komponentit ---
        Label instructions = new Label("Enter amount, select currencies and press Convert.");
        instructions.setStyle("-fx-font-style: italic; -fx-text-fill: #555;");

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("e.g. 100");

        Label fromLabel = new Label("From:");
        ComboBox<String> fromComboBox = new ComboBox<>();
        fromComboBox.getItems().addAll(currencyConverterControl.getAvailableCurrencies());
        fromComboBox.setValue("EUR");

        Label toLabel = new Label("To:");
        ComboBox<String> toComboBox = new ComboBox<>();
        toComboBox.getItems().addAll(currencyConverterControl.getAvailableCurrencies());
        toComboBox.setValue("USD");

        Button convertButton = new Button("Convert");
        convertButton.setMaxWidth(Double.MAX_VALUE);

        Label resultLabel = new Label("Converted Amount:");
        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.setStyle("-fx-font-weight: bold;");

        // --- Layout ---
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(instructions, 0, 0, 2, 1);

        gridPane.add(amountLabel, 0, 1);
        gridPane.add(amountField, 0, 2);

        // "Right on top" asettelu
        gridPane.add(fromLabel, 0, 3);
        gridPane.add(fromComboBox, 0, 4);

        gridPane.add(toLabel, 1, 3);
        gridPane.add(toComboBox, 1, 4);

        gridPane.add(convertButton, 0, 5, 2, 1);

        gridPane.add(resultLabel, 0, 6);
        gridPane.add(resultField, 0, 7, 2, 1);

        // --- Tapahtuma ---
        convertButton.setOnAction(e -> {
            String amount = amountField.getText();
            String from = fromComboBox.getValue();
            String to = toComboBox.getValue();

            String result = currencyConverterControl.convert(amount,from,to);
            resultField.setText(result);

            if (result.startsWith("❌")){
                resultField.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold;");
            }
            else{
                resultField.setStyle("-fx-text-fill: #008000; -fx-font-weight: bold;");
            }
        });

        // --- Scene ---
        Scene scene = new Scene(gridPane, 450, 350);
        try {
            scene.getStylesheets().add(getClass().getResource("/Currency.css").toExternalForm());
        } catch (Exception ex) {
            System.out.println("CSS file not found, continuing with defaults.");
        }
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }



}
