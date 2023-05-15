package pack.infrastructure.repository.user;

import jakarta.annotation.Resource;
import jakarta.persistence.*;
import jakarta.transaction.*;
import jakarta.transaction.RollbackException;
import pack.application.api.out.IUsersRep;

public class UsersRep implements IUsersRep {

    @PersistenceUnit(unitName = "pg_persistence_unit")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    public String selectUser(String login, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            userTransaction.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }
        entityManager.joinTransaction();
        EUser user = entityManager
                .createQuery("SELECT u FROM EUser u WHERE u.login = :login AND u.password = :password", EUser.class)
                .setParameter("login", login).setParameter("password", password).getSingleResult();

        try {
            userTransaction.commit();
        } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user.getRole();
        }
        return "not found";
    }
}
