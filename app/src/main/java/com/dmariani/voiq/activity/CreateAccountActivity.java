package com.dmariani.voiq.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmariani.voiq.R;
import com.dmariani.voiq.model.*;
import com.dmariani.voiq.request.RequestListener;
import com.dmariani.voiq.request.RequestManager;
import com.dmariani.voiq.util.StringUtils;

import java.util.Calendar;

/**
 * Provides an activity that allows account operation
 *
 * @author Danielle Mariani
 */
public class CreateAccountActivity extends AppCompatActivity implements OnClickListener, DatePickerDialog.OnDateSetListener, RequestListener<User> {

    // Views
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPasswordConfirmation;
    private EditText editTextPhone;
    private EditText editTextZipCode;
    private TextView buttonBirthday;
    private Button buttonCreateAccount;
    private ProgressDialog dialog;

    // Attrs
    private User user;
    private Calendar currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        editTextFirstName = (EditText) findViewById(R.id.edittext_first_name);
        editTextLastName = (EditText) findViewById(R.id.edittext_last_name);
        editTextEmail = (EditText) findViewById(R.id.edittext_email);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        editTextPasswordConfirmation = (EditText) findViewById(R.id.edittext_password_confirmation);
        editTextPhone = (EditText) findViewById(R.id.edittext_phone);
        editTextZipCode = (EditText) findViewById(R.id.edittext_zip_code);
        buttonBirthday = (TextView) findViewById(R.id.edittext_birthday);
        buttonCreateAccount = (Button) findViewById(R.id.button_create_account);

        buttonBirthday.setOnClickListener(this);
        buttonCreateAccount.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * CLICK EVENTS
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_create_account) {
            onClickCreateAccountButton();
        } else if (id == R.id.edittext_birthday) {
            onClickBirthdayButton();
        }
    }

    private void onClickCreateAccountButton() {
        if (validateInput()) {
            dialog = new ProgressDialog(this);
            dialog.setMessage(getString(R.string.creating_account));
            dialog.show();
            RequestManager.createAccountRequest(this, user, this);
        }

    }

    private boolean validateInput() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmationPassword = editTextPasswordConfirmation.getText().toString();
        String birthday = buttonBirthday.getText().toString();
        String zipCode = editTextZipCode.getText().toString();
        String phone = editTextPhone.getText().toString();

        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName)
                && !StringUtils.isValidEmail(email) && !StringUtils.isValidPassword(password)
                && !StringUtils.isValidPassword(confirmationPassword) && TextUtils.isEmpty(zipCode)
                && TextUtils.isEmpty(phone)) {
            Toast.makeText(this, R.string.invalid_create_account_input, Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, R.string.invalid_first_name_input, Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, R.string.invalid_last_name_input, Toast.LENGTH_LONG).show();
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

        if (!StringUtils.isValidPassword(confirmationPassword) || !password.equals(confirmationPassword)) {
            Toast.makeText(this, R.string.invalid_confirmation_password_input, Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(zipCode)) {
            Toast.makeText(this, R.string.invalid_zip_code_input, Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, R.string.invalid_phone_input, Toast.LENGTH_LONG).show();
            return false;
        }

        user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setConfirmationPassword(confirmationPassword);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setZipCode(zipCode);
        return true;
    }


    private void onClickBirthdayButton() {
        showDatePicker();
    }

    public void showDatePicker() {
        if (currentDate == null) {
            currentDate = Calendar.getInstance();
        }

        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, this, year, month, day).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        currentDate.set(year, monthOfYear, dayOfMonth);

        final String format = "yyyy-MM-dd";
        buttonBirthday.setText(StringUtils.dateToString(currentDate, format));
    }

    @Override
    public void onSuccess(User response) {
        Toast.makeText(this, "Create Account: Successful", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        Toast.makeText(this, "Create Account: Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinish() {
        dialog.dismiss();
    }
}
