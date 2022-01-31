package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.HealthcentreDao;
import healthcentremanagement.entities.Healthcentre;
import java.util.List;

public class HealthcentreService {
    HealthcentreDao healthcentreDao = new HealthcentreDao(JPAConfiguration.getEntityManager());

    public List<Healthcentre> getHealthcentre() {
        return healthcentreDao.findById(1);
    }

    public List<Healthcentre> findHealthcentreByKkfNumber(String kkfNumber) {
            return healthcentreDao.findByKkfNumber(kkfNumber);
    }

    public void insertHealthcentre(Healthcentre healthcentre){
        System.out.println("Inserted healthcentre: " + healthcentreDao.insertOneRecord(healthcentre));
    }

    public void deleteHealthcentre(String kkfnumber){
        System.out.println("Healthcentre deleted: " + healthcentreDao.deleteOneRecord(kkfnumber));
    }

    public void updateHealthcentreName(String kkfnumber, String name){
        System.out.println("Appointment updated: " + healthcentreDao.updateHealthcentreName(kkfnumber, name));
    }
}
