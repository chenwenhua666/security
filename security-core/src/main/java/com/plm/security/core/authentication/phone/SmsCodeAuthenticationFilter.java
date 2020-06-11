package com.plm.security.core.authentication.phone;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : cwh
 * 2019/6/13 0013
 * description ：
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phoneNumber";

    private String phoneParameter = SPRING_SECURITY_FORM_PHONE_KEY;
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/phone", "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String phoneNumber = obtainPhoneNumber(request);

        if (phoneNumber == null) {
            phoneNumber = "";
        }

        phoneNumber = phoneNumber.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phoneNumber);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainPhoneNumber(HttpServletRequest request) {
        return request.getParameter(phoneParameter);
    }

    protected void setDetails(HttpServletRequest request,
                              SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "phoneNumber parameter must not be empty or null");
        this.phoneParameter = phoneParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getPhoneParameter() {
        return phoneParameter;
    }

}

