package module7_4.Dao;

import jakarta.persistence.EntityManager;
import module7_4.Datasource.MariaDbJpaConnection;
import module7_4.Entity.Currency2;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CurrencyDao {
    public double getExchangeRate(String currencyCode) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        Currency2 currency = em.find(Currency2.class, currencyCode);

        if (currency == null) {
            throw new RuntimeException("Currency not found");
        }
        return currency.getRate();
    }

    public Map<String, Object[]> getAllCurrencyInfo() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        List<Currency2> currencies = em.createQuery("SELECT c FROM Currency2 c", Currency2.class).getResultList();
        Map<String, Object[]> result = new HashMap<>();

        for (Currency2 c : currencies) {
            result.put(c.getCode(), new Object[]{c.getName(), c.getRate()});
        }
        return result;
    }

    public void insertCurrency(Currency2 currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();

        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }

    public Currency2 find(String code) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        return em.find(Currency2.class, code);
    }
}