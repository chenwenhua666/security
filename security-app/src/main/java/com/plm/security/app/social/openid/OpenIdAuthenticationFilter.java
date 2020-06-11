package com.plm.security.app.social.openid;

import com.plm.security.core.authentication.phone.SmsCodeAuthenticationToken;
import com.plm.security.core.properties.SecurityConstants;
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
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openIdParameter = SecurityConstants.DEFAULT_PARAMETER_OPENID;
    private String providerIdParameter = SecurityConstants.DEFAULT_PARAMETER_PROVIDERID;

    private boolean postOnly = true;

    // ~ Constructor
    // ===================================================================================================

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_UNAUTHENTICATION_OPENID, "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String openId = obtainOpenId(request);
        String providerId = obtainProviderId(request);

        if (openId == null) {
            openId = "";
        }

        if (providerId == null) {
            providerId = "";
        }
        openId = openId.trim();


        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId, providerId);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(openIdParameter);
    }

    protected String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }

    protected void setDetails(HttpServletRequest request,
                              OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setOpenIdParameter(String openId) {
        Assert.hasText(openId, "openId parameter must not be empty or null");
        this.openIdParameter = openId;
    }

    public void setProviderIdParameter(String prividerId) {
        Assert.hasText(prividerId, "prividerId parameter must not be empty or null");
        this.providerIdParameter = prividerId;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getOpenIdParameter() {
        return openIdParameter;
    }

    public final String getPhoneParameter() {
        return providerIdParameter;
    }

}

