package app.service.fabrics;

import app.dao.provider.ChoiceDaoProvider;
import app.service.SingerService;
import app.service.api.ISingerService;

public class SingersServiceSingleton {
    private volatile static SingerService instance;

    private SingersServiceSingleton() {
    }

    public static ISingerService getInstance() {
        if (instance == null) {
            synchronized (SingersServiceSingleton.class) {
                if (instance == null) {
                    instance = new SingerService(ChoiceDaoProvider.getInstance().singerDao());
                }
            }
        }
        return instance;
    }
}
