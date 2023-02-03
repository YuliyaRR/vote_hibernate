package app.service.fabrics;

import app.dao.provider.ChoiceDaoProvider;
import app.service.VoteService;
import app.service.api.IVotesService;


public class VoteServiceSingleton {
    private volatile static VoteService instance;

    public static IVotesService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(ChoiceDaoProvider.getInstance().votingDao(),
                            SingersServiceSingleton.getInstance(),
                            GenresServiceSingleton.getInstance(),
                            SendMailServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
