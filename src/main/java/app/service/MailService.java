package app.service;

import app.dao.api.IMailDao;
import app.entity.GenreEntity;
import app.entity.MailEntity;
import app.entity.SingerEntity;
import app.entity.VoiceEntity;
import app.service.api.IMailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MailService implements IMailService {
    private final String WORK_EMAIL_PROPERTY_NAME  = "mail.box";
    private final String PASSWORD_WORK_EMAIL_PROPERTY_NAME = "mail.password";
    private Properties properties;
    private final IMailDao mailDao;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    public MailService(Properties properties, IMailDao mailDao) {
        this.properties = properties;
        this.mailDao = mailDao;
    }

    public void send(MailEntity mail) {

        String email = mail.getEmail();
        Integer id = mail.getId();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty(WORK_EMAIL_PROPERTY_NAME),
                        properties.getProperty(PASSWORD_WORK_EMAIL_PROPERTY_NAME));
            }
        });

        while (!this.getVoice(id).getStatus()
                && this.getVoice(id).getAttempt() <= this.getVoice(id).getMaxAttempts()) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(session.getProperty(WORK_EMAIL_PROPERTY_NAME)));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("Your voice");
                message.setText(mail.getText());
                Transport.send(message);

                this.updateStatusMail(id);

            } catch (MessagingException e) {
                 throw new RuntimeException("ошибка отправки почты", e);
            } finally {
                this.updateAttempt(id);
            }
        }
    }

    @Override
    public MailEntity save(VoiceEntity voice) {
        String info = createInfoVoice(voice);
        String email = voice.getEmail();
        MailEntity mail = new MailEntity(info, email);
        this.mailDao.save(mail);
        return  mail;
    }
    @Override
    public void updateStatusMail(int id){
        this.mailDao.updateStatusMail(id);
    }

    @Override
    public void updateAttempt(int id) {
        this.mailDao.updateAttempt(id);
    }

    @Override
    public MailEntity getVoice(int id) {
        return this.mailDao.getVoice(id);
    }


    private String createInfoVoice(VoiceEntity voiceEntity){
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        SingerEntity singer = voiceEntity.getSinger();
        List<GenreEntity> genres = voiceEntity.getGenres();
        String message = voiceEntity.getMessage();
        LocalDateTime creationTime = voiceEntity.getTime();

        builder.append("Ваш голос: исполнитель -> ").append(singer)
                .append(", жанры -> ");

        for (GenreEntity genre: genres) {
            builder.append(genre).append(", ");
        }

        builder.append("информация о себе -> ").append(message)
                .append(", дата и время голосования -> ").append(dtf.format(creationTime));


        return builder.toString();
    }
}
