package app.dao.db.orm.manager;

import app.dao.db.orm.api.IManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager implements IManager {
    private final EntityManagerFactory factory;

    public Manager() {
        this.factory = Persistence.createEntityManagerFactory("app");
    }

    public EntityManager getEntityManager(){
        return this.factory.createEntityManager();
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
