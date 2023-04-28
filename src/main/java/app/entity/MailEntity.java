package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "app.mail")
public class MailEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;
    private String text;
    private String email;
    private boolean status;
    private int attempt;
    @Column(name = "max_attempts")
    private int maxAttempts;

    public MailEntity() {
    }

    public MailEntity(String text, String email) {
        this.text = text;
        this.email = email;
        this.status = false;
        this.attempt = 0;
        this.maxAttempts = 5;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getEmail() {
        return email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
