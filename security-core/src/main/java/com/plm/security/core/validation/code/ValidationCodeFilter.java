package com.plm.security.core.validation.code;

import com.plm.security.core.properties.SecurityConstants;
import com.plm.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : cwh
 * 2019/6/11 0011
 * description ：
 */
@Component("validationCodeFilter")
public class ValidationCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidationCodeProcessorHolder validationCodeProcessorHolder;

    private Map<String, ValidationCodeType> urlMap = new HashMap<>();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidationCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidationCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidationCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidationCodeType.SMS);
    }

    protected void addUrlToMap(String urlString, ValidationCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ValidationCodeType type = getValidateCodeType(request);
        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validationCodeProcessorHolder.findValidateCodeProcessor(type.toString())
                        .validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");
            } catch (ValidationCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private ValidationCodeType getValidateCodeType(HttpServletRequest request) {
        ValidationCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }

}
