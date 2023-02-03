package app.dao.db.factory;

import app.dao.db.VotingDaoDB;
import app.dao.api.IVotingDao;
import app.orm.factory.ManagerSingleton;

import java.beans.PropertyVetoException;


public class VotingDaoDBSingleton {
    private volatile static VotingDaoDB instance;

    private VotingDaoDBSingleton() {
    }

    public static IVotingDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (VotingDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new VotingDaoDB(ManagerSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
