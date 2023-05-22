package pack.infrastructure.builder;

import pack.application.api.in.IBasket;
import pack.application.api.out.IBasketRep;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Default;



public class BasketBuilder { 

    @Inject @Default
    private IBasket model;

    @Inject @Default
    private IBasketRep repository;

    @Produces @Built
    public IBasket buildModel() {
	   model.injectRepository(repository);
       return model;
    } 
}