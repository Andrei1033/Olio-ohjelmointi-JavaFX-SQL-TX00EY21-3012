package module7_4.Datasource;

import jakarta.persistence.*;

public class MariaDbJpaConnection {
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getInstance(){
        if (em == null){
            if (emf == null){
                emf = Persistence.createEntityManagerFactory("CurrencyMariaDbUnit2");
            }
            em = emf.createEntityManager();
        }
        return em;
    }

}
