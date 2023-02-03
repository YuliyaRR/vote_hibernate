package app.dao.api;

import app.entity.GenreEntity;

import java.util.List;

public interface IGenreDao {

    List<GenreEntity> getGenreList();

    boolean isContain(int id);

    void delete(GenreEntity genreEntity);

    void create(GenreEntity genreEntity);

    void update(GenreEntity genreEntity);

    GenreEntity getGenre(int id);
}
