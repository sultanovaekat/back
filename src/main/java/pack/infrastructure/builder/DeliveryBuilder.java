package pack.infrastructure.builder;
import pack.application.api.in.IDelivery;
import pack.application.api.out.IOrderRep;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Default;

public class DeliveryBuilder {
    @Inject @Default
    private IDelivery model;

    @Inject @Default
    private IOrderRep repository;

    @Produces @Built
    public IDelivery buildModel() {
        model.injectRepository(repository);
        return model;
    }
}
