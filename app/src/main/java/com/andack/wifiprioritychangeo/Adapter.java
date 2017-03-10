package com.andack.wifiprioritychangeo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 项目名称：WifiPriorityChangeO
 * 项目作者：anDack
 * 项目时间：2017/3/9
 * 邮箱：    1160083806@qq.com
 * 描述：    列表Adapter
 */

public class Adapter extends BaseAdapter {
    private ArrayList<MEntity> mEntities;
    private Context context;
    private LayoutInflater inflater;
//    private static setOnItemListener setOnItemListener;
    public Adapter(ArrayList<MEntity> mEntities, Context context){
        this.mEntities=mEntities;
        this.context=context;
        inflater= (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;

        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_layout,null);
            viewHolder.wifiPriority= (TextView) convertView.
                    findViewById(R.id.wifi_priority);
            viewHolder.wifiSsid= (TextView) convertView.
                    findViewById(R.id.wifi_ssid);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.wifiSsid.setText(mEntities.get(position).getWifiSsid());
        viewHolder.wifiPriority.setText(mEntities.get(position).getWifiPriority()+"");

        return convertView;
    }
    class ViewHolder{
        private TextView wifiSsid;
        private TextView wifiPriority;
    }
}
