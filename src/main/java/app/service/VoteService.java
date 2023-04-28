package app.service;

import app.dao.api.IVotingDao;
import app.dto.SavedVoiceDTO;
import app.entity.GenreEntity;
import app.entity.VoiceEntity;
import app.dto.VoiceDTO;
import app.entity.SingerEntity;
import app.service.api.IGenreService;
import app.service.api.IMailService;
import app.service.api.ISingerService;
import app.service.api.IVotesService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoteService implements IVotesService {
    private final IVotingDao votingDao;

    private final ISingerService singerService;

    private final IGenreService genreService;

    private final IMailService mailService;

    public VoteService(IVotingDao voiceDao, ISingerService singerService,
                       IGenreService genreService, IMailService mailService) {
        this.votingDao = voiceDao;
        this.singerService = singerService;
        this.genreService = genreService;
        this.mailService = mailService;
    }

    @Override
    public void save(VoiceDTO voice) {
        check(voice);

        int singer = voice.getSinger();
        int[] genres = voice.getGenre();
        String message = voice.getMessage();
        String email = voice.getEmail();

        SingerEntity singerEntity = new SingerEntity(singer, singerService.getSinger(singer).getName());

        List<GenreEntity>listGenre = new ArrayList<>();
        for (int genre : genres) {
            listGenre.add(new GenreEntity(genre, genreService.getGenre(genre).getName()));
        }

        VoiceEntity savedVoice = new VoiceEntity(singerEntity, listGenre, message, email);

        votingDao.save(savedVoice);

        mailService.send(mailService.save(savedVoice));

    }

    @Override
    public List<SavedVoiceDTO> get() {
        List<SavedVoiceDTO> result = new ArrayList<>();
        List<VoiceEntity> voiceList = votingDao.getVoiceList();
        SavedVoiceDTO.SavedVoteBuilder savedVoteBuilder = SavedVoiceDTO.SavedVoteBuilder.create();

        for (VoiceEntity voiceEntity : voiceList) {
            SingerEntity singer = voiceEntity.getSinger();
            savedVoteBuilder.setSinger(singer.getId());

            List<GenreEntity> genres = voiceEntity.getGenres();
            for (GenreEntity genre : genres) {
                savedVoteBuilder.addGenre(genre.getId());
            }

            SavedVoiceDTO savedVoiceDTO = savedVoteBuilder
                    .setMessage(voiceEntity.getMessage())
                    .setEmail(voiceEntity.getEmail())
                    .setDtCreate(voiceEntity.getTime())
                    .build();

            result.add(savedVoiceDTO);
        }

        return result;
    }

    private void check(VoiceDTO voice) {
        int singer = voice.getSinger();
        if (!singerService.checkNumber(singer)) {
            throw new IllegalArgumentException("Singer №" + singer + " is not on the list");
        }

        int[] genres = voice.getGenre();

        Set<Integer> setGenre = new HashSet<>();

        for (int val : genres) {
            setGenre.add(val);
        }

        if (setGenre.size() < 3 || setGenre.size() > 5) {
            throw new IllegalArgumentException("Wrong number of genres, need from 3 to 5");
        }

        if (setGenre.size() != genres.length) {
            throw new IllegalArgumentException("Received genres contain duplicates");
        }

        for (Integer genre : setGenre) {
            if (!genreService.check(genre)) {
                throw new IllegalArgumentException("Genre №" + genre + " is not on the list");
            }
        }

        String aboutMe = voice.getMessage();
        if (aboutMe == null || aboutMe.isBlank()) {
            throw new IllegalArgumentException("You need to enter information about yourself");
        }

        String email = voice.getEmail();
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("You need to enter email");
        }
        String regEx = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Invalid: check the correctness of the entered e-mail");
        }
    }

}
