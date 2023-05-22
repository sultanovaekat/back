package pack.application.implemetation;

import java.util.ArrayList;

import pack.application.dto.Product;
import pack.application.api.in.IBasket;
import pack.application.api.out.IBasketRep;
import pack.infrastructure.out.repository.product.EProduct;

public class Basket implements IBasket {
  IBasketRep repository;
  @Override
  public boolean addToBasketFromProducts(EProduct p, String login){
      return repository.addProduct(p, login);
    }
    @Override
    public void injectRepository(IBasketRep repository) {
      this.repository = repository;
    }
  @Override
  public boolean deleteProduct(int id, String userLogin) {
    return repository.deleteProduct(id, userLogin);
  }
  @Override
  public ArrayList<Product> findProductsBasket(String userLogin)  {
    ArrayList<Product> products =  repository.findProductsBasket(userLogin);
    return products; 
  }
}
