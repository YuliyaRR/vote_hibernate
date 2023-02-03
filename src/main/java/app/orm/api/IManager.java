package app.orm.api;

import javax.persistence.EntityManager;

public interface IManager {

    EntityManager getEntityManager();

    void close();
}
