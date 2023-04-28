package app.dto;

import java.util.List;
public class AllStatisticDTO {
    private final List<VoteCounterRaw<SingerDTO>> singers;
    private final List<VoteCounterRaw<GenreDTO>> genres;
    private final List<AboutUserDTO> aboutUsers;

    public AllStatisticDTO(List<VoteCounterRaw<SingerDTO>> singers,
                           List<VoteCounterRaw<GenreDTO>> genres,
                           List<AboutUserDTO> aboutUsers) {
        this.singers = singers;
        this.genres = genres;
        this.aboutUsers = aboutUsers;
    }

    public List<VoteCounterRaw<SingerDTO>> getSingers() {
        return singers;
    }

    public List<VoteCounterRaw<GenreDTO>> getGenres() {
        return genres;
    }

    public List<AboutUserDTO> getAboutUsers() {
        return aboutUsers;
    }
}
