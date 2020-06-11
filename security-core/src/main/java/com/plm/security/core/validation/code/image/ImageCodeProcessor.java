package com.plm.security.core.validation.code.image;

import com.plm.security.core.validation.code.impl.AbstractValidationCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author : cwh
 * 2019/6/13 0013
 * description ：
 */
@Component("imageValidationCodeProcessor")
public class ImageCodeProcessor extends AbstractValidationCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
