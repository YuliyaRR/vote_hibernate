package app.service;

import app.dao.api.ISingerDao;
import app.dto.SingerDTO;
import app.entity.SingerEntity;
import app.service.api.ISingerService;

import java.util.ArrayList;
import java.util.List;

public class SingerService implements ISingerService {

    private final ISingerDao dao;

    public SingerService(ISingerDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean checkNumber(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Enter ID for Singer");
        }
        return this.dao.isContain(number);

    }

    @Override
    public List<SingerDTO> get() {
        List<SingerEntity> singerList = dao.getSingerList();

        List<SingerDTO>list = new ArrayList<>();

        for (SingerEntity singerEntity : singerList) {
            list.add(new SingerDTO(singerEntity.getName(), singerEntity.getId()));
        }

        return list;
    }

    @Override
    public void delete(SingerDTO singerDTO) {
        int id = singerDTO.getId();
        if(dao.isContain(id)){
            dao.delete(new SingerEntity(id));
        }else {
            throw new IllegalArgumentException("Singer with this id was not found in the database");
        }
    }

    @Override
    public void create(SingerDTO singerDTO) {
        String singer = singerDTO.getName();
        if (singer != null && !singer.isBlank()) {
            dao.create(new SingerEntity(singer));
        } else {
            throw new IllegalArgumentException("Singer name not specified");
        }
    }

    @Override
    public void update(SingerDTO singerDTO) {
        String singer = singerDTO.getName();
        if (singer == null || singer.isBlank()) {
            throw new IllegalArgumentException("Singer name not specified");
        }

        int id = singerDTO.getId();
        if(dao.isContain(id)){
            dao.update(new SingerEntity(id, singer));
        } else {
            throw new IllegalArgumentException("Singer with this id was not found in the database");
        }
    }

    @Override
    public SingerDTO getSinger(int id) {
        SingerEntity singerEntity = this.dao.get(id);
        return new SingerDTO(singerEntity.getName(), singerEntity.getId());
    }
}
