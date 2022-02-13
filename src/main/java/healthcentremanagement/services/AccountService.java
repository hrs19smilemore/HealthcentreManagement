package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.AccountDao;
import healthcentremanagement.entities.Account;

import javax.persistence.PersistenceException;
import java.util.List;

public class AccountService {
    private boolean login;
    AccountDao accountDao = new AccountDao(JPAConfiguration.getEntityManager());

    public List<Account> findAccountByUsernameAndPassword(String username, String password){
        return accountDao.findAccountByUsernameAndPassword(username, password);
    }

    public void insertNewAccount(Account account){
        try {
            System.out.println("Account inserted: " + accountDao.insertOneRecord(account));
        } catch(PersistenceException persistenceException){
            System.out.println("Invalid: Username already exists");
            System.exit(0);
        }
    }

    public List<Account> findAccountByUsernameAndType(String username, String type){
        return accountDao.findAccountByUsernameAndType(username, type);
    }

    public boolean checkIfLoggedIn(boolean login){
        if (login){
            return false;
        } else {
            return true;
        }
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
