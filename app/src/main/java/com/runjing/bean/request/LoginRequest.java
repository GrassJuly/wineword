package com.runjing.bean.request;

import com.runjing.base.BaseRequest;

/**
 * @Created: qianxs  on 2020.07.16 21:30.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 21:30.
 * @Remark:
 */
public class LoginRequest extends BaseRequest {
    private String phone;
    private String pin;
    private String city;
    private int platform;
    private int registerChnnel;
    private int userLevel;
    private int userType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getRegisterChnnel() {
        return registerChnnel;
    }

    public void setRegisterChnnel(int registerChnnel) {
        this.registerChnnel = registerChnnel;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "phone='" + phone + '\'' +
                ", pin='" + pin + '\'' +
                ", city='" + city + '\'' +
                ", platform=" + platform +
                ", registerChnnel=" + registerChnnel +
                ", userLevel=" + userLevel +
                ", userType=" + userType +
                '}';
    }
}
