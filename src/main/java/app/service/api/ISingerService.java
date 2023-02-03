package app.service.api;

import app.dto.SingerDTO;

import java.util.List;

public interface ISingerService {

    boolean checkNumber(int number);

    List<SingerDTO> get();

    void delete(SingerDTO singerDTO);

    void create(SingerDTO singerDTO);

    void update(SingerDTO singerDTO);

    SingerDTO getSinger(int id);

}
