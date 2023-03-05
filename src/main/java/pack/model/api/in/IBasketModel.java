package pack.model.api.in;
import pack.model.api.dto.Product;
import pack.model.api.out.IBasketRep;
import pack.model.api.out.IProductsRep;
import pack.repository.product.EProduct;

import java.util.ArrayList;


public interface IBasketModel {
  boolean deleteProduct(int id, String login);
  ArrayList<Product> findProductsBasket(String userLogin);
    void injectRepository(IBasketRep repository);
   boolean addToBasketFromProducts(EProduct p, String login);
}