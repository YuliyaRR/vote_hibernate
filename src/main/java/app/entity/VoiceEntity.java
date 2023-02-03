package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app.votes")
public class VoiceEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "app.votes_singer",
            joinColumns = @JoinColumn(name = "vote_id"))
    private SingerEntity singer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app.votes_genres",
            joinColumns = @JoinColumn(name = "vote_id"))
    private List<GenreEntity> genres;

    private String message;

    private String email;

    private LocalDateTime time;

    public VoiceEntity() {
    }

     public VoiceEntity(SingerEntity singer, List<GenreEntity> genres, String message, String email, LocalDateTime creationTime) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.email = email;
        this.time = creationTime;
    }

    public VoiceEntity(SingerEntity singer, List<GenreEntity> genres, String message, String email) {
        this.singer = singer;
        this.genres = genres;
        this.message = message;
        this.email = email;
        this.time = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public SingerEntity getSinger() {
        return singer;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getTime() {
        return time;
    }

}
