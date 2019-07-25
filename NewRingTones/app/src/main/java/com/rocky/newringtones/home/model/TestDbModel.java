package com.rocky.newringtones.home.model;

/**
 * create by 2019/7/25
 * <p>
 * author: wgl
 * <p>
 * Believe in yourself, you can do it.
 */
public class TestDbModel {
    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public LoginJsonBean getLoginJsonBean() {
        return mLoginJsonBean;
    }

    public void setLoginJsonBean(LoginJsonBean loginJsonBean) {
        mLoginJsonBean = loginJsonBean;
    }

    private int uuid;
    private LoginJsonBean mLoginJsonBean;
}
