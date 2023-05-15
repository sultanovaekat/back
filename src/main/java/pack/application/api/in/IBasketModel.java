package pack.application.api.in;
import pack.application.api.dto.Product;
import pack.application.api.out.IBasketRep;
import pack.infrastructure.repository.product.EProduct;

import java.util.ArrayList;


public interface IBasketModel {
  boolean deleteProduct(int id, String userLogin);
  ArrayList<Product> findProductsBasket(String userLogin);
    void injectRepository(IBasketRep repository);
   boolean addToBasketFromProducts(EProduct p, String login);
}