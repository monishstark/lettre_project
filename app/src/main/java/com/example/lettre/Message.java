package com.example.lettre;

public class Message {
    private String messageId, message,senderId,receiverName;
    private long timestamp;


    public Message() {
    }

    public Message(String message, String senderId, long timestamp,String receiverName) {
        this.message = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.receiverName = receiverName;

    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
