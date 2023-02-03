package app.dao.api;

import app.entity.SingerEntity;

import java.util.List;

public interface ISingerDao {

    List<SingerEntity> getSingerList();

    boolean isContain(int id);

    void delete(SingerEntity singerEntity);

    void create(SingerEntity singerEntity);

    void update(SingerEntity singerEntity);

    SingerEntity get(int id);
}
