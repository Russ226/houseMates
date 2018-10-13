package com.housemate.jsonhelpers.message;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;

public class SuccessExpenseMessage implements Message {
    private String message;
    private HttpStatus statusCode;

    public SuccessExpenseMessage(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }


    @Override
    public String getJson() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
