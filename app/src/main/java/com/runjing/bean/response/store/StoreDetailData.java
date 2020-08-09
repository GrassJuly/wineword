package com.runjing.bean.response.store;

import com.runjing.bean.response.home.GoodBean;

/**
 * @Created: qianxs  on 2020.08.03 19:55.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.08.03 19:55.
 * @Remark:
 */
public class StoreDetailData {

    private DetailStroeBean detailStroe;
    private GoodBean detailGood;

    public StoreDetailData(DetailStroeBean detailStroe, GoodBean detailGood) {
        this.detailStroe = detailStroe;
        this.detailGood = detailGood;
    }

    public DetailStroeBean getDetailStroe() {
        return detailStroe;
    }

    public void setDetailStroe(DetailStroeBean detailStroe) {
        this.detailStroe = detailStroe;
    }

    public GoodBean getDetailGood() {
        return detailGood;
    }

    public void setDetailGood(GoodBean detailGood) {
        this.detailGood = detailGood;
    }

    @Override
    public String toString() {
        return "StoreDetailData{" +
                "detailStroe=" + detailStroe +
                ", detailGood=" + detailGood +
                '}';
    }
}
