package com.dmariani.voiq.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmariani.voiq.R;
import com.dmariani.voiq.model.ErrorResponse;
import com.dmariani.voiq.model.User;
import com.dmariani.voiq.request.RequestListener;
import com.dmariani.voiq.request.RequestManager;
import com.dmariani.voiq.util.StringUtils;

/**
 * Provides an activity that allows login operation
 *
 * @author Danielle Mariani
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener, RequestListener<User> {

    // Views
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView buttonCreateAccount;
    private TextView buttonForgotPassword;
    private TextView buttonProfile;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        editTextEmail = (EditText) findViewById(R.id.edittext_email);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonCreateAccount = (TextView) findViewById(R.id.button_create_account);
        buttonForgotPassword = (TextView) findViewById(R.id.button_forgot_password);
        buttonProfile = (TextView) findViewById(R.id.button_profile);

        buttonLogin.setOnClickListener(this);
        buttonCreateAccount.setOnClickListener(this);
        buttonForgotPassword.setOnClickListener(this);
        buttonProfile.setOnClickListener(this);
    }

    /**
     * CLICK EVENTS
     */
    @Override
    public void onClick(View view) {
       int id = view.getId();

        if (id == R.id.button_login) {
            onClickLoginButton();
        } else if (id == R.id.button_create_account) {
            onClickCreateAccount();
        } else if (id == R.id.button_forgot_password) {
            onClickForgotPasswordButton();
        }  else if (id == R.id.button_profile) {
            onClickProfileButton();
        }
    }

    private void onClickLoginButton() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (validateInput(email, password)) {
            dialog = new ProgressDialog(this);
            dialog.setMessage(getString(R.string.logging_in));
            dialog.show();

            RequestManager.loginRequest(this, email, password, this);
        }

    }

    private boolean validateInput(String email, String password) {
        if (!StringUtils.isValidEmail(email) && !StringUtils.isValidPassword(password)) {
            Toast.makeText(this, R.string.invalid_login_input, Toast.LENGTH_LONG).show();
            return false;
        }

        if (!StringUtils.isValidEmail(email)) {
            Toast.makeText(this, R.string.invalid_email_input, Toast.LENGTH_LONG).show();
            return false;
        }

        if (!StringUtils.isValidPassword(password)) {
            Toast.makeText(this, R.string.invalid_password_input, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void onClickCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    private void onClickForgotPasswordButton() {
        Toast.makeText(this, R.string.not_available, Toast.LENGTH_LONG).show();
    }

    private void onClickProfileButton() {
        String profileUrl = getString(R.string.profile);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(profileUrl));
        startActivity(browserIntent);
    }

    @Override
    public void onSuccess(User response) {
        Toast.makeText(this, "Login Success" + response.getEmail(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinish() {
        dialog.dismiss();
    }
}
