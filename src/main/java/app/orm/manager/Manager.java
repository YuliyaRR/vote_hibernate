package app.orm.manager;

import app.orm.api.IManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager implements IManager {
    private EntityManagerFactory factory;

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
