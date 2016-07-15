package com.yjbo.yjboandroidmodule.util.video;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

import com.yjbo.yjboandroidmodule.util.ComApplicaUtil;


public class MovieRecorder implements
		MediaRecorder.OnErrorListener, PreviewCallback {
	private MediaRecorder mediarecorder;
	boolean isRecording;
	String imagePathAllUse = ComApplicaUtil.Voide_SAVEPATH();
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

	/** 开始预览 */
	public void startPreview(Activity mactivity, SurfaceHolder mSurfaceHolder) {

		try {
			try {
				camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);// 后置摄像头
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				camera = Camera.open(Camera.getNumberOfCameras() - 1);// 前置摄像头
			}
			camera.setDisplayOrientation(90);
			try {
				camera.setPreviewDisplay(mSurfaceHolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 设置摄像头参数
			mParameters = camera.getParameters();
			mSupportedPreviewSizes = mParameters.getSupportedPreviewSizes();// 获取支持的尺寸
			// prepareCameraParaments();
			camera.setParameters(mParameters);
			setPreviewCallback(mactivity);
			camera.startPreview();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 设置回调 */
	protected void setPreviewCallback(Activity mactivity) {
		Size size = mParameters.getPreviewSize();
		if (size != null) {
			PixelFormat pf = new PixelFormat();
			PixelFormat.getPixelFormatInfo(mParameters.getPreviewFormat(), pf);
			int buffSize = size.width * size.height * pf.bitsPerPixel / 8;
			try {
				camera.addCallbackBuffer(new byte[buffSize]);
				camera.addCallbackBuffer(new byte[buffSize]);
				camera.addCallbackBuffer(new byte[buffSize]);
				camera.setPreviewCallbackWithBuffer(this);
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
			}
			// startPreview...setPreviewCallbackWithBuffer...width:640
			// height:480
		} else {
			camera.setPreviewCallback(this);
		}
	}

	/** 停止预览 */
	public void stopPreview() {
		if (camera != null) {
			try {
				camera.stopPreview();
				camera.setPreviewCallback(null);
				camera.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
			camera = null;
		}
	}

	/*** 开始录制 */
	public void startRecording(Activity mactivity, SurfaceView surfaceView) {
		if (mediarecorder == null) {
			mediarecorder = new MediaRecorder();
			mediarecorder.setOnErrorListener(this);
		} else {
			try {
				mediarecorder.reset();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		camera.unlock();
		mediarecorder.setCamera(camera);
		mediarecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
		mediarecorder.setOrientationHint(90);
		// 设置录制视频源为Camera(相机)
		mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4
		mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		// 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
		// 设置录制视频的最长时间，单位是ms（毫秒）
		mediarecorder.setMaxDuration(30 * 1000);
		// 设置录制视频的最大的大小，单位是long型（L）
		mediarecorder.setMaxFileSize(20 * 1024 * 1024L);
		mediarecorder.setVideoSize(160, 120);// width:640 height:480
		mediarecorder.setVideoEncodingBitRate(2 * 1024 * 1024);// 设置越大，越清楚，占的内存越大
		mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		// 设置录制的视频编码h263 h264 MPEG_4_SP
		mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		mediarecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
		timeStr = dateFormat.format(new Date());
		// 设置视频文件输出的路径
		lastFileName = imagePathAllUse + timeStr + ".mp4";
		mediarecorder.setOutputFile(lastFileName);
		try {
			// 准备录制
			mediarecorder.prepare();
			// 开始录制
			mediarecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRecording = true;
	}

	private String lastFileName;

	/*** 停止录制 */
	public String stopRecording() {
		String newPath = "";
		try {
			if (mediarecorder != null) {
				try {
					// 设置后不会崩
					mediarecorder.setOnErrorListener(null);
					mediarecorder.setPreviewDisplay(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					// 停止
					mediarecorder.stop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (null != lastFileName && !"".equals(lastFileName)) {
					File f = new File(lastFileName);
					String name = f.getName().substring(0,
							f.getName().lastIndexOf(".mp4"));
					name += "_" + "video.mp4";
					newPath = f.getParentFile().getAbsolutePath() + "/" + name;
					if (f.renameTo(new File(newPath))) {
						int i = 0;
						i++;
					}

				}
			}
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (camera != null) {
			try {
				camera.lock();
				Log.e("yjb", "stopRecord-camera -已经停止-camera.lock();");
				// camera.release();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		/***释放资源*/
		release();

		return newPath;
	}

	/** 释放资源 */
	public void release() {
		if (mediarecorder != null) {
			mediarecorder.setOnErrorListener(null);
			try {
				mediarecorder.release();
			} catch (IllegalStateException e) {
				Log.w("Yixia", "stopRecord", e);
			} catch (Exception e) {
				Log.w("Yixia", "stopRecord", e);
			}
		}
		mediarecorder = null;
	}

	@Override
	public void onError(MediaRecorder mr, int what, int extra) {
		try {
			if (mr != null)
				mr.reset();
		} catch (IllegalStateException e) {
			Log.w("Yixia", "stopRecord", e);
		} catch (Exception e) {
			Log.w("Yixia", "stopRecord", e);
		}
	}


	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		mPreviewFrameCallCount++;
		camera.addCallbackBuffer(data);
	}
}
