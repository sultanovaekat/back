package pack.infrastructure.websocket;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class DeliveryUpdate implements IDeliveryAsync {
    @Asynchronous
    @Override
    public void nextAndUpdate(IDeliveryUpdate updater) {
        updater.update();
    }
}
