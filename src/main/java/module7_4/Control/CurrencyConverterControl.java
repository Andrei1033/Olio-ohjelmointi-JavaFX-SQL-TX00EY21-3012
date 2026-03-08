package module7_4.Control;

import module7_4.Application.CurrencyConverter;

import module7_4.Dao.CurrencyDao;
import module7_4.Dao.TransactionDao;
import module7_4.Entity.Transaktion;
import module7_4.Entity.Currency2;

public class CurrencyConverterControl {
    private CurrencyConverter converter;

    private TransactionDao transactionDao = new TransactionDao();
    private CurrencyDao currencyDao = new CurrencyDao();

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

            Currency2 sourceCurrency = currencyDao.find(from);
            Currency2 targetCurrency = currencyDao.find(to);
            
            Transaktion transaktion = new Transaktion(
                    amount,
                    result,
                    sourceCurrency,
                    targetCurrency
            );
            
            transactionDao.persist(transaktion);

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
