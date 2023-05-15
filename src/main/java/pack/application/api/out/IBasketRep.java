package pack.application.api.out;

import java.util.ArrayList;

import pack.application.api.dto.Product;
import pack.infrastructure.repository.product.EProduct;

public interface IBasketRep {
    ArrayList<Product> findProductsBasket(String userLogin);
    boolean deleteProduct(int id, String userLogin);
    boolean addProduct(EProduct p, String login);
}
