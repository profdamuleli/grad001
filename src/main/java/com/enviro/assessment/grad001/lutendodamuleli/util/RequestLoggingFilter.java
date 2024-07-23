package com.enviro.assessment.grad001.lutendodamuleli.util;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Component
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        LOGGER.info("Before request: " + message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        LOGGER.info("After request: " + message);
    }
}
