package module7_3.Application;

import module7_3.Dao.CurrencyDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CurrencyConverter {
    private Map<String, Double> exchangeRates;
    private Map<String, String> currencyNames;
    private CurrencyDao currencyDao;

    public CurrencyConverter() {
        this.exchangeRates = new HashMap<>();
        this.currencyNames = new HashMap<>();
        this.currencyDao = new CurrencyDao();

        // Load all data from database
        loadCurrenciesFromDatabase();
    }

    private void loadCurrenciesFromDatabase() {
        // Clear any existing data
        exchangeRates.clear();
        currencyNames.clear();

        // Get all currency information from database
        Map<String, Object[]> allCurrencies = currencyDao.getAllCurrencyInfo();

        for (Map.Entry<String, Object[]> entry : allCurrencies.entrySet()) {
            String currencyCode = entry.getKey();
            Object[] currencyInfo = entry.getValue();
            String currencyName = (String) currencyInfo[0];
            Double exchangeRate = (Double) currencyInfo[1];

            exchangeRates.put(currencyCode, exchangeRate);
            currencyNames.put(currencyCode, currencyName);
        }
    }

    public Set<String> getCurrencies() {
        if (exchangeRates.isEmpty()) {
            loadCurrenciesFromDatabase(); // Reload if empty
        }
        return exchangeRates.keySet();
    }

    public String getCurrencyName(String currencyCode) {
        if (currencyNames.isEmpty()) {
            loadCurrenciesFromDatabase();
        }
        return currencyNames.get(currencyCode);
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
