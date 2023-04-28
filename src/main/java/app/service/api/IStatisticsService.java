package app.service.api;

import app.dto.*;

import java.util.List;

public interface IStatisticsService {

    AllStatisticDTO getStatistic();

    List<VoteCounterRaw<SingerDTO>> getSortSinger();

    List<VoteCounterRaw<GenreDTO>> getSortGenre();

    List<AboutUserDTO> getSortAbout();


}
