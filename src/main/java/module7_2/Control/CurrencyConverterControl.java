package module7_2.Control;

import module7_2.Entity.CurrencyConverter;

public class CurrencyConverterControl {
    private CurrencyConverter converter;

    public CurrencyConverterControl() {
        converter = new CurrencyConverter();
    }

    public String convert(String amountInput, String from, String to) {
        if (amountInput == null || amountInput.isBlank()) {
            return "❌ Please enter an amount.";
        }

        try {
            double amount = Double.parseDouble(amountInput);

            if (amount <= 0) {
                return "❌ Amount must be positive And bigger than 0.";
            }

            double result = converter.convert(amount, from, to);
            return String.format("✅ %.2f %s = %.2f %s", amount, from, result, to);
        }
        catch (NumberFormatException e) {
            return "❌ Invalid number format.";
        }
        catch (IllegalArgumentException e) {
            return "❌ " + e.getMessage();
        }
    }

    public java.util.Set<String> getAvailableCurrencies() {
        return converter.getCurrencies();
    }
}
