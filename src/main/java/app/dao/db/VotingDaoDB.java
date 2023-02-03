package app.dao.db;

import app.dao.api.IVotingDao;
import app.entity.VoiceEntity;
import app.orm.api.IManager;

import javax.persistence.EntityManager;
import java.util.List;

public class VotingDaoDB implements IVotingDao {

    private final IManager manager;

    public VotingDaoDB(IManager manager) {
        this.manager = manager;
    }


    @Override
    public List<VoiceEntity> getVoiceList() {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<VoiceEntity> resultList = entityManager.createQuery("from VoiceEntity", VoiceEntity.class).getResultList();
            entityManager.getTransaction().commit();

            return resultList;

        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void save(VoiceEntity voice) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(voice);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }


    }
}
