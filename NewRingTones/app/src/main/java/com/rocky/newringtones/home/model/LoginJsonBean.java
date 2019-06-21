package com.rocky.newringtones.home.model;


public class LoginJsonBean {
    private String pwd;
    private String name;
    private int uuid;

    @Override
    public String toString() {
        return "LoginJsonBean{" +
                "pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", uuid=" + uuid +
                '}';
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }
}
