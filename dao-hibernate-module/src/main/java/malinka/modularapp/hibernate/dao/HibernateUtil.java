package malinka.modularapp.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

/**
 * This is the utility class used to create the entity manager.
 *
 * @author Malinka Phann
 */
public class HibernateUtil {

    private static EntityManager entityManager;

    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager(String persistence) {
        Objects.requireNonNull(persistence, "the input persistence must not be null");
        if (persistence.isEmpty()) throw new IllegalArgumentException("the input persistence must not be empty");
        if (entityManager == null) {
            factory = Persistence.createEntityManagerFactory(persistence);
            if (Objects.nonNull(factory)) {
                entityManager = factory.createEntityManager();
            }
        }
        return entityManager;
    }

    /**
     * Close both
     */
    public static void shutdown() {
        if ((Objects.nonNull(factory)) && (factory.isOpen())) {
            factory.close();
        }
        if ((Objects.nonNull(entityManager)) && (entityManager.isOpen())) {
            entityManager.close();
        }
    }
}
