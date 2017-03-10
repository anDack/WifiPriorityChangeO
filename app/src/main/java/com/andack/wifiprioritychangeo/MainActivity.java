package com.andack.wifiprioritychangeo;

import android.content.DialogInterface;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *这个暂时做6.0以下的
 */
public class MainActivity extends AppCompatActivity  {
    private ListView listView;
    private WifiTools wifiTools;
    private List<WifiConfiguration> wifiConfigurations;
    private ArrayList<MEntity> mEntities;
    private ArrayList<Integer> numList;
    private Adapter adapter;

    private int max=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mEntities=new ArrayList<>();
        numList=new ArrayList<>();
        wifiTools=new WifiTools(this);
        if (wifiTools.isEnableState()) {
            wifiTools.startScan();
            wifiConfigurations=wifiTools.getConfiguration();
            Log.i("Myactivity", "initData: in"+wifiConfigurations.size());

            for (int i = 0; i < wifiConfigurations.size(); i++) {

                MEntity mentity=new MEntity();
                int priority=wifiConfigurations.get(i).
                        priority;
                mentity.setWifiPriority(priority);
                mentity.setWifiSsid(wifiConfigurations.get(i).
                        SSID);
                numList.add(priority);
                if (max<priority){
                    max=priority;
                }
                mEntities.add(mentity);
            }
        }else {
            wifiTools.openWifi();
        }
        adapter=new Adapter(mEntities,this);
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.wifi_list);
        listView.setAdapter(adapter);
        final int[] item=new int[numList.size()];
        final String[] items=new String[numList.size()];
        for (int i = 0; i < numList.size(); i++) {
            item[i]=numList.get(i);
            items[i]=numList.get(i)+"级";
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                wifiConfigurations=wifiTools.getConfiguration();
               final WifiConfiguration wificon=wifiConfigurations.get(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("优先级选择");
                builder.setMessage("优先级越高，越先连接");
                final EditText editText=new EditText(MainActivity.this);
                builder.setView(editText);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String proStr=editText.getText().toString();
                        if (TextUtils.isEmpty(proStr)) {
                            Toast.makeText(MainActivity.this, "没有修改优先级", Toast.LENGTH_SHORT).show();
                        }else {
                            int pro=0;
                            try {
                                pro=Integer.parseInt(proStr);
                            }catch (Exception e){
                                Toast.makeText(MainActivity.this, "非法数据,优先级降为零", Toast.LENGTH_SHORT).show();
                            }
                            wificon.priority=pro;
                            wifiTools.updateConfig(wificon);
                            MEntity m=new MEntity();
                            m.setWifiPriority(wificon.priority);
                            m.setWifiSsid(wificon.SSID);
                            mEntities.add(m);
                            mEntities.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

    }
}
