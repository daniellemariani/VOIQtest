package com.dmariani.voiq.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for error messages implemented by requests
 *
 * @author Danielle Mariani
 */
public class ErrorResponse {

    private int code;
    private String status;

    @SerializedName("text")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
