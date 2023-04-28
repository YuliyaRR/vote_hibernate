package app.service.fabrics;


import app.service.StatisticsService;
import app.service.api.IStatisticsService;

public class StatisticServiceSingleton {
    private volatile static StatisticsService instance;

    private StatisticServiceSingleton() {

    }

    public static IStatisticsService getInstance() {
        if (instance == null) {
            synchronized (StatisticServiceSingleton.class) {
                if (instance == null) {
                    instance = new StatisticsService(VoteServiceSingleton.getInstance(),
                            SingersServiceSingleton.getInstance(),
                            GenresServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
