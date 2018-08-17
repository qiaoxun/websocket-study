package com.study.endpoint;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

@ServerEndpoint("/dukeetf")
public class ETFEndpoint {

    private static Queue<Session>  queue = new ConcurrentLinkedQueue<>();

    private static ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    private static Random random = new Random();

    static {
        ses.scheduleAtFixedRate(() ->
            send(100 * random.nextDouble(), random.nextInt(100))
        ,1000, 1000, TimeUnit.MILLISECONDS);
    }

    private static void send(double price, int volume) {
        String msg = String.format("%.2f / %d", price, volume);
        try {
            for (Session session : queue) {
                session.getBasicRemote().sendText(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void openConnection(Session session) {
        queue.add(session);
        System.out.println("session " + session.getId() + " has been added to the queue. And the size of queue is " + queue.size());
    }

    @OnClose
    public void closedConnection(Session session) {
        queue.remove(session);
        System.out.println("session " + session.getId() + " has been closed. And the size of queue is " + queue.size());
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
        t.printStackTrace();
    }

}
