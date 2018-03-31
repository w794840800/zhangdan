package com.beidou.ybz.accountbook.widget.morewindow;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ShareModel;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.File;
import java.util.ArrayList;

public class MoreWindow extends PopupWindow implements OnClickListener{
	private String TAG = MoreWindow.class.getSimpleName();
	Activity mContext;
	private int mWidth;
	private int mHeight;
	private int statusBarHeight;
	private Bitmap mBitmap= null;
	private Bitmap overlay = null;
	ImageView close = null;//,iv_top
	RelativeLayout rll;
	private Handler mHandler = new Handler();
	RelativeLayout layout = null;
	private String shareType;
	private ShareModel mShareModel;
	private String title,imgurl,outerContent,htmlurl;
	private final String SD_PATH = Environment.getExternalStorageDirectory().getPath()+"/ceb";
	private boolean isImageShare;//true-纯图片分享 false-链接分享
	/**
	 *
	 * @param context 上下文环境
	 * @param shareType "img"-纯图片分享  "web"-链接分享
	 * @param shareModel
	 */
	public MoreWindow(Activity context , String shareType, ShareModel shareModel) {
		mContext = context;
		this.shareType = shareType;
		if(shareModel != null){
			mShareModel = shareModel;
			title = mShareModel.getTitle();
			imgurl = mShareModel.getImgurl();
			outerContent = mShareModel.getContent();
			htmlurl = mShareModel.getHtmlurl();

			if(TextUtils.isEmpty(imgurl)){
				imgurl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515859586886&di=90aa207daed8f6e3eedd9ae6127b1211&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Ffc74a8ded8e1e0d25c9a115556fc5a9e22b28b6f.jpg";
				//http://api.baichanghaoche.com/Public/Api/images/logo.png";
			}

//			title="有本账1.0上线啦";
//			outerContent="哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
//			htmlurl="https://www.baidu.com";
		}
	}

	//分享
	private Tencent mTencent;
	String mAppid = "101448247";//腾讯开发平台
	private final String APP_ID = "wx47aab94a860b83ed";//微信
	private IWXAPI api;
	public void init() {
		Rect frame = new Rect();
		mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		statusBarHeight = frame.top;
		DisplayMetrics metrics = new DisplayMetrics();
		mContext.getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		mWidth = metrics.widthPixels;
		mHeight = metrics.heightPixels;
		
		setWidth(mWidth);
		setHeight(mHeight);

		mTencent = Tencent.createInstance(mAppid, mContext.getApplicationContext());
		api = WXAPIFactory.createWXAPI(mContext, APP_ID, true);
	}

	public void showMoreWindow(View anchor,int bottomMargin) {
		layout = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.share_more_window, null);
		setContentView(layout);
		close= (ImageView)layout.findViewById(R.id.iv_close);
		rll = (RelativeLayout) layout.findViewById(R.id.rll);
		if(shareType != null && shareType.equals("img")){
			isImageShare = true;
		}else{
			isImageShare = false;
		}

		RelativeLayout.LayoutParams params =new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.bottomMargin = bottomMargin;
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isShowing()) {
					closeAnimation(layout);
				}
			}

		});
		
		showAnimation(layout);
		setOutsideTouchable(true);
		setFocusable(true);
		showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);


	}

	private void showAnimation(ViewGroup layout){
		for(int i=0;i<layout.getChildCount();i++){
			final View child = layout.getChildAt(i);
			if(child.getId() == R.id.iv_close){
				continue;
			}
			child.setOnClickListener(this);
			child.setVisibility(View.INVISIBLE);
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					child.setVisibility(View.VISIBLE);
					ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 300, 0);
					fadeAnim.setDuration(800);
					fadeAnim.setInterpolator(new SpringInterpolator());
					KickBackAnimator kickAnimator = new KickBackAnimator();//�ص�����
					kickAnimator.setDuration(150);
//					fadeAnim.setEvaluator(kickAnimator);
					
//					ElasticEaseOut easeOut = new ElasticEaseOut(1500);
//					fadeAnim.setEvaluator(easeOut);
					
					fadeAnim.start();
				}
			}, i * 100);
		}

		ObjectAnimator animator1 = ObjectAnimator.ofFloat(close,"alpha", 0f, 0.6f);
		animator1.setDuration(700).start();

		ObjectAnimator.ofFloat(close,"rotation", 0, 180).setDuration(700).start();

