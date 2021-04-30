package com.example.lettre;

public class Message {
    private String messageId, message,senderId,receiverName;
    private String time;


    public Message() {
    }

    public Message(String message, String senderId, String time,String receiverName) {
        this.message = message;
        this.senderId = senderId;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
