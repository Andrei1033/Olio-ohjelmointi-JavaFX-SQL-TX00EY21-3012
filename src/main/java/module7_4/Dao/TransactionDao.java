package module7_4.Dao;

import module7_4.Entity.Transaktion;
import jakarta.persistence.EntityManager;
import module7_4.Datasource.MariaDbJpaConnection;

public class TransactionDao {
    public void persist(Transaktion transaktion) {
        EntityManager entityManager = MariaDbJpaConnection.getInstance();

        entityManager.getTransaction().begin();
        entityManager.persist(transaktion);
        entityManager.getTransaction().commit();
    }
}
