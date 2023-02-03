package app.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SavedVoiceDTO {
    private SingerDTO singer;
    private List<GenreDTO> genres;
    private String message;
    private String email;
    private LocalDateTime dtCreate;

    public SavedVoiceDTO(SingerDTO singer, List<GenreDTO> genres, String message, String email, LocalDateTime dtCreate) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.email = email;
        this.dtCreate = dtCreate;
    }

    public SingerDTO getSinger() {
        return singer;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setSinger(SingerDTO singer) {
        this.singer = singer;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Override
    public String toString() {
        return "SavedVoiceDTO{" +
                "singer=" + singer +
                ", genres=" + genres +
                ", message='" + message + '\'' +
                ", email='" + email + '\'' +
                ", dtCreate=" + dtCreate +
                '}';
    }

    public static class SavedVoteBuilder {
        private SingerDTO singer;
        private List<GenreDTO> genres = new ArrayList<>();
        private String message;
        private String email;
        private LocalDateTime dtCreate;

        private SavedVoteBuilder() {
        }

        public static SavedVoteBuilder create(){
            return new SavedVoteBuilder();
        }

        public SavedVoteBuilder setSinger(SingerDTO singer) {
            this.singer = singer;
            return this;
        }

        public SavedVoteBuilder setGenres(List<GenreDTO> genres) {
            this.genres = genres;
            return this;
        }

       public SavedVoteBuilder addGenre(GenreDTO genreDTO){
            this.genres.add(genreDTO);
            return this;
        }

        private void clearListGenres(){
            this.genres.clear();
        }

        public SavedVoteBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public SavedVoteBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public SavedVoteBuilder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public SavedVoiceDTO build(){
            List<GenreDTO>copyGenres = List.copyOf(this.genres);
            clearListGenres();
            return new SavedVoiceDTO(singer, copyGenres, message, email, dtCreate);
        }
    }


}
