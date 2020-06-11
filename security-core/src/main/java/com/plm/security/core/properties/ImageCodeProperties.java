package com.plm.security.core.properties;

/**
 * @author : cwh
 * 2019/6/11 0011
 * description ：
 */
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;
    private int height = 23;
    private String url;

    public ImageCodeProperties() {
        setLength(4);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
