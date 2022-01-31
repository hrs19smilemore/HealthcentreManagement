package healthcentremanagement.dao;

import healthcentremanagement.entities.Healthcentre;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class HealthcentreDao {
    private EntityManager entityManager;

    public HealthcentreDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Healthcentre> retrieveHealthcentreList() {
        entityManager.getTransaction().begin();
        String jpql = "select h from Healthcentre h";
        TypedQuery<Healthcentre> query = entityManager.createQuery(jpql, Healthcentre.class);
        List<Healthcentre> healthcentreList = query.getResultList();
        entityManager.getTransaction().commit();
        return healthcentreList;
    }

    public List<Healthcentre> findById(int id){
        entityManager.getTransaction().begin();
        String jpql = "select h from Healthcentre h  where h.id = :id";
        TypedQuery<Healthcentre> query = entityManager.createQuery(jpql, Healthcentre.class);
        List<Healthcentre> healthcentres = query.setParameter("id", id).getResultList();
        entityManager.getTransaction().commit();
        return healthcentres;
    }

    public Healthcentre insertOneRecord(Healthcentre healthcentre){
        entityManager.getTransaction().begin();
        entityManager.persist(healthcentre);
        entityManager.getTransaction().commit();
        return  healthcentre;
    }

    public int deleteOneRecord(String kkfNumber) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Healthcentre h where h.kkfNumber = :kkfNumber");
        query.setParameter("kkfNumber", kkfNumber);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public List<Healthcentre> findByKkfNumber(String kkfNumber) {
        entityManager.getTransaction().begin();
        String jpql = "select h from Healthcentre h  where h.kkfNumber = :kkfNumber";
        TypedQuery<Healthcentre> query = entityManager.createQuery(jpql, Healthcentre.class);
        List<Healthcentre> healthcentres = query.setParameter("kkfNumber" +
                "", kkfNumber).getResultList();
        entityManager.getTransaction().commit();
        return healthcentres;
    }

    public int updateHealthcentreName(String kkfnumber, String name) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Healthcentre h SET h.name = :name where h.kkfNumber = :kkfNumber");
        query.setParameter("kkfNumber", kkfnumber);
        query.setParameter("name", name);
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }
}
