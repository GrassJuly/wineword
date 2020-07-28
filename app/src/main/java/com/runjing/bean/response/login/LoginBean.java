package com.runjing.bean.response.login;

/**
 * @Created: qianxs  on 2020.07.28 14:41.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.28 14:41.
 * @Remark:
 */
public class LoginBean {
    private LoginWelfareBean getWelfare;
    private String customerMobile;
    private int userType;

    public LoginWelfareBean getGetWelfare() {
        return getWelfare;
    }

    public void setGetWelfare(LoginWelfareBean getWelfare) {
        this.getWelfare = getWelfare;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "getWelfare=" + getWelfare +
                ", customerMobile='" + customerMobile + '\'' +
                ", userType=" + userType +
                '}';
    }
}
