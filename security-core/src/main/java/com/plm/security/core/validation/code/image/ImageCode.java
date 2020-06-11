package com.plm.security.core.validation.code.image;

import com.plm.security.core.validation.code.ValidationCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author : cwh
 * 2019/6/9 0009
 * description ：
 */
public class ImageCode extends ValidationCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
