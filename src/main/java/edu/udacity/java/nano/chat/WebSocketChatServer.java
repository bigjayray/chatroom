package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat") //@ServerEndpoint is used to decorate a class that implements a WebSocket server endpoint
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        onlineSessions.values().forEach(session -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        //TODO: add on open connection.

        // new message object
        Message message = new Message();

        // add session
        onlineSessions.put(session.getId(), session);;
        // add user
        message.setOnlineCount(onlineSessions.size());
        // build message
        message.setType(Message.MessageType.OTHER);
        // encode message to json
        String json = JSON.toJSONString(message);
        // send message
        sendMessageToAll(json);

    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.

        //add session
        onlineSessions.put(session.getId(), session);
        //parse a json string to the Message class
        Message message = JSON.parseObject(jsonStr, Message.class);
        // build message
        message.setType(Message.MessageType.SPEAK);
        // encode message to json
        String json = JSON.toJSONString(message);
        // send message
        sendMessageToAll(json);

    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.

        // new message object
        Message message = new Message();
        //remove session
        onlineSessions.remove(session.getId(), session);
        // update user
        message.setOnlineCount(onlineSessions.size());
        // build message
        message.setType(Message.MessageType.OTHER);
        // encode message to json
        String json = JSON.toJSONString(message);
        // send message
        sendMessageToAll(json);

    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
