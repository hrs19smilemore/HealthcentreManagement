package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.IdentificationDao;
import healthcentremanagement.entities.Identification;

import javax.persistence.PersistenceException;
import java.util.List;

public class IdentificationService {
    IdentificationDao identificationDao = new IdentificationDao(JPAConfiguration.getEntityManager());

    public void insertIdentification(Identification identification){
        try {
            identificationDao.insertOneRecord(identification);
        } catch(PersistenceException persistenceException){
            System.out.println("Invalid: Identification already exists!");
            System.exit(0);
        }
    }

    public List<Identification> findIdentification(String idNumber){
        return identificationDao.findByIdentification(idNumber);
    }
}
