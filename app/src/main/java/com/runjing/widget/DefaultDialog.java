package com.runjing.widget;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.runjing.common.Appconfig;
import com.runjing.wineworld.R;

/**
 * 默认 dialog
 * 
 * @author zm
 * 
 */
public class DefaultDialog extends Dialog {

	private WindowManager.LayoutParams params;// 外框params
	private View dialogXML;// dialog的xml布局

	private OnLeftListener LeftListener = null;
	private OnRightListener rightListener = null;
	protected TextView titleTv;
	// private RelativeLayout contentRly;//内容layout
	protected TextView leftBtn;
	protected TextView rightBtn;// 右侧按钮
	private ProgressBar bar;// 进度条
	private TextView progressTv;// 文件下载进度
	private TextView fileSizeTv;// 文件大小
	private TextView showTxtTv; // 文本dialog 文本view
	private ImageView iLine;// 竖线
	private ImageView dialog_bottom_line_imv;//横线
	private Activity mContext;

	public DefaultDialog(Activity context) {
		super(context);
		this.mContext = context;
		dialogXML = View.inflate(context, R.layout.upgrade_dialog, null);
		initView();
	}

	/**
	 * 初始化控件
	 */
	public void initView() {
		titleTv = (TextView) findView(R.id.dialog_title_tv);
		leftBtn = (TextView) findView(R.id.dialog_button_left);
		rightBtn = (TextView) findView(R.id.dialog_button_right);
		iLine = (ImageView) findView(R.id.im_iline);
		dialog_bottom_line_imv = (ImageView) findView(R.id.dialog_bottom_line_imv);
		bar = (ProgressBar) findView(R.id.dialog_progress_bar);
		bar.setProgress(0);

		// ----------------------------------添加progressbar的进度图片
		int r = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6,
				getContext().getResources().getDisplayMetrics());
		float[] outerR = new float[] { r, r, r, r, r, r, r, r };
		RoundRectShape rr = new RoundRectShape(outerR, null, null);
		ShapeDrawable drawableBgProgress = new ShapeDrawable();// 创建圆角的drawable
		drawableBgProgress.setShape(rr);
		drawableBgProgress.getPaint().setColor(
				getContext().getResources().getColor(R.color.color_e73828));
		ShapeDrawable drawableBg = new ShapeDrawable();
		drawableBg.setShape(rr);
		drawableBg.getPaint().setColor(
				getContext().getResources().getColor(R.color.color_f2f2f2));
		Drawable layers[] = {
				drawableBg,
				new ClipDrawable(drawableBgProgress, Gravity.LEFT,
						ClipDrawable.HORIZONTAL) };
		LayerDrawable progressDrawable = new LayerDrawable(layers);
		progressDrawable.setId(0, android.R.id.background);
		progressDrawable.setId(1, android.R.id.progress);
		bar.setProgressDrawable(progressDrawable);
		// -----------------------------------------------------------------

