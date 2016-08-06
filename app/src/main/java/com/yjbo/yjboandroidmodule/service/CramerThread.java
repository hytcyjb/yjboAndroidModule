package com.yjbo.yjboandroidmodule.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yjbo.yjboandroidmodule.entity.HomeStatusClass;
import com.yjbo.yjboandroidmodule.entity.MainMessage;

import de.greenrobot.event.EventBus;

public class CramerThread extends Thread {
	private MediaRecorder mediarecorder;// 录制视频的类private long
	public static SurfaceHolder surfaceHolder;
//	private SurfaceView surfaceview;// 显示视频的控件
	private Camera mCamera;
	public static long recordTime;
	private boolean isRecording = false;
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private Context context;
//	private TextView time;
	private long startTime = Long.MIN_VALUE;
	private long endTime = Long.MIN_VALUE;
	private HashMap<String, String> map = new HashMap<String, String>();
	private static final String TAG = "SEDs508EG";
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;

//	public RecordThread() {
//		backStop();
//	}


//	public CramerThread(long recordTime, SurfaceView surfaceview,
//						SurfaceHolder surfaceHolder, Context context, TextView time) {
//		this.recordTime = recordTime;
//		this.surfaceview = surfaceview;
//		this.surfaceHolder = surfaceHolder;
//		this.context = context;
//		this.time = time;
//	}
	public CramerThread( Context context) {
//		this.recordTime = recordTime;
//		this.surfaceview = surfaceview;
//		this.surfaceHolder = surfaceHolder;
		this.context = context;
//		this.time = time;
	}

	@Override
	public void run() {
		/** * 开始录像 */
		startRecord();
		/** * 启动定时器，到规定时间recordTime后执行停止录像任务 */
		Timer timer = new Timer();
		timer.schedule(new TimerThread(), recordTime);
	}

	/** * 获取摄像头实例对象 * * @return */
	public Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open();
		} catch (Exception e) {
			// 打开摄像头错误
			Log.i("info", "打开摄像头错误");
		}
		return c;
	}

	/** * 开始录像 */
	public void startRecord() {
		mediarecorder = new MediaRecorder();// 创建mediarecorder对象
		mCamera = getCameraInstance(); // 解锁camera
		mCamera.unlock();
		// if (isRecording == true) {
		// // 停止录像并释放Camera
		// mediarecorder.stop();
		// releaseMediaRecorder();
		// // 将控制权由MediaRecorder交回Camera
		// mCamera.lock();
		// isRecording = false;
		// } else if(isRecording == false){
		// // Camera已可用并解锁，MediaReocrder已就绪，现在可以开始录像
		// mediarecorder.start();
		// mCamera.unlock();
		// isRecording = true;
		// releaseMediaRecorder();
		// }
		mediarecorder.setCamera(mCamera); // 设置录制视频源为Camera(相机)
		mediarecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); // 设置录制文件质量，格式，分辨率之类，这个全部包括了
		mediarecorder.setProfile(CamcorderProfile
				.get(CamcorderProfile.QUALITY_LOW));
		mediarecorder.setVideoSize(160, 120);// width:640 height:480
		mediarecorder.setVideoEncodingBitRate(2 * 1024 * 1024);// 设置越大，越清楚，占的内存越大
//		mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		// 设置录制的视频编码h263 h264 MPEG_4_SP
//		mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		mediarecorder.setPreviewDisplay(surfaceHolder.getSurface()); // 设置视频文件输出的路径
		// mediarecorder.setOutputFile("/sdcard/sForm.3gp");
		mediarecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO)
				.toString());
		try { // 准备录制
			mediarecorder.prepare(); // 开始录制
			mediarecorder.start();
			// time.setVisibility(View.VISIBLE);// 设置录制时间显示
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void releaseMediaRecorder() {
		if (mediarecorder != null) {
			// 清除recorder配置
			mediarecorder.reset();
			// 释放recorder对象
			mediarecorder.release();
			mediarecorder = null;
			// 为后续使用锁定摄像头
			mCamera.lock();
		}
	}

	/** * 停止录制 */
	public void stopRecord() {
		System.out.println("--------------");

		if (mediarecorder != null) {
			// 停止录制
			mediarecorder.stop();
//			 time.setText(format(hour) + ":" + format(minute) + ":"
//			 + format(second));
			// 释放资源
			mediarecorder.release();
			mediarecorder = null;
			if (mCamera != null) {
				mCamera.release();
				mCamera = null;
			}
			EventBus.getDefault().post(new MainMessage("继续录像"));
//			SurfaceView surfaceview = new SurfaceView(context);
//			surfaceHolder = surfaceview.getHolder();
//			Toast.makeText(context, "开始录像", Toast.LENGTH_SHORT).show();
//			run();
//			CramerThread thread = new CramerThread(context);
//			thread.start();
		}
	}

	/**
	 * 格式化时间
	 */
	public String format(int i) {
		String s = i + "";
		if (s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}

	/** * 定时器 * @author bcaiw * */
	class TimerThread extends TimerTask {
		/** * 停止录像 */
		@Override
		public void run() {
			try {
				stopRecord();
				this.cancel();

			} catch (Exception e) {
				map.clear();
				map.put("recordingFlag", "false");
				String ac_time = getVedioRecordTime();// 录像时间
				map.put("recordTime", ac_time);
				// sendMsgToHandle(m_msgHandler, iType, map);
			}
			// stopRecord();
			// this.cancel();
			// Intent intent = new Intent(context,OneActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			// // intent.setClassName(context,
			// "com.example.camera.OneActivity");
			// context.startActivity(intent);

		}
	}

	/**
	 * 通用方法，接收多线程过来的数据，有可能不仅仅是msg，所以定义map对象
	 *
	 * @param handle
	 * @param iType
	 * @param info
	 */
	public void sendMsgToHandle(Handler handle, int iType,
								Map<String, String> info) {
		Message threadMsg = handle.obtainMessage();
		threadMsg.what = iType;
		Bundle threadbundle = new Bundle();
		threadbundle.clear();
		for (Iterator i = info.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			threadbundle.putString(obj.toString(), info.get(obj));
		}
		threadMsg.setData(threadbundle);
		handle.sendMessage(threadMsg);

	}

	/**
	 * 计算当前已录像时间，默认值返回0
	 *
	 * @return
	 */
	public String getVedioRecordTime() {
		String result = "0";
		if (startTime != Long.MIN_VALUE && endTime != Long.MIN_VALUE) {
			long tempTime = (endTime - startTime);
			result = String.valueOf(tempTime);
		}
		return result;

	}

	public void backStop(){
		mediarecorder.stop();
	}
	private static File getOutputMediaFile(int type) {
		// 判断SDCard是否存在
		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			Log.d(TAG, "SDCard不存在");
			return null;
		}

		// File mediaStorageDir = new
		// File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
		// "MyCameraApp");
		// 如果期望图片在应用程序卸载后还存在，且能被其他应用程序共享，则此保存位置最合适
		// 如果不存在的话，则创建存储目录
		File mediaStorageDir = new File(
				Environment.getExternalStorageDirectory() + File.separator
						+ "/MyXingCheCamera3GP/");
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdir()) {
				Log.d(TAG, "failed to create directory");
				return null;
			}
		}
		// 创建媒体文件名
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timestamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timestamp + ".3gp");
		} else {
			Log.d(TAG, "文件类型有误");
			return null;
		}

		return mediaFile;
	}
}
