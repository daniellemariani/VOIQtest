package com.dmariani.voiq.util;

import android.text.TextUtils;

/**
 * Provides String util operations
 */
public class StringUtils {

    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 20;

    public static boolean isValidEmail(String email) {

        if (TextUtils.isEmpty(email)) {
            return false;
        }

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }

        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            return false;
        }

        return true;
    }


}
