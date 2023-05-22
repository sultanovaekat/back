package pack.infrastructure.out.websocket;

import java.io.IOException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket")
public class WebSocket {

    private final static ConcurrentLinkedQueue<Session> queue = new ConcurrentLinkedQueue<>();
    private final static ConcurrentHashMap<String,Session> mapSession = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<Session,String> mapLogins = new ConcurrentHashMap<>();

    public static void sendAll(String message) {
        try {
            for (Session sess : queue) {
                if (sess.isOpen()) {
                    sess.getBasicRemote().sendText(message);
                }
            }
        }
        catch (IOException ioe) {
        }
    }

    public static void send(String login) {
        try {
            Session session = mapSession.get(login);
            if (login!=null) {
                session.getBasicRemote().sendText("");
            }
        }
        catch (IOException ioe) {
        }
    }
    public static void sendAdmin( String message) {
        try {
            Session session = mapSession.get("admin");
                session.getBasicRemote().sendText(message);
        }
        catch (IOException ioe) {
        }
    }

    @OnOpen
    public void connectionOpened(Session session) {
        queue.add(session);
    }

    @OnClose
    public void connectionClosed(Session session) {
        queue.remove(session);
        String message = mapLogins.remove(session);
        mapSession.remove(message);
    }

    @OnMessage
    public void processMessage(Session session, String login) {
        mapLogins.put(session, login);
        mapSession.put(login,session );
    }

}
