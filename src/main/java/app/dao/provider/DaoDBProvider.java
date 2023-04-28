package app.dao.provider;

import app.dao.api.IGenreDao;
import app.dao.api.IMailDao;
import app.dao.api.ISingerDao;
import app.dao.api.IVotingDao;
import app.dao.db.factory.GenreDaoDBSingleton;
import app.dao.db.factory.MailDaoDBSingleton;
import app.dao.db.factory.SingerDaoDBSingleton;
import app.dao.db.factory.VotingDaoDBSingleton;
import app.dao.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoDBProvider implements IDaoProvider {

    @Override
    public IGenreDao genreDao() {
        try {
            return GenreDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ISingerDao singerDao() {
        try {
            return SingerDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public IVotingDao votingDao() {
        try {
            return VotingDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public IMailDao mailDao() {
        try {
            return MailDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }
}
