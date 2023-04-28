package app.dao.provider;

import app.dao.api.IGenreDao;
import app.dao.api.IMailDao;
import app.dao.api.ISingerDao;
import app.dao.api.IVotingDao;
import app.dao.provider.api.IDaoProvider;

public class ChoiceDaoProvider implements IDaoProvider {
    private static volatile ChoiceDaoProvider instance;

    private IDaoProvider daoProvider;

    private ChoiceDaoProvider() {
        daoProvider = new DaoDBProvider();
    }

    @Override
    public IGenreDao genreDao() {
        return daoProvider.genreDao();
    }

    @Override
    public ISingerDao singerDao() {
        return daoProvider.singerDao();
    }

    @Override
    public IVotingDao votingDao() {
        return daoProvider.votingDao();
    }

    @Override
    public IMailDao mailDao() {
        return daoProvider.mailDao();
    }

    public static IDaoProvider getInstance() {
        if(instance == null){
            synchronized (ChoiceDaoProvider.class){
                if(instance == null){
                    instance = new ChoiceDaoProvider();
                }
            }
        }
        return instance;
    }
}
