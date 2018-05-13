package com.yjbo.yjboandroidmodule.util.video;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.L;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class TakeVideoActivity extends Activity implements OnClickListener {
	MovieRecorder mRecorder;
	Button btnRecoder, btn_no, btn_yes,change_surfaceview;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	TextView tx_time;
	Timer timer;
	TimerTask task;
	String flagTime;
	int timeSize = 0;
	String videoStr = "";
	Camera camera;
	String timeStr;
	Date date;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/** 摄像头参数 */
	protected Camera.Parameters mParameters = null;
	/** 最大帧率 */
	public static final int MAX_FRAME_RATE = 25;
	/** 最小帧率 */
	public static final int MIN_FRAME_RATE = 15;
	/** 摄像头支持的预览尺寸集合 */
	protected List<Size> mSupportedPreviewSizes;
	/** 帧率 */
	protected int mFrameRate = MIN_FRAME_RATE;
	/** PreviewFrame调用次数，测试用 */
	protected volatile long mPreviewFrameCallCount = 0;
	private boolean hasSurface;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置横屏显示
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 选择支持半透明模式,在有surfaceview的activity中使用。
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.activity_video_take);

		btnRecoder = (Button) findViewById(R.id.btnRecoder);
		change_surfaceview = (Button) findViewById(R.id.change_surfaceview);
		btnRecoder.setOnClickListener(this);
		change_surfaceview.setOnClickListener(this);
		btn_no = (Button) findViewById(R.id.btn_no);
		btn_no.setOnClickListener(this);
		btn_yes = (Button) findViewById(R.id.btn_yes);
		btn_yes.setOnClickListener(this);
		tx_time = (TextView) findViewById(R.id.tx_time);
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		hasSurface = false;
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(surfaceHolderCallback); // holder加入回调接口
		// 这段代码是说在Android 高版本上已经不推荐使用了 ，但是如果你要兼容低版本（如Android
		// 2.3或以下版本）还是要加上这段代码，不然播放时 只会有声音 没有图像。
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder.setKeepScreenOn(true);
		// initSurfaceView();
		mRecorder = new MovieRecorder();
		refreshViewByRecordingState();
	}

	private void ChangeSurfaceView() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int mSurfaceViewWidth = dm.widthPixels;
		int mSurfaceViewHeight = dm.heightPixels;
		int w = mSurfaceViewHeight * 600 / 200;
		int margin = (mSurfaceViewWidth - w) / 2;
		L.d("margin:" + margin);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		lp.setMargins(margin, 0, margin, 0);
		surfaceView.setLayoutParams(lp);
	}

	protected void onDestroy() {
		if (timer != null) {
			timer.cancel();
		}
		exit();
		super.onDestroy();
	};


	Callback surfaceHolderCallback = new Callback() {

		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {
			surfaceHolder = null;
			hasSurface = false;
		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			// TODO Auto-generated method stub
			surfaceHolder = arg0;
			if(!hasSurface){
				hasSurface = true;
				mRecorder.startPreview(TakeVideoActivity.this, surfaceHolder);
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
								   int arg3) {
			// TODO Auto-generated method stub
			surfaceHolder = arg0;
		}
	};

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 1:
					if (flagTime == "start") {
						String timeStr =  getFormatedDateTime(timeSize);
						if (timeStr.contains("停止")){
							//停止之后再开始
							stopbtn();
							//停止之后再开始
							startbtn();
						}else{
							tx_time.setText(timeStr);
						}
//						if (timeSize < 10) {
//							tx_time.setText("00:0" + timeSize);
//						} else if (timeSize >= 60*10) {// 设置录像在30秒时间
//							stopbtn();
//						} else {
//								tx_time.setText("00:" + timeSize);
//						}
					} else {// ( flagTime == "start")

					}

					break;

				default:
					break;
			}
		}
	};
	//这是24小时以内（不包含24小时）采用的方法：
	public static String getFormatedDateTime(int dateInt) {
		String dateTime = "";
		//测试10秒自动停止录屏，再次启动录屏
		if (dateInt < 5) {
			dateTime = "00:0" + dateInt;
		}
//		else if (dateInt < 60) {
//			dateTime = "00:" + dateInt;
//		}
		//这是一小时以内采用的--此处是10分钟
//		else if (dateInt < (10 * 60 +1)) {//60' * 60 +1
//			int miaoInt = dateInt % 60 ;
//			int fenInt = dateInt / 60;
//			//分别判断分钟和秒钟是否大于10，进行赋值；大于10则不需要前面放一个0了
//			String fenStr = "";
//			String miaoStr = "";
//			if (fenInt < 10) {
//				fenStr = "0" + fenInt;
//			}else {
//				fenStr = "" + fenInt;
//			}
//			if (miaoInt < 10) {
//				miaoStr ="0" + miaoInt;
//			} else {
//				miaoStr ="" + miaoInt;
//			}
//			dateTime = fenStr + ":" + miaoStr;
//		}
		//这是一小时以上采用的
//		else if (dateInt < (60 * 60 * 24 +1)) {//60' * 60' * 24 +1
//
//			int shiInt = dateInt / 60 / 60;
//
//			dateInt = dateInt - shiInt * 60 * 60;
//			int miaoInt = dateInt % 60 ;
//			int fenInt = dateInt / 60;
//
//			//分别判断小时，分钟和秒钟是否大于10，进行赋值；大于10则不需要前面放一个0了
//			String shiStr = "";
//			String fenStr = "";
//			String miaoStr = "";
//
//			if (shiInt < 10) {
//				shiStr = "0" + shiInt;//这个不会遇到，因为没有到一小时，不走这里
//			}else {
//				shiStr = "" + shiInt;
//			}
//			if (fenInt < 10) {
//				fenStr = "0" + fenInt;
//			}else {
//				fenStr = "" + fenInt;
//			}
//			if (miaoInt < 10) {
//				miaoStr ="0" + miaoInt;
//			} else {
//				miaoStr ="" + miaoInt;
//			}
//
//			dateTime =shiStr+":"+ fenStr + ":" + miaoStr;
//		}
		else  {
			dateTime = "时间到停止计时";
		}

		return dateTime + "s";
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.change_surfaceview:
				ChangeSurfaceView();
				break;
			case R.id.btnRecoder:
				if (!mRecorder.isRecording) {
					startbtn();
				} else {
					stopbtn();
				}
				break;
			case R.id.btn_no:
				// if (mRecorder != null) {
				// mRecorder.release();
				// }
				exit();
				finish();
				break;
			case R.id.btn_yes:
				exit();
				// 确认了之后就可以上传，或者是保存下来了，
				Intent mintent = this.getIntent();
				mintent.putExtra("videoStr", videoStr);// 录制的视频的路径
				setResult(Activity.RESULT_OK, mintent);
				finish();
				break;
			default:
				break;
		}
	}

	public void exit() {
		if(flagTime == "start"){//在录制进行中强制退出了---先停止录制视频
			stopbtn();
		}else if(flagTime == "over"){//在录制完成了正常退出了---正常退出
		}else{
		}
		try {
			mRecorder.stopPreview();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 开始录像的按钮
	private void startbtn() {
		// TODO Auto-generated method stub
		// 计时器
		timeSize = 0;
		flagTime = "start";
		timer = new Timer();
		if (timer != null) {
			if (task != null) {
				task.cancel();
			}
			task = new TimerTask() {
				public void run() {
					timeSize++;
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);

				}
			};
		}
		timer.schedule(task, 0, 1000); // 延时1000ms后执行，1000ms执行一次
		mRecorder.startRecording(TakeVideoActivity.this, surfaceView);
		mRecorder.isRecording = true;
		btn_no.setVisibility(View.GONE);
		btnRecoder.setVisibility(View.VISIBLE);
		btnRecoder.setBackgroundResource(R.drawable.camera_3);
	}

	// 停止摄像的按钮
	private void stopbtn() {
		// TODO Auto-generated method stub
		flagTime = "over";
		timer = new Timer();
		if (timer != null) {
			if (task != null) {
				task.cancel();
			}
			task = new TimerTask() {
				public void run() {
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);

				}
			};
		}
		timer.schedule(task, 0, 1000); // 延时1000ms后执行，1000ms执行一次
		videoStr = mRecorder.stopRecording();
		mRecorder.isRecording = false;
		btn_no.setVisibility(View.VISIBLE);
		btn_yes.setVisibility(View.VISIBLE);
		btnRecoder.setBackgroundResource(R.drawable.camera_1);
	}

	/* 刷新状态 */
	protected void refreshViewByRecordingState() {
		if (mRecorder.isRecording) {// 正在录像的界面
			mRecorder.isRecording = true;
			btn_no.setVisibility(View.GONE);
			btnRecoder.setVisibility(View.VISIBLE);
			btnRecoder.setBackgroundResource(R.drawable.camera_3);
		} else {// 正在停止录像的界面
			mRecorder.isRecording = false;
			btnRecoder.setVisibility(View.VISIBLE);
			btn_no.setVisibility(View.VISIBLE);
			btnRecoder.setBackgroundResource(R.drawable.camera_1);
		}
	}

}
