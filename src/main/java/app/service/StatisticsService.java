package app.service;

import app.dto.*;
import app.service.api.IGenreService;
import app.service.api.ISingerService;
import app.service.api.IStatisticsService;
import app.service.api.IVotesService;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService implements IStatisticsService {
    private final IVotesService iVotesService;
    private final ISingerService iSingerService;
    private final IGenreService iGenreService;

    public StatisticsService(IVotesService iVotesService,
                             ISingerService iSingerService,
                             IGenreService iGenreService) {
        this.iVotesService = iVotesService;
        this.iSingerService = iSingerService;
        this.iGenreService = iGenreService;
    }

    public List<VoteCounterRaw<SingerDTO>> getSortSinger(){
        List<VoteCounterRaw<SingerDTO>> result = iSingerService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        for (SavedVoiceDTO savedVoiceDTO : iVotesService.get()) {
            Integer singerID = savedVoiceDTO.getSinger();
            for (VoteCounterRaw<SingerDTO> singer : result) {
                if(singer.getItem().getId().equals(singerID)){
                    singer.addVoice();
                    break;
                }
            }
        }

        result.sort((o1, o2)-> o2.getCountVoice() - o1.getCountVoice());

        return result;
    }

    public List<VoteCounterRaw<GenreDTO>> getSortGenre(){
        List<VoteCounterRaw<GenreDTO>> result = iGenreService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        for (SavedVoiceDTO savedVoiceDTO : iVotesService.get()) {
            List<Integer> genres = savedVoiceDTO.getGenres();

            for (VoteCounterRaw<GenreDTO> resultGenre : result) {
                for (Integer genre : genres) {
                    if (resultGenre.getItem().getId().equals(genre)){
                        resultGenre.addVoice();
                        break;
                    }
                }
            }
        }

        result.sort((o1, o2) -> o2.getCountVoice() - o1.getCountVoice());

        return result;
    }

    public List<AboutUserDTO> getSortAbout() {
        return iVotesService.get().stream()
                .map(o -> new AboutUserDTO(o.getMessage(), o.getDtCreate()))
                .sorted((o1, o2) -> o2.getCreationTime().compareTo(o1.getCreationTime()))
                .collect(Collectors.toList());
    }

    public AllStatisticDTO getStatistic() {
        List<SavedVoiceDTO> savedVoice = iVotesService.get();

        List<VoteCounterRaw<SingerDTO>> singerList = iSingerService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        List<VoteCounterRaw<GenreDTO>> genreList = iGenreService.get().stream()
                .map(VoteCounterRaw::new)
                .collect(Collectors.toList());

        List<AboutUserDTO> aboutUserList = new ArrayList<>();

        for (SavedVoiceDTO savedVoiceDTO : savedVoice) {
            Integer singerID = savedVoiceDTO.getSinger();
            List<Integer> genresID = savedVoiceDTO.getGenres();

            for (VoteCounterRaw<SingerDTO> singer : singerList) {
                if(singer.getItem().getId().equals(singerID)){
                    singer.addVoice();
                    break;
                }
            }

            for (VoteCounterRaw<GenreDTO> genre : genreList) {
                for (Integer genreID : genresID) {
                    if(genre.getItem().getId().equals(genreID)){
                        genre.addVoice();
                        break;
                    }
                }
            }

            aboutUserList.add(new AboutUserDTO(savedVoiceDTO.getMessage(), savedVoiceDTO.getDtCreate()));

        }

        singerList.sort(((o1, o2) -> o2.getCountVoice() - o1.getCountVoice()));
        genreList.sort(((o1, o2) -> o2.getCountVoice() - o1.getCountVoice()));
        aboutUserList.sort(((o1, o2) -> o2.getCreationTime().compareTo(o1.getCreationTime())));

        return new AllStatisticDTO(singerList, genreList, aboutUserList);
    }
}
