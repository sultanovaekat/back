package pack.infrastructure.deliveryUpdater;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import pack.application.api.out.IDeliveryAsync;
import pack.application.api.out.IDeliveryUpdate;

@Startup
@Singleton
public class DeliveryUpdate implements IDeliveryAsync {
    @Asynchronous
    @Override
    public void nextAndUpdate(IDeliveryUpdate updater) {
        updater.update();
    }
}