		progressTv = (TextView) findView(R.id.dialog_content_planTv);
		progressTv.setText("0%");
		fileSizeTv = (TextView) findView(R.id.file_size_tv);
		showTxtTv = (TextView) findView(R.id.dialog_showTxt_tv);
	}

	public void setTitleValue(String value) {
		titleTv.setText(value);
	}

	/**
	 * 将progressbar dialog 转变为text dialog
	 * 
	 * @param showText
	 *            显示文本
	 */
	public void changeTextDialog(String showText) {
		bar.setVisibility(View.GONE);
		progressTv.setVisibility(View.GONE);
		fileSizeTv.setVisibility(View.GONE);
		showTxtTv.setVisibility(View.VISIBLE);
		showTxtTv.setText(showText);
	}

	public void setLeftBtnValue(String value) {
		leftBtn.setText(value);
	}

	public void setRightBtnValue(String value) {
		rightBtn.setText(value);
	}

	public void btnAlone() {
		leftBtn.setVisibility(View.GONE);
		iLine.setVisibility(View.GONE);
	}
	
	public void rightbtnAlone(){
		rightBtn.setVisibility(View.GONE);
		dialog_bottom_line_imv.setVisibility(View.GONE);
	}

	/**
	 * 将text dialog 转化为默认的progressbar dialog
	 * 
	 * @param barProgress
	 *            progressbar 的进度值
	 * @param textProgress
	 *            下载比例值
	 * @param fileSizeValue
	 *            下载文件大小值
	 */
	public void changeToDefault(String barProgress, String textProgress,
                                String fileSizeValue) {
		bar.setVisibility(View.VISIBLE);
		progressTv.setVisibility(View.VISIBLE);
		fileSizeTv.setVisibility(View.VISIBLE);
		bar.setProgress(Integer.valueOf(barProgress));
		progressTv.setText(textProgress);
		fileSizeTv.setText(fileSizeValue);
	}

	/**
	 * 设置progressbar 的进度值
	 * 
	 * @param value
	 */
	public void setProgressBarValue(int value) {
		bar.setProgress(value);
	}

	/**
	 * 设置text 进度值
	 * 
	 * @param value
	 */
	public void setProgressTvValue(String value) {
		Log.d("dialog", value);
		progressTv.setText(value);
	}

	/**
	 * 设置已下载量
	 * 
	 * @param value
	 */
	public void setFileSizeDownload(String value) {
		fileSizeTv.setText(value);
	}

	/**
	 * 初始化view
	 * 
	 * @param id
	 * @return
	 */
	public View findView(int id) {
		return dialogXML.findViewById(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标头
		getWindow().getDecorView().getBackground().setAlpha(0);// 设置透明度
		params = getWindow().getAttributes();// 获取params
		getWindow().setContentView(dialogXML);// 加载xml
		getWindow().setAttributes(
				(WindowManager.LayoutParams) params);
		// 获得屏幕宽
		params.height = LayoutParams.WRAP_CONTENT;
		params.width = LayoutParams.WRAP_CONTENT;

		rightBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (rightListener != null) {
					rightListener.onRightListener();
				}
				cancel();
			}
		});
		leftBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (LeftListener != null) {
					LeftListener.onLeftListener();
				}
				cancel();
			}
		});
	}

	/**
	 * 显示 text形式的dialog
	 */
	public void showTextDialog(String text, String explain) {
		bar.setVisibility(View.GONE);
		progressTv.setVisibility(View.GONE);
		fileSizeTv.setVisibility(View.GONE);
		showTxtTv.setVisibility(View.VISIBLE);
		showTxtTv.setText(text + "\n" + explain);
	}

	/**
	 * dialog左侧按钮监听
	 * 
	 * @author dingxuewei
	 * 
	 */
	public interface OnLeftListener {
		public void onLeftListener();
	}

	/**
	 * dialog 右侧按钮监听
	 * 
	 * @author dingxuewei
	 * 
	 */
	public interface OnRightListener {
		public void onRightListener();
	}

	/**
	 * 右按钮的listener
	 */
	public void setRightButtonListener(OnRightListener onRightListener) {
		rightListener = onRightListener;

	}

	/**
	 * 左按钮的listener
	 */
	public void setLeftButtonLintener(OnLeftListener onLeftListener) {
		LeftListener = onLeftListener;

	}

	/**
	 * 设置只有一个按钮
	 */
	public void OnlyButton() {
		leftBtn.setVisibility(View.GONE);
		iLine.setVisibility(View.GONE);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (Appconfig.isFource != null && !Appconfig.isFource.equals("")) {
				if (Appconfig.isFource.equals("T")) {
//					for (Activity activity : AppContext.activityList) {
//						activity.finish();
//					}
//					AppContext.activityList.clear();
					System.exit(0);
				} else {
					this.dismiss();
				}

			} else {
				this.dismiss();
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);

	}

}
