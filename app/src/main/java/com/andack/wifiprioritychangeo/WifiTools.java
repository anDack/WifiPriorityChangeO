package com.andack.wifiprioritychangeo;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * 项目名称：WifiPriorityChangeO
 * 项目作者：anDack
 * 项目时间：2017/3/9
 * 邮箱：    1160083806@qq.com
 * 描述：    Wifi的工具
 */

public class WifiTools {
    private Context context;
    private List<ScanResult>mWifiList;
    private WifiManager wifiManager;
    private List<WifiConfiguration> wifiConfigurations;
    public WifiTools(Context context){
        this.context=context;
        wifiManager= (WifiManager) context.
                getSystemService(Context.WIFI_SERVICE);
    }
    public void openWifi(){
        if(!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
    }
    //关闭wifi
    public void closeWifi(){
        if(!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(false);
        }
    }
    // 检查当前wifi状态
    public boolean isEnableState() {
        if (wifiManager.getWifiState()== WifiManager.WIFI_STATE_ENABLED) {
           return true;
        }
        return false;
    }
    public List<WifiConfiguration> getConfiguration(){
        return wifiConfigurations;
    }
    public void startScan(){
        wifiManager.startScan();
        //得到扫描结果
        mWifiList=wifiManager.getScanResults();
        //得到配置好的网络连接
        wifiConfigurations=wifiManager.getConfiguredNetworks();
    }
    //得到网络列表
    public List<ScanResult> getWifiList(){
        return mWifiList;
    }

    /**
     * 更新WiFi优先级，低于Android M
     *
     */
    public void updateConfig(WifiConfiguration wifi){

           wifiManager.updateNetwork(wifi);
//            Log.i("update", "upDateConfig: "+code);
    }
}
