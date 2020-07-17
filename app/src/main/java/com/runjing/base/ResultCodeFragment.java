package com.runjing.base;

import android.content.Intent;
import android.renderscript.Sampler;

import com.runjing.common.Appconfig;


/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：应用Fragment抽象基础类
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public abstract class ResultCodeFragment extends TitleBarFragment {


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            onCodeResult((Sampler.Value) data.getSerializableExtra(Appconfig.TAG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param value
     */
    public void onCodeResult(Sampler.Value value) {
    }
}
