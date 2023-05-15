package pack.infrastructure.interconnector;

import jakarta.ejb.MessageDriven;

import jakarta.jms.MessageListener;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import jakarta.jms.JMSException;
import pack.infrastructure.websocket.WebSocket;

@MessageDriven(mappedName = "jms/TestQueue")

public class Interconnector implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            String text = textMessage.getText();
            WebSocket.sendAdmin(text);
        } catch (JMSException e) {}
    }
    // принимает сообщение
}