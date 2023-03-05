package pack.model.api.out;

import java.util.ArrayList;

import pack.model.api.dto.Product;
import pack.repository.product.EProduct;

public interface IProductsRep {
    boolean addProduct(Product product);
    ArrayList<Product> findAllProducts();
    EProduct findProductByID(int id);
    boolean deleteProduct(int id);
}
