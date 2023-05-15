package pack.infrastructure.repository.order;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import jakarta.transaction.*;
import jakarta.transaction.RollbackException;
import pack.application.api.dto.Delivery;
import pack.application.api.out.IOrderRep;

public class OrderRep implements IOrderRep {
    @PersistenceUnit(unitName = "pg_persistence_unit")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;

  public ArrayList<Delivery> findOrder(String  user){
    EntityManager entityManager= entityManagerFactory.createEntityManager();
    try {
        userTransaction.begin();
    } catch (NotSupportedException | SystemException e) {
        e.printStackTrace();
    }
    entityManager.joinTransaction();
    List<EOrder> EOrders = entityManager
            .createQuery("SELECT o FROM EOrder o WHERE o.user = :user", EOrder.class)
            .setParameter("user", user).getResultList();
    try {
        userTransaction.commit();
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
            | HeuristicRollbackException | SystemException e) {
        e.printStackTrace();
    }
    ArrayList<Delivery> orders =  new ArrayList<>();
    for (EOrder Eorder: EOrders){
        Delivery p = new Delivery();
        p.setId(Eorder.getId());
        p.setName(Eorder.getName());
        p.setUser(Eorder.getUser());
        p.setStatus(Eorder.getStatus());
        orders.add(p);
    }
    return orders;
  }
    public void updateStatus(String login) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            userTransaction.begin();
            entityManager.joinTransaction();
            EOrder EOrder = entityManager.find(pack.infrastructure.repository.order.EOrder.class, login);
            if(EOrder.getStatus()==false){EOrder.setStatus(true);}
            else  EOrder.setStatus(false);
            userTransaction.commit();
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public ArrayList<Delivery> findAllOrders() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            userTransaction.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }
        entityManager.joinTransaction();
        List<EOrder> EDeliveries = entityManager.createQuery("SELECT o FROM EOrder o", EOrder.class).getResultList();
        try {
            userTransaction.commit();
        } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                 | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
        ArrayList<Delivery> orders = new ArrayList<>();
        for (EOrder Eorder : EDeliveries) {
            Delivery p = new Delivery();
            p.setId(Eorder.getId());
            p.setName(Eorder.getName());
            p.setUser(Eorder.getUser());
            p.setStatus(Eorder.getStatus());
            orders.add(p);}
            return orders;
        }


    }

