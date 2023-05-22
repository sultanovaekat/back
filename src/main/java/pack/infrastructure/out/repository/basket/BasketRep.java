package pack.infrastructure.out.repository.basket;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.*;
import jakarta.transaction.*;
import jakarta.transaction.RollbackException;
import pack.application.dto.Product;
import pack.application.api.out.IBasketRep;
import pack.infrastructure.out.repository.product.EProduct;

public class BasketRep  implements IBasketRep {

    @PersistenceUnit(unitName = "pg_persistence_unit")
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private UserTransaction userTransaction;

    public boolean addProduct(EProduct p, String login) {
        if(p!=null){
        try{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            userTransaction.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }
        entityManager.joinTransaction();
        EBasket eBasket = new EBasket();
        eBasket.setId(p.getId());
        eBasket.setName(p.getName());
        eBasket.setParametrs(p.getParametrs());
        eBasket.setTotal(p.getTotal());
        eBasket.setUserLogin(login);

        entityManager.persist(eBasket);
        try {
            userTransaction.commit();
        } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                 | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
        return true;
    } catch(
    Exception e)

    {
        e.printStackTrace();
        return false;
    }}
else return false;
    }

  public boolean deleteProduct(int id, String userLogin) {
        try {
    EntityManager entityManager= entityManagerFactory.createEntityManager();
    try {
        userTransaction.begin();
    } catch (NotSupportedException | SystemException e) {
        e.printStackTrace();
    }
    entityManager.joinTransaction();
      entityManager.createQuery("DELETE FROM EBasket o WHERE o.id = :id AND o.userLogin = :userLogin")
              .setParameter("id", id).setParameter("userLogin", userLogin)
              .executeUpdate();
      try {
        userTransaction.commit();
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
            | HeuristicRollbackException | SystemException e) {
        e.printStackTrace();
    }return true;}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
  }
  public ArrayList<Product> findProductsBasket(String  userLogin){
    EntityManager entityManager= entityManagerFactory.createEntityManager();
    try {
        userTransaction.begin();
    } catch (NotSupportedException | SystemException e) {
        e.printStackTrace();
    }
    entityManager.joinTransaction();
    List<EBasket> EProducts = entityManager
            .createQuery("SELECT b FROM EBasket b WHERE b.userLogin = :userLogin", EBasket.class)
            .setParameter("userLogin", userLogin).getResultList();
    try {
        userTransaction.commit();
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
            | HeuristicRollbackException | SystemException e) {
        e.printStackTrace();
    }
    ArrayList<Product> products =  new ArrayList<>();
    for (EBasket Eptoduct: EProducts){
        Product p = new Product();
        p.setId(Eptoduct.getId());
        p.setName(Eptoduct.getName());
        p.setParametrs(Eptoduct.getParametrs());
        p.setTotal(Eptoduct.getTotal());
        products.add(p);
    }
    return products;
  }


}
