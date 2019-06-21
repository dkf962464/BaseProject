package com.rocky.newringtones.search.model;


public class aaa {

    /**
     * cmd : 0
     * code : 0
     * data : {"name":"wanghaowei","pwd":"123456","uuid":4}
     * msg : 登陆成功
     */

    private int cmd;
    private int code;
    private DataBean data;
    private String msg;

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * name : wanghaowei
         * pwd : 123456
         * uuid : 4
         */

        private String name;
        private String pwd;
        private int uuid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public int getUuid() {
            return uuid;
        }

        public void setUuid(int uuid) {
            this.uuid = uuid;
        }
    }
}
