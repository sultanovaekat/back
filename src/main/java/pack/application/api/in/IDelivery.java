package pack.application.api.in;

import pack.application.api.out.IDeliveryUpdate;
import pack.application.dto.Delivery;
import pack.application.api.out.IOrderRep;

import java.util.ArrayList;

public interface IDelivery {
    void injectRepository(IOrderRep repository);
    ArrayList<Delivery> findOrderByLogin(String userLogin);
    ArrayList<Delivery> findAllOrders();
    void update(IDeliveryUpdate updater, String login);
}
