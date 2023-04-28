package app.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SavedVoiceDTO {
    private Integer singer;
    private List<Integer>genres;
    private String message;
    private String email;
    private LocalDateTime dtCreate;

    public SavedVoiceDTO(int singer, List<Integer> genres, String message, String email, LocalDateTime dtCreate) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.email = email;
        this.dtCreate = dtCreate;
    }

    public Integer getSinger() {
        return singer;
    }

    public List<Integer> getGenres() {
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
        private Integer singer;
        private List<Integer>genres = new ArrayList<>();
        private String message;
        private String email;
        private LocalDateTime dtCreate;

        private SavedVoteBuilder() {
        }

        public static SavedVoteBuilder create(){
            return new SavedVoteBuilder();
        }

        public SavedVoteBuilder setSinger(Integer singer) {
            this.singer = singer;
            return this;
        }

        public SavedVoteBuilder setGenres(List<Integer> genres) {
            this.genres = genres;
            return this;
        }

        public SavedVoteBuilder addGenre(Integer genre){
            this.genres.add(genre);
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
            List<Integer>copyGenres = List.copyOf(this.genres);
            clearListGenres();
            return new SavedVoiceDTO(singer, copyGenres, message, email, dtCreate);
        }
    }


}
