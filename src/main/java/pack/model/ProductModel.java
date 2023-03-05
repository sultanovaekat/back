package pack.model;

import java.util.ArrayList;


import pack.model.api.dto.Product;
import pack.model.api.in.IProductModel;
import pack.model.api.out.IProductsRep;
import pack.repository.product.EProduct;


public class ProductModel implements IProductModel {
 
  IProductsRep repository;

    
    @Override
    public void injectRepository(IProductsRep repository) {
      this.repository = repository;
    } 



  @Override
  public boolean addProduct(Product product) {
   return repository.addProduct(product);

  }

  @Override
  public EProduct findProductByID(int id) {
    return repository.findProductByID(id);
  }

  public boolean deleteProduct(int id){
    return repository.deleteProduct(id);
  }
  @Override
  public ArrayList<Product> findAllProducts() {
    ArrayList<Product> products =  repository.findAllProducts();
    return products; 
  }
}
