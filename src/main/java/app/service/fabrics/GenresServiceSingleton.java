package app.service.fabrics;

import app.dao.provider.ChoiceDaoProvider;
import app.service.GenreService;
import app.service.api.IGenreService;

public class GenresServiceSingleton {
    private volatile static GenreService instance;

    private GenresServiceSingleton() {
    }

    public static IGenreService getInstance() {
        if (instance == null) {
            synchronized (GenresServiceSingleton.class) {
                if (instance == null) {
                    instance = new GenreService(ChoiceDaoProvider.getInstance().genreDao());
                }
            }
        }
        return instance;
    }
}
