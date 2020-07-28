package com.runjing.bean.response.login;

import com.runjing.base.BaseResponse;

/**
 * @Created: qianxs  on 2020.07.16 21:30.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.16 21:30.
 * @Remark:
 */
public class LoginResponse extends BaseResponse {

    private LoginBean data;

    public LoginBean getData() {
        return data;
    }

    public void setData(LoginBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                '}';
    }
}
