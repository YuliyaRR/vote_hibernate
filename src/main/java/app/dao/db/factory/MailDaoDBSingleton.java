package app.dao.db.factory;

import app.dao.api.IMailDao;
import app.dao.db.MailDaoDB;
import app.dao.db.orm.factory.ManagerSingleton;

import java.beans.PropertyVetoException;

public class MailDaoDBSingleton {
    private volatile static MailDaoDB instance;

    private MailDaoDBSingleton() {
    }

    public static IMailDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (MailDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new MailDaoDB(ManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }


}
