package com.runjing.bean.response.guild;

/**
 * @Created: qianxs  on 2020.08.01 21:50.
 * @Describe：
 * @Review：
 * @Modify：
 * @Version: v_1.0 on 2020.08.01 21:50.
 * @Remark:
 */
public class GuildImageBean {
    private String image;
    private int img;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "GuildImageBean{" +
                "image='" + image + '\'' +
                ", img=" + img +
                '}';
    }
}
