package pack.application.api.out;

import pack.application.api.out.IDeliveryUpdate;

public interface IDeliveryAsync {
    void nextAndUpdate(IDeliveryUpdate updater);
}
