package pack.application;

import java.util.ArrayList;


import pack.application.api.dto.Product;
import pack.application.api.in.IProductModel;
import pack.application.api.out.IProductsRep;
import pack.infrastructure.repository.product.EProduct;


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
  public boolean checkProductByID(int id){return repository.checkProductByID(id);};
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
