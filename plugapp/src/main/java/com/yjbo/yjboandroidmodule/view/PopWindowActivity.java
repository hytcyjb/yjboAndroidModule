package com.yjbo.yjboandroidmodule.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.base.BaseYjboActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopWindowActivity extends BaseYjboActivity {

    @Override
    public void setonCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pop_window);
        ButterKnife.bind(this);
    }

    @Override
    public void setonView() {
        setSGNextStr("弹出pop");
    }

    @Override
    public void setonData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    List<Map<String, Object>> listData = new ArrayList<>();

    private void initPop() {
//        listData = new ArrayList<>();
        View layoutView = LayoutInflater.from(PopWindowActivity.this).inflate(
                R.layout.pop_webview_layout, null);
        PopupWindow popupWindow = null;
        ListView webCZListView = (ListView) layoutView.findViewById(R.id.web_caozuo_listView);
//        for(int i =0; i < lengh; i++) {
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("name", "操作");
        listData.add(item);
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("name", "保存");
        listData.add(item1);
        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("name", "编辑");
        listData.add(item2);
//        }
        webCZListView.setAdapter(new MyAdapter(PopWindowActivity.this));
//        webCZListView.setAdapter(new SimpleAdapter(debugWebActivity.this, listData, R.layout.pop_webview_item_layout,
//                new String[]{"name"}, new int[R.id.web_item_txt]));
        webCZListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ToastUtils.showShort(debugWebActivity.this, "您点击了" + listData.get(position));
                Intent intent = new Intent();
                intent.setClass(PopWindowActivity.this, PopWindowActivity.class);
                intent.putExtra("URL", "http://www.baidu.com/");
                intent.putExtra("Title", "百度");
                startActivity(intent);
            }
        });
        if (popupWindow != null) {
        } else {
            WindowManager manager=(WindowManager) getSystemService(Context.WINDOW_SERVICE);

            popupWindow = new PopupWindow(layoutView, 400,
                    manager.getDefaultDisplay().getHeight()/4, false);
            int xpos= manager.getDefaultDisplay().getWidth()/2-popupWindow.getWidth()/2;
//            popupWindow = new PopupWindow(layoutView, RelativeLayout.LayoutParams.WRAP_CONTENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT, false);
            // 显示在Activity的根视图中心
//            popupWindow.showAtLocation(debugWebActivity.this.nextPublicTxt,
//                    Gravity.RIGHT, 10, 5);

            //获取xoff

            //xoff,yoff基于anchor的左下角进行偏移。
//            popupWindow.showAsDropDown(parent,xpos, 0);
            popupWindow.showAsDropDown(nextPublicTxt,-xpos/2,0);
        }
    }

    class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.pop_webview_item_layout, null);
                holder.web_item_txt = (TextView) convertView.findViewById(R.id.web_item_txt);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.web_item_txt.setText((String) listData.get(position).get("name"));
//            //给每一个列表后面的按钮添加响应事件
//            holder.viewBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showInfo();
//                }
//            });

            return convertView;
        }

        public final class ViewHolder {
            public TextView web_item_txt;
        }
    }
    @OnClick({R.id.next_public_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_public_txt:
                initPop();
                break;
        }
    }
}
