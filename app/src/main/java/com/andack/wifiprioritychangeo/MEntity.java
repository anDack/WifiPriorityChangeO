package com.andack.wifiprioritychangeo;

/**
 * 项目名称：WifiPriorityChangeO
 * 项目作者：anDack
 * 项目时间：2017/3/9
 * 邮箱：    1160083806@qq.com
 * 描述：    实体类
 */

public class MEntity {
    private String WifiSsid;
    private int WifiPriority;

    public String getWifiSsid() {
        return WifiSsid;
    }

    public void setWifiSsid(String wifiSsid) {
        WifiSsid = wifiSsid;
    }

    public int getWifiPriority() {
        return WifiPriority;
    }

    public void setWifiPriority(int wifiPriority) {
        WifiPriority = wifiPriority;
    }
}
