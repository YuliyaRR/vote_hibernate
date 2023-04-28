package app.dao.db;

import app.dao.api.IMailDao;
import app.entity.MailEntity;
import app.dao.db.orm.api.IManager;

import javax.persistence.EntityManager;

public class MailDaoDB implements IMailDao {

    private final IManager manager;

    public MailDaoDB(IManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(MailEntity mail) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(mail);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void updateStatusMail(int id){
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            MailEntity mailEntity = entityManager.find(MailEntity.class, id);
            if (mailEntity != null) {
                mailEntity.setStatus(true);
                entityManager.merge(mailEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void updateAttempt(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            MailEntity mailEntity = entityManager.find(MailEntity.class, id);
            if (mailEntity != null) {
                int attempt = mailEntity.getAttempt();
                mailEntity.setAttempt(++attempt);
                entityManager.merge(mailEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public MailEntity getVoice(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            MailEntity mailEntity = entityManager.find(MailEntity.class, id);
            if (mailEntity != null) {
                return mailEntity;
            } else {
                entityManager.getTransaction().rollback();
                throw new RuntimeException("Not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

}
