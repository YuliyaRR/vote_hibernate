package app.dao.db.orm.api;

import javax.persistence.EntityManager;

public interface IManager {

    EntityManager getEntityManager();

    void close();
}
