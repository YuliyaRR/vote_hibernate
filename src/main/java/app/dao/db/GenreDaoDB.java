package app.dao.db;

import app.dao.api.IGenreDao;
import app.entity.GenreEntity;
import app.dao.db.orm.api.IManager;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreDaoDB implements IGenreDao {

    private final IManager manager;

    public GenreDaoDB(IManager manager) {
        this.manager = manager;//агрегация
    }

    @Override
    public List<GenreEntity> getGenreList() {
        EntityManager entityManager =  null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            List<GenreEntity> resultList = entityManager.createQuery("from GenreEntity", GenreEntity.class).getResultList();
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
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();

            return genreEntity != null;

        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(GenreEntity genreEntity) {
        int id = genreEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            genreEntity = entityManager.find(GenreEntity.class, id);

            if(genreEntity != null) {
                entityManager.remove(genreEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Delete is not possible. The genre wasn't found in the database");
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
    public void create(GenreEntity genreEntity) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(genreEntity);
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
    public void update(GenreEntity genreEntity) {
        int id = genreEntity.getId();
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genreEntityDB = entityManager.find(GenreEntity.class, id);

            if (genreEntityDB != null) {
                entityManager.merge(genreEntity);
                entityManager.getTransaction().commit();
            } else {
                entityManager.getTransaction().rollback();
                throw new NullPointerException("Update is not possible. The genre wasn't found in the database");
            }
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public GenreEntity getGenre(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            GenreEntity genreEntity = entityManager.find(GenreEntity.class, id);
            entityManager.getTransaction().commit();

            if (genreEntity != null) {

                return genreEntity;

            } else {
                throw new NullPointerException("The genre wasn't found in the database");
            }
        } catch (Exception e) {
            throw new RuntimeException("Database connection error", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
