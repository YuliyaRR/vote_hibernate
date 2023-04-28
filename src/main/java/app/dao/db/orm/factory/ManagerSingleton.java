package app.dao.db.orm.factory;

import app.dao.db.orm.api.IManager;
import app.dao.db.orm.manager.Manager;

public class ManagerSingleton {
    private volatile static IManager instance;

    private ManagerSingleton() {
    }

    public static IManager getInstance() {
        if (instance == null) {
            synchronized (ManagerSingleton.class){
                if(instance == null){
                    instance = new Manager();
                }
            }
        }
        return instance;
    }
}
