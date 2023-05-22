package pack.application.api.out;

import pack.application.dto.Delivery;

import java.util.ArrayList;

public interface IOrderRep {
    ArrayList<Delivery> findOrder(String  user);
    ArrayList<Delivery> findAllOrders();
    void updateStatus(String login);}
