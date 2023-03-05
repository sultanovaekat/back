package pack.model.api.out;

import java.util.ArrayList;

import pack.model.api.dto.Product;
import pack.repository.product.EProduct;

public interface IBasketRep {
    ArrayList<Product> findProductsBasket(String userLogin);
    boolean deleteProduct(int id, String login);
    boolean addProduct(EProduct p, String login);
}
