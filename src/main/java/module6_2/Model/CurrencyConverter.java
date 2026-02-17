package module6_2.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CurrencyConverter {
    private Map<String, Double> exchangeRates;
    private Map<String, String> names;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        names = new HashMap<>();

        // Peruskurssit (esimerkkiarvot)
        add("EUR", "Euro", 1.0);
        add("USD", "US Dollar", 1.08);
        add("GBP", "British Pound", 0.85);
        add("JPY", "Japanese Yen", 162.5);
    }

    private void add(String currency, String name, double rate) {
        exchangeRates.put(currency, rate);
        names.put(currency, name);
    }

    public Set<String> getCurrencies() {
        return exchangeRates.keySet();
    }

    public double convert(double amount, String from, String to) {
        if (!exchangeRates.containsKey(from) || !exchangeRates.containsKey(to)) {
            throw  new IllegalArgumentException("Unsupported currency.");
        }
        // Muunnetaan ensin euroiksi
        double amountInEUR = amount / exchangeRates.get(from);
        // Sitten kohdevaluuttaan
        return amountInEUR * exchangeRates.get(to);
    }
}
