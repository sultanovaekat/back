package pack.application.api.in;

import pack.infrastructure.websocket.IDeliveryUpdate;
import pack.application.api.dto.Delivery;
import pack.application.api.out.IOrderRep;

import java.util.ArrayList;

public interface IDeliveryModel {
    void injectRepository(IOrderRep repository);
    ArrayList<Delivery> findOrderByLogin(String userLogin);
    ArrayList<Delivery> findAllOrders();
    void update(IDeliveryUpdate updater, String login);
}
