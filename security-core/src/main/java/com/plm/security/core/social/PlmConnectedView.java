package com.plm.security.core.social;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author : cwh
 * 2019/7/18 0018
 * description ：
 */
public class PlmConnectedView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        if (model.get("connections") == null) {
            response.getWriter().write("<h3>解绑成功</h3>");
        } else {
            response.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
