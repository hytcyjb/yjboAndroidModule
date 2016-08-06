package com.yjbo.yjboandroidmodule.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.entity.HomeStatusClass;
import com.yjbo.yjboandroidmodule.service.CramerThread;
import com.yjbo.yjboandroidmodule.util.L;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MyCramerActivity extends Activity implements
        SurfaceHolder.Callback {
    private SurfaceView surfaceview;// 视频预览控件
    private LinearLayout lay; // 愿揽控件的
    private SurfaceHolder surfaceHolder; // //和surfaceView相关的
    private Context context;
    private boolean isRecorder = false;
    private TextView time;
    WindowManager wm;
//	LinearLayout relLay;

    /**
     * 检测摄像头硬件 如果应用程序未使用manifest声明对摄像头需求进行特别指明，则应该在运行时检查一下摄像头是否可用
     */
    public boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        L.i("----onNewIntent显示录像-----");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 全屏显示
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN,
//				LayoutParams.FLAG_FULLSCREEN);
//		getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.my_cramer);
        EventBus.getDefault().register(this);

        surfaceview = (SurfaceView) findViewById(R.id.surfaceView1);
        // 创建Camera实例
        if (!checkCameraHardware(this)) {
            Toast.makeText(this, "摄像头被占用或不存在", Toast.LENGTH_LONG).show();
            return;
        }

//		// 设置悬浮窗体属性
//		// 1.得到WindoeManager对象：
////		wm = (WindowManager) getApplicationContext().getSystemService("window");
//		wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
//		// 2.得到WindowManager.LayoutParams对象，为后续设置相关参数做准备：
//		LayoutParams wmParams = new LayoutParams();
//		// 3.设置相关的窗口布局参数，要实现悬浮窗口效果，要需要设置的参数有
//		// 3.1设置window type
//		wmParams.type = LayoutParams.TYPE_PHONE;
//		// 3.2设置图片格式，效果为背景透明 //wmParams.format = PixelFormat.RGBA_8888;
//		wmParams.format = 1;
//		// 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
//		wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
//				| LayoutParams.FLAG_NOT_FOCUSABLE;
//		// 4.// 设置悬浮窗口长宽数据
//		wmParams.width = LayoutParams.WRAP_CONTENT;
//		wmParams.height = LayoutParams.WRAP_CONTENT;
//		// 5. 调整悬浮窗口至中间
//		wmParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
//		// 6. 以屏幕左上角为原点，设置x、y初始值
//		wmParams.x = 0;
//		wmParams.y = 0;
        // 7.将需要加到悬浮窗口中的View加入到窗口中了：
        // 如果view没有被加入到某个父组件中，则加入WindowManager中
//		surfaceview = new SurfaceView(this);
        surfaceHolder = surfaceview.getHolder();
//		LayoutParams params_sur = new LayoutParams();
//		params_sur.width = 1;
//		params_sur.height = 1;
//		params_sur.alpha = 255;
//		surfaceview.setLayoutParams(params_sur);

        surfaceview.getHolder()
                .setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // surface.getHolder().setFixedSize(800, 1024);
        surfaceview.getHolder().addCallback(this);

//		relLay = new LinearLayout(this);
//		LayoutParams params_rel = new LayoutParams();
//		params_rel.width = LayoutParams.WRAP_CONTENT;
//		params_rel.height = LayoutParams.WRAP_CONTENT;
//		relLay.setLayoutParams(params_rel);
//		relLay.addView(surfaceview);
//		wm.addView(relLay, wmParams); // 创建View

        time = (TextView) findViewById(R.id.video_timer);
        // 设置计时器不可见
//		time.setVisibility(View.GONE);

//		// 回到主界面
//		Intent i = new Intent(MyCramerActivity.this, Main.class);
//		// i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		i.addCategory(Intent.CATEGORY_HOME);
//		startActivity(i);
//		 finish();
        //

        // 初始化控件
        // init();
    }

    //	/** * 初始化控件以及回调 */
//	private void init() {
//		// surfaceview = (SurfaceView) this.findViewById(R.id.surfaceview);
//		// lay = (LinearLayout) this.findViewById(R.id.lay);
//		// lay.setVisibility(LinearLayout.INVISIBLE);
//
//		SurfaceHolder holder = this.surfaceview.getHolder();// 取得holder
//		holder.addCallback(this); // holder加入回调接口 // 设置setType
//		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//		System.out.println("--------------");
//
//	}
    @Subscribe(threadMode = ThreadMode.BackgroundThread)
    public void onMainEventBus(HomeStatusClass msg) {
        L.i("onEventBus() returned: " + Thread.currentThread() + "--" + msg.getStatusStr());
        Toast.makeText(MyCramerActivity.this, "开始录像0", Toast.LENGTH_SHORT).show();
        if ("继续录像".equals(msg.getStatusStr())) {
            L.i("--开始录像--");
//            CramerThread.recordTime = 10 * 1000;
//            SurfaceView surfaceview = new SurfaceView(this);
//            CramerThread.surfaceHolder = surfaceview.getHolder();
//            CramerThread thread = new CramerThread(context);
//            thread.start();
            Toast.makeText(MyCramerActivity.this, "开始录像1", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2,
                               int arg3) {
        System.out.println("*****");
        // 将holder，这个holder为开始在oncreat里面取得的holder，将它赋给surfaceHolder
        surfaceHolder = holder;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("++++++");

        // 将holder，这个holder为开始在oncreat里面取得的holder，将它赋给surfaceHolder
        surfaceHolder = holder;
        CramerThread.recordTime = 10 * 1000;
        CramerThread.surfaceHolder = surfaceHolder;
        Log.i("process", Thread.currentThread().getName());
        // //录像线程，当然也可以在别的地方启动，但是一定要在onCreate方法执行完成以及surfaceHolder被赋值以后启动
//        CramerThread thread = new CramerThread(10 * 1000, surfaceview,
//                surfaceHolder, context, time);// 设置录制时间为2小时
        CramerThread thread = new CramerThread(context);// 设置录制时间为2小时
        thread.start();
        Toast.makeText(MyCramerActivity.this, "开始录像", Toast.LENGTH_LONG).show();
        // if (isRecorder == false) {
        // thread.start();
        // Toast.makeText(RecordDemoActivity.this, "开始录像", Toast.LENGTH_LONG)
        // .show();
        // isRecorder = true;
        // } else if (isRecorder == true) {
        // thread.stopRecord();
        // isRecorder = false;
        // }

//		thread.stopRecord();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        System.out.println("*****");
        // surfaceDestroyed的时候同时对象设置为null
        surfaceview = null;
        surfaceHolder = null;
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    public void delete() {
//		if (relLay.getParent() != null)
//			wm.removeView(relLay);
    }

    public void back() {
//		Intent intent = new Intent();
//		intent.setClass(MyCramerActivity.this, Main.class);
//		startActivity(intent);
//		finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

//		if (relLay.getParent() != null)
//			wm.removeView(relLay);
        back();

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
