package pack.application.implemetation;

import java.util.ArrayList;


import pack.application.api.in.IProduct;
import pack.application.api.out.IProductsRep;
import pack.infrastructure.out.repository.product.EProduct;


public class Product implements IProduct {
  IProductsRep repository;
    
    @Override
    public void injectRepository(IProductsRep repository) {
      this.repository = repository;
    }
  @Override
  public boolean addProduct(pack.application.dto.Product product) {
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
  public ArrayList<pack.application.dto.Product> findAllProducts() {
    ArrayList<pack.application.dto.Product> products =  repository.findAllProducts();
    return products; 
  }
}
