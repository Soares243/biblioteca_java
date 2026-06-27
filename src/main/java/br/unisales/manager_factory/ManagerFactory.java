package br.unisales.manager_factory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ManagerFactory implements AutoCloseable {
    public static final String DEFAULT_PERSISTENCE_UNIT = "PostgresPU";

    private final EntityManagerFactory emf;

    public ManagerFactory() {
        this(DEFAULT_PERSISTENCE_UNIT);
    }

    public ManagerFactory(String persistenceUnit) {
        this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
    }

    public EntityManagerFactory get() {
        return this.emf;
    }

    @Override
    public void close() {
        this.emf.close();
    }

}
