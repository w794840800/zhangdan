package com.beidou.ybz.accountbook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.beidou.ybz.accountbook.R;

import java.lang.reflect.Method;

public class EmptyView extends RelativeLayout {
	private String mLoadingText;
	private ImageView ivEmpty;
	private TextView tvReload;
	private View mBindView;
	private int width;
	private int height;
	private CharSequence emptyText;
	ImageView pro;
	ProgressBar mProgressBar;
	private TextView tv,tvOperational;
	public EmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, 0, 0);
		String text = ta.getString(R.styleable.EmptyView_android_text);
		String buttonText = ta.getString(R.styleable.EmptyView_buttonText);
		mLoadingText = ta.getString(R.styleable.EmptyView_loadingText);
		ta.recycle();
		init(context,text, buttonText);
	}


	private void init(Context context,String text, String buttonText) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
		Log.i("系统信息", "该设备的分辨是：" + width + "*" + height);//480*854
		
		if(TextUtils.isEmpty(text)) text = "暂无数据";
		if(TextUtils.isEmpty(buttonText)) buttonText = "点击任意位置重新加载";
		if(TextUtils.isEmpty(mLoadingText)) mLoadingText = "加载中...";


		/**
		 * 进度条  屏幕中间
		 */
		LayoutParams monIndicatorParams = new LayoutParams(200, LayoutParams.WRAP_CONTENT);
//		monIndicatorParams.addRule(RelativeLayout.CENTER_IN_PARENT);//整体居中
		monIndicatorParams.addRule(RelativeLayout.CENTER_HORIZONTAL);//整体居顶
		monIndicatorParams.setMargins(0, 160, 0, 0);
		pro = new ImageView(getContext());
		pro.setVisibility(View.VISIBLE);
		pro.setAdjustViewBounds(true);
		pro.setImageResource(R.drawable.empty_icon);
		pro.setId(R.id.id_empty_image);
		addView(pro,monIndicatorParams);


		/**
		 * 加载中/暂无数据/加载失败
		 */
		tv = new TextView(getContext());
		tv.setTextColor(getResources().getColor(R.color.button_text_unable));
		tv.setTextSize(16);
		tv.setText("正在加载中...");
		LayoutParams textViewParams = null;
		textViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		textViewParams.addRule(RelativeLayout.BELOW,R.id.id_empty_image);
		textViewParams.setMargins(0, 30, 0, 0);
		tv.setId(R.id.id_empty_text);
		addView(tv, textViewParams);

		//运营文案
		tvOperational = new TextView(getContext());
		tvOperational.setTextColor(getResources().getColor(R.color.button_unable));
		tvOperational.setTextSize(12);
		tvOperational.setText("添加后让你更了解自己的资产");
		LayoutParams operationalParams = null;
		operationalParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		operationalParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		operationalParams.addRule(RelativeLayout.BELOW,R.id.id_empty_text);
		operationalParams.setMargins(0, 20, 0, 0);
		addView(tvOperational, operationalParams);

		mProgressBar = new ProgressBar(getContext(),null,android.R.attr.progressBarStyleSmall);
		LayoutParams params = null;
		params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.LEFT_OF,R.id.id_empty_text);
//		params.addRule(RelativeLayout.ALIGN_BASELINE,R.id.id_empty_text);
		params.addRule(RelativeLayout.BELOW,R.id.id_empty_image);
		params.setMargins(0, 34, 16, 0);
		addView(mProgressBar, params);



	}
	
	public void bindView(View view) {
		mBindView = view;
	}

	public void setEmptyText(CharSequence empty){
		emptyText = empty;
	}
	
	public void loading() {
		if(mBindView != null) mBindView.setVisibility(View.GONE);
		tv.setText("正在加载中...");
		setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.VISIBLE);
		tvOperational.setVisibility(View.GONE);
		pro.setVisibility(View.INVISIBLE);
	}
	
	public void success() {
		setVisibility(View.GONE);
		if(mBindView != null) mBindView.setVisibility(View.VISIBLE);
	}
	
	public void empty() {
		if(mBindView != null) mBindView.setVisibility(View.GONE);
		if(emptyText == null || TextUtils.isEmpty(emptyText)){
			tv.setText("暂无数据，请点击重试");
		}else{
			tv.setText(emptyText);
		}

		setVisibility(View.VISIBLE);
		pro.setVisibility(View.VISIBLE);
		tvOperational.setVisibility(View.VISIBLE);
		setClickable(true);
		pro.setImageResource(R.drawable.empty_icon);
		mProgressBar.setVisibility(View.INVISIBLE);
	}

	public void assetsAddAll() {
		if(mBindView != null) mBindView.setVisibility(View.GONE);
		if(emptyText == null || TextUtils.isEmpty(emptyText)){
			tv.setText("暂无数据，请点击重试");
		}else{
			tv.setText(emptyText);
		}

		setVisibility(View.VISIBLE);
		tvOperational.setVisibility(View.INVISIBLE);
		setClickable(false);
		pro.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		pro.setImageResource(R.drawable.addall);
	}
	
	public void loadfail() {
		if(mBindView != null) mBindView.setVisibility(View.GONE);
		tv.setText("加载失败，请点击重试");
		setVisibility(View.VISIBLE);
		setClickable(true);
		pro.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		tvOperational.setVisibility(View.GONE);
	}

	public void netless() {
//		shimmerFrameLayout.stopShimmerAnimation();
		if(mBindView != null) mBindView.setVisibility(View.GONE);
		setVisibility(View.VISIBLE);
		setClickable(true);
		tv.setVisibility(View.VISIBLE);//+++++++++++++++++++++++
		tv.setText("网络连接失败，请检查网络设置");
		pro.setVisibility(View.VISIBLE);//+++++++++++++++++++++++
		mProgressBar.setVisibility(View.INVISIBLE);
		tvOperational.setVisibility(View.GONE);
	}

	public void loadFailWithMsg(String data) {
		if(mBindView != null) mBindView.setVisibility(View.GONE);
		setVisibility(View.VISIBLE);
		tv.setVisibility(View.VISIBLE);//+++++++++++++++++++++++
		tv.setText(data);
		ivEmpty.setVisibility(View.INVISIBLE);
		tvReload.setClickable(true);
		setClickable(true);
		pro.setVisibility(View.INVISIBLE);//+++++++++++++++++++++++
		tvReload.setVisibility(View.INVISIBLE);
	}
	

	
	
	 public void buttonClick(final Object base, final String method,
	            final Object... parameters) {
	        this.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                int length = parameters.length;
	                Class<?>[] paramsTypes = new Class<?>[length];
	                for (int i = 0; i < length; i++) {
	                    paramsTypes[i] = parameters[i].getClass();
	                }
	                try {
	                    Method m = base.getClass().getDeclaredMethod(method, paramsTypes);
	                    m.setAccessible(true);
	                    m.invoke(base, parameters);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	        
//	        tvReload.setOnClickListener(new OnClickListener() {
//	            public void onClick(View v) {
//
//					Intent ins = new Intent(getContext(), MainActivity.class);
//					ins.putExtra("flag","miche");
//					getContext().startActivity(ins);
//					try {
//						ActivityUtils.finishActivity((Activity)getContext());
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//	        });
	}
}
