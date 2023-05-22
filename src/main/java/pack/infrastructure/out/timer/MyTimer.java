package pack.infrastructure.out.timer;

import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import pack.infrastructure.out.websocket.WebSocket;

@Stateless
public class MyTimer {
    @Schedule(hour = "17", dayOfWeek = "Mon, Tue, Wed, Thu, Fri")
    public void sendMessage() {
        WebSocket.sendAdmin("До конца рабочего дня осталось 10 минут");
    }
}