//		ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv_top,"alpha", 0f, 1f);
//		animator2.setDuration(1000).start();
		ObjectAnimator.ofFloat(rll,"alpha", 0f, 1f).setDuration(600).start();
		
	}

	private void closeAnimation(ViewGroup layout){
		ObjectAnimator animator1 = ObjectAnimator.ofFloat(close,"alpha", 0.6f, 0f);
		animator1.setDuration(200).start();
		ObjectAnimator.ofFloat(close,"rotation", 180, 0).setDuration(700).start();
//		ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv_top,"alpha", 1f, 0f);
//		animator2.setDuration(200).start();
		ObjectAnimator.ofFloat(rll,"alpha", 1f, 0f).setDuration(600).start();

		for(int i=0;i<layout.getChildCount();i++){
			final View child = layout.getChildAt(i);
			if(child.getId() == R.id.iv_close){
				continue;
			}
			child.setOnClickListener(this);
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					child.setVisibility(View.VISIBLE);
					ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 900);
					fadeAnim.setDuration(200);
					KickBackAnimator kickAnimator = new KickBackAnimator();
					kickAnimator.setDuration(100);
					fadeAnim.setEvaluator(kickAnimator);
					fadeAnim.start();
					fadeAnim.addListener(new AnimatorListener() {
						@Override
						public void onAnimationStart(Animator animation) {}
						@Override
						public void onAnimationRepeat(Animator animation) {}
						@Override
						public void onAnimationEnd(Animator animation) {
							child.setVisibility(View.INVISIBLE);

//							destroy();
						}

						@Override
						public void onAnimationCancel(Animator animation) {}
					});
				}
			}, (layout.getChildCount()-i-1) * 30);
			
			if(child.getId() == R.id.tv_shareQQ){
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						dismiss();
					}
				}, (layout.getChildCount()-i) * 30 + 80);
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_wechat:
			if (Utils.isWeChatAppInstalled(mContext)){
				if(isImageShare){
					weiixnImage(mShareModel.getImgurl(),0);
				}else{
					weixin();
				}
				closeAnimation(layout);
			}else {
				Toast.makeText(mContext.getApplicationContext(), "本设备未安装微信，无法使用微信分享功能", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.tv_shareCircle:
			if (Utils.isWeChatAppInstalled(mContext)){
				if(isImageShare){
					weiixnImage(mShareModel.getImgurl(),1);
				}else{
					circle();
				}
				closeAnimation(layout);
			}else {
				Toast.makeText(mContext.getApplicationContext(), "本设备未安装微信，无法使用微信分享功能", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.tv_shareQQ:
			if (Utils.isQQClientAvailable(mContext)){
				if(isImageShare){
					shareQQImage(mShareModel.getImgurl());
				}else{
					shareToQQ();
				}
				closeAnimation(layout);
			}else {
				Toast.makeText(mContext.getApplicationContext(), "本设备未安装QQ，无法使用QQ分享功能", Toast.LENGTH_LONG).show();
			}
			break;
				/*case R.id.tv_sina:
//			weibo();
			LogUtils.loge(SD_PATH);
			weiixnImage(SD_PATH+"/share.jpg",0);
			closeAnimation(layout);
			break;
		case R.id.tv_qzone:
			kongjian();
			closeAnimation(layout);
			break;*/
		/*case R.id.tv_message:
			shareToSms();
			closeAnimation(layout);
			break;*/
		default:
			break;
		}
	}


	/**
	 * 微信分享——纯图片分享
	 * @param path 图片本地路径
	 * @param sendtype (0:分享到微信好友，1：分享到微信朋友圈)
	 */
	private void weiixnImage(String path ,int sendtype){
		int THUMB_SIZE = 150;
		File file = new File(path);
		if (!file.exists()) {
			Toast.makeText(mContext, "图片不存在", Toast.LENGTH_LONG).show();
		}
		WXImageObject imgObj = new WXImageObject();
		imgObj.setImagePath(path);
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = imgObj;
		Bitmap bmp = BitmapFactory.decodeFile(path);
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, (int) (THUMB_SIZE*1.4), true);// int dstWidth, int dstHeight,
		msg.setThumbImage(thumbBmp);
		bmp.recycle();
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		req.scene = sendtype==0?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;

		api.sendReq(req);
	}

	/**
	 * 微信好友分享——链接分享
	 */
	private void weixin() {
		api.registerApp(APP_ID);
		if (!api.isWXAppInstalled()) {
			ToastUtils.toast(mContext,"您还未安装微信客户端");
			return;
		}
			WXWebpageObject webpage = new WXWebpageObject();
			webpage.webpageUrl = htmlurl;
			final WXMediaMessage msg = new WXMediaMessage(webpage);
			msg.title = title;
			msg.description = outerContent;
		LogUtils.loge("imgurl:"+imgurl);
		final Bitmap[] thumb = {null};
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if(imgurl != null) {
                        thumb[0] = Utils.returnBitmap(imgurl);
						Bitmap thumb1 =Bitmap.createScaledBitmap(thumb[0], 120, 120, true);//压缩Bitmap
						thumb[0].recycle();
                        msg.setThumbImage(thumb1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
				SendMessageToWX.Req req = new SendMessageToWX.Req();
					req.transaction = String.valueOf(System.currentTimeMillis());
					req.message = msg;
					req.scene = 0;
					LogUtils.loge("api.sendReq:"+api.sendReq(req));
					api.sendReq(req);

			}
		}).start();
	}

	/**
	 * 微信朋友圈分享—— 链接分享
	 */
	void circle(){
		if (!api.isWXAppInstalled()) {
			ToastUtils.toast(mContext,"您还未安装微信客户端");
			return;
		}
			WXWebpageObject webpage = new WXWebpageObject();
			webpage.webpageUrl = htmlurl;
			final WXMediaMessage msg = new WXMediaMessage(webpage);

			// msg.title = getResources().getString(R.string.fenxiang_title);
			msg.title = title;
			// msg.description = getResources().getString(R.string.fenxiang_text);
			msg.description = outerContent;
		final Bitmap[] thumb = {null};
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if(imgurl != null) {
                        thumb[0] = Utils.returnBitmap(imgurl);
                        LogUtils.loge("thumb == null:" + (thumb[0] == null));
						Bitmap thumb1 =Bitmap.createScaledBitmap(thumb[0], 120, 120, true);//压缩Bitmap
						thumb[0].recycle();
                        msg.setThumbImage(thumb1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
				SendMessageToWX.Req req = new SendMessageToWX.Req();
				req.transaction = String.valueOf(System.currentTimeMillis());
				req.message = msg;
				req.scene = 1;
				LogUtils.loge("api.sendReq:"+api.sendReq(req));
				api.sendReq(req);

			}
		}).start();

	}


	/**
	 * QQWebpage分享
	 */
	void shareToQQ(){
		final Bundle params = new Bundle();
			params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
			params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
			params.putString(QQShare.SHARE_TO_QQ_SUMMARY, outerContent);
			params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  htmlurl);
			params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,imgurl);
				mTencent.shareToQQ(mContext, params,
						new BaseUiListener());
	}


	/**
	 * QQ纯图片分享
	 * @param path 图片路径
	 */
	void shareQQImage(String path){
		Bundle shareParams = new Bundle();
		shareParams.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
				QQShare.SHARE_TO_QQ_TYPE_IMAGE);
		shareParams.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,path);
		shareParams.putString(QQShare.SHARE_TO_QQ_APP_NAME, "LPS CRM");
		shareParams.putInt(QQShare.SHARE_TO_QQ_EXT_INT,
				QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
		mTencent.shareToQQ(mContext, shareParams, new BaseUiListener());
	}



	void kongjian(){
		final Bundle params2 = new Bundle();
			params2.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
			params2.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);
			params2.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, htmlurl);
			params2.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, outerContent);//选填
			ArrayList<String> imageurls = new ArrayList<String>();
			imageurls.add(imgurl);
			params2.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageurls);
			mTencent.shareToQzone(mContext, params2, new BaseUiListener());
	}


	void shareToSms(){
		Uri smsToUri = Uri.parse("smsto:");

		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);

		intent.putExtra("sms_body", outerContent+htmlurl);

		mContext.startActivity(intent);
	}

	private class BaseUiListener implements IUiListener {

		@Override
		public void onCancel() {
			Toast.makeText(mContext.getApplicationContext(), "onCancel", Toast.LENGTH_LONG).show();
		}

		@Override
		public void onComplete(Object arg0) {
			Toast.makeText(mContext.getApplicationContext(), "success", Toast.LENGTH_LONG).show();
		}

		@Override
		public void onError(UiError arg0) {
//            Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_SHORT)
//                    .show();
//            Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_LONG).show();
			Log.e("error", "onError: "+ arg0.toString());
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == Constants.REQUEST_QZONE_SHARE ||
				resultCode == Constants.ACTIVITY_OK) {
			Tencent.onActivityResultData(requestCode,resultCode,data,new BaseUiListener());
		}
//		super.onActivityResult(requestCode, resultCode, data);
	}

	public void destroy() {
		if (null != overlay) {
			overlay.recycle();
			overlay = null;
			System.gc();
		}
		if (null != mBitmap) {
			mBitmap.recycle();
			mBitmap = null;
			System.gc();
		}
	}
	
}
