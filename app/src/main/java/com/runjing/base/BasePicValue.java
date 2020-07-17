package com.runjing.base;


import com.runjing.wineworld.R;

import java.io.Serializable;

/**
 * @Created by xiaoyu on 2016/9/27.
 * @Describe： 用于 图片上传 数值封装
 * @Review by：
 * @Modify by：
 * @Version : ${ v_ 2.0.2  on 2016/9/27.}
 */
public class BasePicValue implements Serializable {

    /* 上传图片名字*/
    private String titleName;
    /*上传图片本地路径*/
    private String localPath;
    /*网络请求返回路径*/
    private String netPath;
    /*是否选择或者拍照图片*/
    private boolean isSelect;
    /*是否上传图片了*/
    private boolean isUpLoad;
    /*是否加载本地图片*/
    private boolean isLoad;
    /*是否在此界面上传图片*/
    private boolean getPic;
    /*默认本地图片*/
    private int defPicPath;
    /*数据响应码*/
    private int resultCode;
    /*设置小图默认图片*/
    private int defSmallPic;
    /*备注信息*/
    private String remark;
    /*是否压缩*/
    private boolean isCompress;

    public BasePicValue() {
        this.defSmallPic = R.mipmap.camera_40px;
        titleName = "选择图片";
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }

    public boolean isUpLoad() {
        return isUpLoad;
    }

    public void setUpLoad(boolean upLoad) {
        isUpLoad = upLoad;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getNetPath() {
        return netPath;
    }

    public void setNetPath(String netPath) {
        this.netPath = netPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public boolean isGetPic() {
        return getPic;
    }

    public void setGetPic(boolean getPic) {
        this.getPic = getPic;
    }

    public int getDefPicPath() {
        return defPicPath;
    }

    public void setDefPicPath(int defPicPath) {
        this.defPicPath = defPicPath;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getDefSmallPic() {
        return defSmallPic;
    }

    public void setDefSmallPic(int defSmallPic) {
        this.defSmallPic = defSmallPic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isCompress() {
        return isCompress;
    }

    public void setCompress(boolean compress) {
        isCompress = compress;
    }

    @Override
    public String toString() {
        return "BasePicValue{" +
                "titleName='" + titleName + '\'' +
                ", localPath='" + localPath + '\'' +
                ", netPath='" + netPath + '\'' +
                ", isSelect=" + isSelect +
                ", isUpLoad=" + isUpLoad +
                ", isLoad=" + isLoad +
                ", getPic=" + getPic +
                ", defPicPath=" + defPicPath +
                ", resultCode=" + resultCode +
                ", defSmallPic=" + defSmallPic +
                ", remark='" + remark + '\'' +
                ", isCompress=" + isCompress +
                '}';
    }
}
