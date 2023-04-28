package app.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AboutUserDTO {
    private String aboutUser;
    private LocalDateTime creationTime;
    public AboutUserDTO(String aboutUser, LocalDateTime creationTime) {
        this.aboutUser = aboutUser;
        this.creationTime = creationTime;
    }
    public String getAboutUser() {
        return aboutUser;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return "aboutUser = " + aboutUser + ", creationTime = " + dtf.format(creationTime);
    }
}
