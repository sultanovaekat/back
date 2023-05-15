package pack.infrastructure.websocket;

public interface IDeliveryAsync {
    void nextAndUpdate(IDeliveryUpdate updater);
}
