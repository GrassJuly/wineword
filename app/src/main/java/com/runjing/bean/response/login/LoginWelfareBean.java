package com.runjing.bean.response.login;

/**
 * @Created: qianxs  on 2020.07.28 14:42.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.28 14:42.
 * @Remark:
 */
public class LoginWelfareBean {
    private boolean alreadyGet;

    public boolean isAlreadyGet() {
        return alreadyGet;
    }

    public void setAlreadyGet(boolean alreadyGet) {
        this.alreadyGet = alreadyGet;
    }

    @Override
    public String toString() {
        return "LoginWelfareBean{" +
                "alreadyGet=" + alreadyGet +
                '}';
    }
}
