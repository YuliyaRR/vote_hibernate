package app.dao.db;

import app.dao.api.ISingerDao;
import app.entity.SingerEntity;
import app.orm.api.IManager;

import javax.persistence.EntityManager;
import java.util.List;


public class SingerDaoDB implements ISingerDao {

    private final IManager manager;

    public SingerDaoDB(IManager manager) {
        this.manager = manager;
    }
    @Override
    public List<SingerEntity> getSingerList() {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<SingerEntity> resultList = entityManager.createQuery("from SingerEntity", SingerEntity.class).getResultList();
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
    public boolean isContain(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();

            return singerEntity != null;

        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(SingerEntity singerEntity) {
        int id = singerEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            singerEntity = entityManager.find(SingerEntity.class, id);
            if(singerEntity != null) {
                entityManager.remove(singerEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Delete is not possible. The singer wasn't found in the database");
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
    public void create(SingerEntity singerEntity) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(singerEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(SingerEntity singerEntity) {
        int id = singerEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();

            SingerEntity singerEntityDB = entityManager.find(SingerEntity.class, id);

            if (singerEntityDB != null) {
                entityManager.merge(singerEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Update is not possible. The singer wasn't found in the database");
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
    public SingerEntity get(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            SingerEntity singerEntity = entityManager.find(SingerEntity.class, id);
            entityManager.getTransaction().commit();

            if(singerEntity != null){

                return singerEntity;

            } else {
                throw new NullPointerException("The singer wasn't found in the database");
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
