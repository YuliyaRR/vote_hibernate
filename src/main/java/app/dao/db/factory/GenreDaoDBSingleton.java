package app.dao.db.factory;

import app.dao.db.GenreDaoDB;
import app.dao.api.IGenreDao;
import app.dao.db.orm.factory.ManagerSingleton;

import java.beans.PropertyVetoException;

public class GenreDaoDBSingleton {
    private volatile static GenreDaoDB instance;

    private GenreDaoDBSingleton() {
    }

    public static IGenreDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (GenreDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new GenreDaoDB(ManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
