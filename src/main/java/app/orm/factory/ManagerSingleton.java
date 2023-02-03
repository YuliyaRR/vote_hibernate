package app.orm.factory;

import app.orm.api.IManager;
import app.orm.manager.Manager;

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
