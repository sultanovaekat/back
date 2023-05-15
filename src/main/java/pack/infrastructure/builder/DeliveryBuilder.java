package pack.infrastructure.builder;
import pack.application.api.in.IDeliveryModel;
import pack.application.api.out.IOrderRep;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Default;

public class DeliveryBuilder {
    @Inject @Default
    private IDeliveryModel model;

    @Inject @Default
    private IOrderRep repository;

    @Produces @Built
    public IDeliveryModel buildModel() {
        model.injectRepository(repository);
        return model;
    }
}
