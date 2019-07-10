package com.rocky.newringtones.http.model;

public class PublicRepository {
    private static PublicRepository mPublicRepository;

    public static PublicRepository getIntance(){
        if (null==mPublicRepository){
            mPublicRepository=new PublicRepository();
        }
        return mPublicRepository;
    }
    private String tooken;

    private String uid;

    private String sign;

    private String url;

    public String getTooken() {
        return tooken;
    }

    public void setTooken(String tooken) {
        this.tooken = tooken;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
