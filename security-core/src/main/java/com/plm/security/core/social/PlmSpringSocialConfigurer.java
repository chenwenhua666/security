package com.plm.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author : cwh
 * 2019/7/8 0008
 * description ：
 */
public class PlmSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public PlmSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        if (socialAuthenticationFilterPostProcessor != null) {
            socialAuthenticationFilterPostProcessor.process(filter);
        }
        return (T) filter;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
        return socialAuthenticationFilterPostProcessor;
    }

    public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }
}
