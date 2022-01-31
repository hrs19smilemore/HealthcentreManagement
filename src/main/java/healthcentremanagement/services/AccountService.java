package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.AccountDao;
import healthcentremanagement.entities.Account;

import java.util.List;

public class AccountService {
    private boolean login;
    AccountDao accountDao = new AccountDao(JPAConfiguration.getEntityManager());

    public List<Account> findAccountByUsernameAndPassword(String username, String password){
        return accountDao.findAccountByUsernameAndPassword(username, password);
    }

    public void insertNewAccount(Account account){
        System.out.println("Account inserted: " + accountDao.insertOneRecord(account));
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
