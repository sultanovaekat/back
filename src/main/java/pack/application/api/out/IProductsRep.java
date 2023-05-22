package pack.application.api.out;

import java.util.ArrayList;

import pack.application.dto.Product;
import pack.infrastructure.out.repository.product.EProduct;

public interface IProductsRep {
    boolean addProduct(Product product);
    ArrayList<Product> findAllProducts();
    EProduct findProductByID(int id);
    boolean deleteProduct(int id);
    boolean checkProductByID(int id);
}
