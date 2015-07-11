package com.dmariani.voiq.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class User {

    @SerializedName("userId")
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    @SerializedName("password1")
    private String password;

    @SerializedName("password2")
    private String confirmationPassword;

    @SerializedName("phone1")
    private String phone;

    @SerializedName("zipcode")
    private String zipCode;

    @SerializedName("birth")
    private String birthday;

    private int androidDevice;

    private int campaignId;

    @SerializedName("usrPicName")
    private String avatar;

    public User() {
        androidDevice = 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String toJsonString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public JSONObject toJson() {
        try {
            String jsonString = toJsonString();
            JSONObject json = new JSONObject(jsonString);
            return json;
        } catch (Exception e) {
            return null;
        }
    }
}
