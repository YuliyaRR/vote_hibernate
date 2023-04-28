package app.dao.provider.api;

import app.dao.api.IGenreDao;
import app.dao.api.IMailDao;
import app.dao.api.ISingerDao;
import app.dao.api.IVotingDao;

public interface IDaoProvider {

    IGenreDao genreDao();

    ISingerDao singerDao();

    IVotingDao votingDao();

    IMailDao mailDao();
}
