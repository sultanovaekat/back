package pack.application.implemetation;

import pack.application.api.out.IDeliveryUpdate;
import pack.application.api.in.IDelivery;
import pack.application.api.out.IOrderRep;

import java.util.ArrayList;

public class Delivery implements IDelivery {
    @Override
    public void injectRepository(IOrderRep repository) {
        this.repository = repository;
    }
    IOrderRep repository;
    public void update(IDeliveryUpdate updater,String login) {
        updater.update();
        repository.updateStatus(login);
    }
    public ArrayList<pack.application.dto.Delivery> findOrderByLogin(String userLogin) {
        ArrayList<pack.application.dto.Delivery>  orders =  repository.findOrder(userLogin);
        return orders;}
    public ArrayList<pack.application.dto.Delivery> findAllOrders(){
        ArrayList<pack.application.dto.Delivery>  orders =  repository.findAllOrders();
        return orders;}
}
