package com.dmariani.voiq.request;

import com.dmariani.voiq.model.LoginRequest;
import com.dmariani.voiq.model.User;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Defines Web Service API
 */
public interface RequestAPI {

    @POST("/app/controller/dt_users.php?f=login")
    void login(@Body LoginRequest login, Callback<Object> callback);

    @POST("/app/controller/dt_users.php?f=createUser")
    void createAccount(@Body User user, Callback<Object> callback);
}
