package com.runjing.utils;

import android.widget.TextView;

import com.runjing.utils.time.CountDownTimerUtil;
import com.runjing.wineworld.R;

/**
 * @Created: qianxs  on 2020.07.21 12:39.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.07.21 12:39.
 * @Remark:
 */
public class TimerCount extends CountDownTimerUtil {
    private TextView tv;

    /**
     *  * Instantiates a new Timer count.
     *  *
     *  * @param millisInFuture the millis in future
     *  * @param countDownInterval the count down interval
     *  * @param bnt      the bnt
     *  
     */
    public TimerCount(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }


    /**
     *  * Instantiates a new Timer count.
     *  *
     *  * @param millisInFuture the millis in future
     *  * @param countDownInterval the count down interval
     *  
     */
    public TimerCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onFinish() {

        tv.setClickable(true);
        tv.setTextColor(tv.getContext().getResources().getColor(R.color.color_F80000));
        tv.setText("获取动态码");

    }

    @Override
    public void onTick(long arg0) {
        // TODO Auto-generated method stub
        tv.setClickable(false);
        tv.setText(arg0 / 1000 + "s 后重新发送");
        tv.setTextColor(tv.getContext().getResources().getColor(R.color.color_999999));
    }
}
