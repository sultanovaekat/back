package pack.repository.product;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.*;
import jakarta.transaction.*;
import jakarta.transaction.RollbackException;

import pack.model.api.dto.Product;
import pack.model.api.out.IProductsRep;

public class ProductsRep  implements IProductsRep{
    @PersistenceUnit(unitName = "pg_persistence_unit")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;

   public EProduct findProductByID(int id) {
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       try {
           userTransaction.begin();
       } catch (NotSupportedException e) {
           e.printStackTrace();
       } catch (SystemException e) {
           e.printStackTrace();
       }
       entityManager.joinTransaction();
       List<EProduct> productFind = entityManager.createQuery("SELECT p FROM EProduct p WHERE p.id ='" + id + "'", EProduct.class).getResultList();
       try {
           userTransaction.commit();
       } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                | HeuristicRollbackException | SystemException e) {
           e.printStackTrace();
       }
           return productFind.get(0);
   }

    @Override
    public boolean addProduct(Product product)  {
       try{ EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            userTransaction.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }
        entityManager.joinTransaction();
        EProduct eProduct = new EProduct();
        eProduct.setId(product.getId());
        eProduct.setName(product.getName());
        eProduct.setParametrs(product.getParametrs());
        eProduct.setTotal(product.getTotal());

        entityManager.persist(eProduct);
        try {
            userTransaction.commit();
        } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                 | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    }

    public ArrayList<Product> findAllProducts(){
    EntityManager entityManager= entityManagerFactory.createEntityManager();
    try {
        userTransaction.begin();
    } catch (NotSupportedException | SystemException e) {
        e.printStackTrace();
    }
    entityManager.joinTransaction();
    List<EProduct> EProducts = entityManager.createQuery("SELECT p FROM EProduct p",EProduct.class).getResultList();
    try {
        userTransaction.commit();
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
            | HeuristicRollbackException | SystemException e) {
        e.printStackTrace();
    }
    ArrayList<Product> products =  new ArrayList<>();
    for (EProduct Eptoduct: EProducts){
        Product p = new Product();        p.setId(Eptoduct.getId());
        p.setName(Eptoduct.getName());
        p.setParametrs(Eptoduct.getParametrs());
        p.setTotal(Eptoduct.getTotal());
        products.add(p);
    }
    return products;
  }
    public boolean deleteProduct(int id) {
       try {

        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            userTransaction.begin();
        } catch (NotSupportedException | SystemException e) {
            e.printStackTrace();
        }
        entityManager.joinTransaction();
        entityManager.createQuery("DELETE FROM EProduct p WHERE p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        try {
            userTransaction.commit();
        } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                 | HeuristicRollbackException | SystemException e) {
            e.printStackTrace();
        }return true;}catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }
}


