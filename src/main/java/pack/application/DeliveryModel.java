package pack.application;

import jakarta.ejb.Asynchronous;
import pack.infrastructure.websocket.IDeliveryUpdate;
import pack.application.api.dto.Delivery;
import pack.application.api.in.IDeliveryModel;
import pack.application.api.out.IOrderRep;


import java.util.ArrayList;

public class DeliveryModel implements IDeliveryModel{
    @Override
    public void injectRepository(IOrderRep repository) {
        this.repository = repository;
    }
    IOrderRep repository;
    @Asynchronous
    public void update(IDeliveryUpdate updater,String login) {
        updater.update();
        repository.updateStatus(login);
    }
    public ArrayList<Delivery> findOrderByLogin(String userLogin) {
        ArrayList<Delivery>  orders =  repository.findOrder(userLogin);
        return orders;}
    public ArrayList<Delivery> findAllOrders(){
        ArrayList<Delivery>  orders =  repository.findAllOrders();
        return orders;}
}
