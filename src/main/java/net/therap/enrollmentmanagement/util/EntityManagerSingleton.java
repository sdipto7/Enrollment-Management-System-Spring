package net.therap.enrollmentmanagement.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author rumi.dipto
 * @since 8/29/21
 */
public class EntityManagerSingleton {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private static EntityManagerSingleton entityManagerSingleton;

    public EntityManagerSingleton() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit-1");

        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManagerSingleton getInstance() {
        if (entityManagerSingleton == null) {
            entityManagerSingleton = new EntityManagerSingleton();
        }
        return entityManagerSingleton;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
