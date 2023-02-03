package app.dao.db.factory;

import app.dao.db.SingerDaoDB;
import app.dao.api.ISingerDao;
import app.orm.factory.ManagerSingleton;

import java.beans.PropertyVetoException;

public class SingerDaoDBSingleton {
    private volatile static SingerDaoDB instance;

    private SingerDaoDBSingleton() {
    }

    public static ISingerDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (SingerDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new SingerDaoDB(ManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }


}
