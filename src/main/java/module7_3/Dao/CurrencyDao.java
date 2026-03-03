package module7_3.Dao;

import module7_3.Datasource.MariaDbJpaConnection;
import module7_3.Entity.Currency;

import jakarta.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CurrencyDao {
    public double getExchangeRate(String currencyCode) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        Currency currency = em.find(Currency.class, currencyCode);

        if (currency == null) {
            throw new RuntimeException("Currency not found");
        }
        return currency.getRate();
    }

    public Map<String, Object[]> getAllCurrencyInfo() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        List<Currency> currencies = em.createQuery("SELECT c FROM Currency c", Currency.class).getResultList();
        Map<String, Object[]> result = new HashMap<>();

        for (Currency c : currencies) {
            result.put(c.getCode(), new Object[]{c.getName(), c.getRate()});
        }
        return result;
    }

    public void insertCurrency(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();

        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }
}