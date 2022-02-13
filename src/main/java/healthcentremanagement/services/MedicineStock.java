package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.MedicineDao;
import healthcentremanagement.entities.Medicine;

import java.util.List;

public class MedicineStock {
    MedicineDao medicineDao = new MedicineDao(JPAConfiguration.getEntityManager());
    private int medicineInStock;

    public int getMedicineInStock() {
        return medicineInStock;
    }

    public void decreaseMedicineInStock(int medicineWithdrawn) {
        medicineInStock -= medicineWithdrawn;
    }

    public void increaseMedicineInStock(int medicineDeposited) {
        medicineInStock += medicineDeposited;
    }

    public void haveEnoughMedicine(int quantityToWithdraw, Medicine medicine) {
        medicineInStock = medicine.getStock();
        if (quantityToWithdraw > getMedicineInStock()) {
            System.out.println("Error: Not enough stock for that medicine");
            System.out.println("Current Stock: " + getMedicineInStock());
        } else {
            decreaseMedicineInStock(quantityToWithdraw);
            medicineDao.updateMedicineStock(medicineInStock, medicine.getName());
            System.out.println("Withdrawal complete: Current balance is: " + getMedicineInStock());
        }
    }

    public void makeDeposit(int quantityToDeposit, Medicine medicine) {
            medicineInStock = medicine.getStock();
            increaseMedicineInStock(quantityToDeposit);
            medicineDao.updateMedicineStock(medicineInStock, medicine.getName());
            System.out.println("Deposit complete: Current balance is " + getMedicineInStock());
    }

        public void insertNewMedicine (Medicine medicine){
            System.out.println("Medicine inserted; " + medicineDao.insertOneRecord(medicine));
        }

        public void deleteMedicine (String name, String brand){
            System.out.println("Medicine deleted: " + medicineDao.deleteOneRecord(name, brand));
        }

        public List<Medicine> findStockByNameAndBrand(String name, String brand){
            return medicineDao.findStockByNameAndBrand(name, brand);
        }
}


