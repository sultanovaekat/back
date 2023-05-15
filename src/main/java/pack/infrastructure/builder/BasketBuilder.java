package pack.infrastructure.builder;

import pack.application.api.in.IBasketModel;
import pack.application.api.out.IBasketRep;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Default;



public class BasketBuilder { 

    @Inject @Default
    private IBasketModel model;

    @Inject @Default
    private IBasketRep repository;

    @Produces @Built
    public IBasketModel buildModel() {
	   model.injectRepository(repository);
       return model;
    } 
}