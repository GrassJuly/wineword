package com.runjing.widget.seckill;

import android.content.Context;
import android.util.AttributeSet;

import com.runjing.widget.seckill.base.BaseCountDownTimerView;

public class SecondDownTimerView extends BaseCountDownTimerView {

	public SecondDownTimerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SecondDownTimerView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public SecondDownTimerView(Context context) {
		this(context,null);
	}

	@Override
	protected String getStrokeColor() {
		return "FFF80000";
	}

	@Override
	protected String getTextColor() {
		return "FFFFFFFF";
	}

	@Override
	protected int getCornerRadius() {
		return 4;
	}

	@Override
	protected int getTextSize() {
		return 11;
	}

	@Override
	protected String getBackgroundColor() {
		return "FFF80000";
	}
	
}
