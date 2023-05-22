package pack.application.api.in;
import pack.application.dto.Product;
import pack.application.api.out.IProductsRep;
import pack.infrastructure.out.repository.product.EProduct;

import java.util.ArrayList;

public interface IProduct {
    void injectRepository(IProductsRep repository);
    ArrayList<Product> findAllProducts();
    boolean addProduct(Product product);
    EProduct findProductByID(int id);
    boolean checkProductByID(int id);
    boolean deleteProduct(int id);
}
