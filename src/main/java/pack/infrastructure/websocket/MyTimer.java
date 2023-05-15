package pack.infrastructure.websocket;

import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
@Stateless
public class MyTimer {
    @Schedule(hour = "11", dayOfWeek = "Mon, Tue, Wed, Thu, Fri")
    public void sendMessage() {
        WebSocket.sendAdmin("До конца рабочего дня осталось 10 минут");
    }
}