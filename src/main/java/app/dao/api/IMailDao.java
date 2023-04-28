package app.dao.api;

import app.entity.MailEntity;

public interface IMailDao {
    void save(MailEntity mail);
    void updateStatusMail(int id);
    void updateAttempt(int id);
    MailEntity getVoice(int id);
}
