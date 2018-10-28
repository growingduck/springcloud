package com.imooc.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.apigateway.exception.RateLimiterException;
import com.netflix.zuul.ZuulFilter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流
 */
public class RateLimiterFilter extends ZuulFilter {
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100); //每秒放100个令牌

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if(!RATE_LIMITER.tryAcquire()){  //获取一个令牌
            throw new RateLimiterException();
        }
        return null;
    }
}
