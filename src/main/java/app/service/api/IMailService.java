package app.service.api;

import app.entity.MailEntity;
import app.entity.VoiceEntity;

public interface IMailService {

    MailEntity save(VoiceEntity voice);
    void send(MailEntity mail);

    void updateStatusMail(int id);

    void updateAttempt(int id);

    MailEntity getVoice(int id);
}
