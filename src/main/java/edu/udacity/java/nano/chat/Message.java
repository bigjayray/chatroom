package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private Integer onlineCount;
    private String msg;
    private MessageType type;

    public enum MessageType {
        SPEAK,
        OTHER
    }

    public Message() {

    }

    public Message(String username, Integer onlineCount, String msg, MessageType type) {
        this.username = username;
        this.onlineCount = onlineCount;
        this.msg = msg;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
