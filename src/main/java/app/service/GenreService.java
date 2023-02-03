package app.service;

import app.dao.api.IGenreDao;
import app.dto.GenreDTO;
import app.entity.GenreEntity;
import app.service.api.IGenreService;

import java.util.ArrayList;
import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean check(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Enter ID for Genre");
        }
        return this.dao.isContain(number);

    }

    @Override
    public List<GenreDTO> get() {
        List<GenreDTO> list = new ArrayList<>();

        List<GenreEntity> genreList = dao.getGenreList();
        for (GenreEntity genreEntity : genreList) {
            list.add(new GenreDTO(genreEntity.getName(), genreEntity.getId()));
        }

        return list;
    }

    @Override
    public void delete(GenreDTO genreDTO) {
        int id = genreDTO.getId();
        if(dao.isContain(id)){
            dao.delete(new GenreEntity(id));
        } else {
            throw new IllegalArgumentException("Genre with this id was not found in the database");
        }
    }

    @Override
    public void create(GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        if(genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre name not specified");
        }

        dao.create(new GenreEntity(genre));

    }

    @Override
    public void update(GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        if(genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre name not specified");
        }

        int id = genreDTO.getId();

        if(dao.isContain(id)){
            dao.update(new GenreEntity(id, genre));
        } else {
            throw new IllegalArgumentException("Genre with this id was not found in the database");
        }

    }
    @Override
    public GenreDTO getGenre(int id) {
        GenreEntity genre = this.dao.getGenre(id);
        return new GenreDTO(genre.getName(), genre.getId());
    }
}
