package pack.infrastructure.builder;

import pack.application.api.in.IUser;
import pack.application.api.out.IUsersRep;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;

import jakarta.enterprise.inject.Default;


public class UsersBuilder { 

    @Inject @Default
    private IUser model;

    @Inject @Default
    private IUsersRep repository;

    @Produces @Built
    public IUser buildModel() {
	   model.injectRepository(repository);
       return model;
    } 
}