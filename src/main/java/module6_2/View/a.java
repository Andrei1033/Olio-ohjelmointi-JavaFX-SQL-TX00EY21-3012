package module6_2.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import module6_2.Control.CurrencyConverterControl;

public class a extends Application {
    private CurrencyConverterControl currencyConverterControl;

    @Override
    public void start(Stage stage) {
        currencyConverterControl = new CurrencyConverterControl();

        Label instructions = new Label("Enter amount and press Convert.");
        instructions.setStyle("-fx-font-style: italic;");

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();

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

        Label resultTitleLabel = new Label("Converted Amount:");
        TextField resultField = new TextField();
        resultField.setEditable(false); // Estää kirjoittamisen
        resultField.setPromptText("Result will appear here...");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        // Asettelu
        gridPane.add(instructions, 0, 0, 2, 1);
        gridPane.add(amountLabel, 0, 1);
        gridPane.add(amountField, 0, 2);
        gridPane.add(fromLabel, 0, 3);
        gridPane.add(fromComboBox, 0, 4);
        gridPane.add(toLabel, 1, 3);
        gridPane.add(toComboBox, 1, 4);
        gridPane.add(convertButton, 0, 5, 2, 1);
        gridPane.add(resultTitleLabel, 0, 6);
        gridPane.add(resultField, 0, 7, 2, 1);

        // TÄRKEÄ OSA: Tapahtumankäsittelijä
        convertButton.setOnAction(e -> {
            String amount = amountField.getText();
            String from = fromComboBox.getValue();
            String to = toComboBox.getValue();

            // Haetaan tulos Controllerilta
            String result = currencyConverterControl.convert(amount, from, to);

            // Asetetaan tulos tekstikenttään
            resultField.setText(result);

            if (result.startsWith("❌")) {
                resultField.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            } else {
                resultField.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            }
        });

        Scene scene = new Scene(gridPane, 450, 450);
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }
}