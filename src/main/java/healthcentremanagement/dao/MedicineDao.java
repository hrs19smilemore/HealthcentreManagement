package healthcentremanagement.dao;

import healthcentremanagement.entities.Doctor;
import healthcentremanagement.entities.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MedicineDao {
    private EntityManager entityManager;

    public MedicineDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Medicine> retrieveMedicineList() {
        entityManager.getTransaction().begin();

        String jpql = "select m from Medicine m";
        TypedQuery<Medicine> query = entityManager.createQuery(jpql, Medicine.class);
        List<Medicine> medicineList = query.getResultList();
        entityManager.getTransaction().commit();
        return medicineList;
    }

    public Medicine insertOneRecord(Medicine medicine){
        entityManager.getTransaction().begin();
        entityManager.persist(medicine);
        entityManager.getTransaction().commit();
        return medicine;
    }

    public int deleteOneRecord(String name, String brand) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Medicine m" +
                " where lower(m.name) = lower(:name) and lower(m.brand) = lower(:brand)");
        query.setParameter("name", name);
        query.setParameter("brand", brand);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public int updateMedicineStock(int medicineAddToStock, String medicineName) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Medicine m SET m.stock = :stock" +
                " where lower(m.name) = lower(:name)");
        query.setParameter("stock", medicineAddToStock);
        query.setParameter("name", medicineName);
        int rowsUpdated = query.executeUpdate();
        System.out.println("medicine Updated: " + rowsUpdated);
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public List<Medicine> findStockByNameAndBrand(String name, String brand) {
        entityManager.getTransaction().begin();
        String jpql = "select m from Medicine m  where lower(m.name) = lower(:name) and lower(m.brand) = lower(:brand)";
        TypedQuery<Medicine> query = entityManager.createQuery(jpql, Medicine.class);
        query.setParameter("name", name);
        query.setParameter("brand", brand);
        List<Medicine> medicines = query.getResultList();
        entityManager.getTransaction().commit();
        return medicines;
    }
}
