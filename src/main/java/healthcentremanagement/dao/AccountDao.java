package healthcentremanagement.dao;

import healthcentremanagement.entities.Account;
import healthcentremanagement.entities.Doctor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDao {
    private EntityManager entityManager;

    public AccountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Account> retrieveAccountList() {
        entityManager.getTransaction().begin();

        String jpql = "select a from Account a";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        List<Account> accountList = query.getResultList();
        entityManager.getTransaction().commit();
        return accountList;
    }

    public Account insertOneRecord(Account account){
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        return account;
    }

    public List<Account> findAccountByUsernameAndPassword(String username, String password) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Account a  where a.username =:username and a.password = :password";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Account> accounts = query.getResultList();
        entityManager.getTransaction().commit();
        return accounts;
    }

    public List<Account> findAccountByUsernameAndType(String username,String type) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Account a  where a.username= :username and a.accType = :type";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        query.setParameter("type", type);
        query.setParameter("username", username);
        List<Account> accounts = query.getResultList();
        entityManager.getTransaction().commit();
        return accounts;
    }
}
