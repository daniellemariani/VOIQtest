package com.dmariani.voiq.request;

import android.content.Context;
import android.util.Log;

import com.dmariani.voiq.model.*;
import com.dmariani.voiq.model.ErrorResponse;
import com.dmariani.voiq.util.DeviceUtils;
import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Manages Web Service Requests
 */
public class RequestManager {

    private static final String LOG_TAG = "RequestManager";
    private static final String BASE_URL = "http://54.205.46.179";
    private static RestAdapter restAdapter;
    private static RequestAPI requestAPI;

    private static void initRestAdapter() {
        if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
                        public void log(String msg) {
                            Log.i(LOG_TAG, msg);
                        }
                    })
                    .build();
        }

        if (requestAPI == null) {
            requestAPI = restAdapter.create(RequestAPI.class);
        }
    }

    public static void loginRequest(Context context, String email, String password, final RequestListener<User> listener) {

        if (!DeviceUtils.isNetworkAvailable(context)) {
            listener.onFinish();
            return;
        }

        initRestAdapter();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        requestAPI.login(loginRequest, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                // TODO: It could not be possible to parse a proper response
                listener.onFinish();
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(error.getMessage());
                listener.onFailure(errorResponse);
                listener.onFinish();
            }
        });
    }

    public static void createAccountRequest(Context context, final User user, final RequestListener<User> listener) {
        if (!DeviceUtils.isNetworkAvailable(context)) {
            listener.onFinish();
            return;
        }

        initRestAdapter();
        requestAPI.createAccount(user, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                // TODO: It could not be possible to parse a proper response
                listener.onFinish();
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(error.getMessage());
                listener.onFailure(errorResponse);
                listener.onFinish();
            }
        });
    }

}
