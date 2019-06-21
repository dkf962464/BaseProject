package com.rocky.newringtones.base.baseutil;

public class NetWorkManager {
    private static int netWorkType=-1;
    public static int hasNetWorkState(NetWorkEnum netWorkEnum) {
        switch (netWorkEnum) {
            case WIFI:
                netWorkType= 1;
                break;
            case MOBEL:
                netWorkType= 2;
                break;
            case NO_NETWORK:
                netWorkType= 0;
                break;

        }
        return  netWorkType;
    }

}
