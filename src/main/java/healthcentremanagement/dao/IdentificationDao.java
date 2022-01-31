package healthcentremanagement.dao;

import healthcentremanagement.entities.Identification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class IdentificationDao {
    EntityManager entityManager;

    public IdentificationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Identification> retrieveIdentificationList() {
        entityManager.getTransaction().begin();

        String jpql = "select i from Identification i";
        TypedQuery<Identification> query = entityManager.createQuery(jpql, Identification.class);
        List<Identification> identificationList = query.getResultList();
        entityManager.getTransaction().commit();
        return identificationList;
    }

    public Identification insertOneRecord(Identification identification){
        entityManager.getTransaction().begin();
        entityManager.persist(identification);
        entityManager.getTransaction().commit();
        return identification;
    }

    public int deleteOneRecord(String identification) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Identification i" +
                " where i.number = :identification");
        query.setParameter("identification", identification);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public List<Identification> findByIdentification(String identificationNr) {
        entityManager.getTransaction().begin();
        String jpql = "select i from Identification i  where i.number = :identification";
        TypedQuery<Identification> query = entityManager.createQuery(jpql, Identification.class);
        List<Identification> identifications = query.setParameter("identification" +
                "", identificationNr).getResultList();
        entityManager.getTransaction().commit();
        return identifications;
    }

    public int updateIdentification(Identification identification) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Identification i SET i.sex = :sex" +
                " where i.number = :identification");
        query.setParameter("identification", identification.getNumber());
        query.setParameter("sex", identification.getSex());
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }
}
