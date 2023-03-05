package pack.model.api.in;
import pack.model.api.dto.Product;
import pack.model.api.out.IProductsRep;
import pack.repository.product.EProduct;

import java.util.ArrayList;

public interface IProductModel {
    void injectRepository(IProductsRep repository);
    ArrayList<Product> findAllProducts();
    boolean addProduct(Product product);
    EProduct findProductByID(int id);

    boolean deleteProduct(int id);
}
