package com.example.whatsappclone;

import java.util.List;

public class ChatResponse {
    int ResponseCode;
    String Message;
    List <Chat> Data;

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<Chat> getData() {
        return Data;
    }

    public void setData(List<Chat> data) {
        Data = data;
    }
}
