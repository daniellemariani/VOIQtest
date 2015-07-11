package com.dmariani.voiq.request;

import com.dmariani.voiq.model.*;

/**
 * Communicates responses from server.
 *
 * @author Danielle Mariani
 */
public interface RequestListener<T> {

    void onSuccess(T response);

    void onFailure(ErrorResponse errorResponse);

    void onFinish();
}
