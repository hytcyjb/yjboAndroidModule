package com.yjbo.yjboandroidmodule.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.ClipboardManager;
import android.widget.EditText;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.entity.MainMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/***
 * josn解析
 * 2016年6月22日11:12:17
 */
public class JsonActivity extends AppCompatActivity {

    @Bind(R.id.edt_show)
    EditText edtShow;
    @Bind(R.id.edt_show_2)
    EditText edtShow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);

        ClipboardManager clip = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
//        clip.getText(); // 粘贴
        clip.setText("杨建波你好"); // 复制
        String json1 = getString(R.string.json_1);
        String json2 = getString(R.string.json_2);
//        edtShow.setText(R.string.json_1);
//        edtShow2.setText(R.string.json_2);
        try {
            JSONObject jsonObject = new JSONObject(json1);
            String address = jsonObject.optString("address");
            String success = jsonObject.optString("success");
            edtShow.setText("address="+address+"--"+"success="+success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(json2);
            JSONArray outputs = jsonObject.getJSONArray("outputs");
            JSONObject jsonObject1 = new JSONObject(outputs.get(0)+"");
            String  outputValue = jsonObject1.optString("outputValue");
            JSONObject jsonObject2 = new JSONObject(outputValue);
            String  dataValue = jsonObject2.optString("dataValue");

            edtShow2.setText(dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Message message = new Message();
        message.obj = "很好";
        message.what = 0;
        mhandler.sendMessage(message);
    }
    Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            edtShow.setText("正常的handler，没有处理");
        }
    };
}
