package pack.model;

import java.util.ArrayList;


import pack.model.api.dto.Product;
import pack.model.api.in.IBasketModel;
import pack.model.api.out.IBasketRep;
import pack.model.api.out.IProductsRep;
import pack.repository.product.EProduct;


public class BasketModel implements IBasketModel {
 
  IBasketRep repository;
  IProductsRep repositoryProducts;


  public boolean addToBasketFromProducts(EProduct p, String login){
      return repository.addProduct(p, login);
    }
    @Override
    public void injectRepository(IBasketRep repository) {
      this.repository = repository;
    } 

  @Override
  public boolean deleteProduct(int id, String login) {
    return repository.deleteProduct(id, login);
  }
  @Override
  public ArrayList<Product> findProductsBasket(String userLogin)  {
    ArrayList<Product> products =  repository.findProductsBasket(userLogin);
    return products; 
  }
}
