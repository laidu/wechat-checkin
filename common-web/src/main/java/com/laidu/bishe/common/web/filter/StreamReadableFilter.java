package com.laidu.bishe.common.web.filter;

import com.laidu.bishe.common.web.utils.HttpUtils;
import com.laidu.bishe.common.web.utils.ReadableHttpRequestWrapper;
import com.laidu.bishe.common.web.utils.ReadableHttpResponseWrapper;
import com.laidu.bishe.utils.utils.HttpLogHelper;
import com.laidu.bishe.utils.utils.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.UUID;

@Component
@Slf4j
@Order(1)
public class StreamReadableFilter extends OncePerRequestFilter {
    private final static String SESSION_KEY = "sessionId";

    private final static int BODY_LIMIT_LENGTH = 2000;

    private final static String BODY_LIMIT_LENGTH_STR = "日志长度超过"+ BODY_LIMIT_LENGTH +"，暂不打印";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        response.setCharacterEncoding("UTF-8");

        ReadableHttpRequestWrapper requestWrapper = new ReadableHttpRequestWrapper(request);

        // Prepare response wrapper for making reponse readable
        ReadableHttpResponseWrapper responseWrapper = new ReadableHttpResponseWrapper(response);

        MDC.put(SESSION_KEY, UUID.randomUUID().toString());

        printRequestLog(requestWrapper);
        try {
            // Use customized http request & response wrapper instead of ServletRequest
            chain.doFilter(requestWrapper, responseWrapper);
            responseWrapper.flushBuffer();
        } catch (IOException e) {
            log.error("IOException occur in servlet filter.", e);
            throw e;
        } catch (ServletException e) {
            log.error("ServletException occur in servlet filter.", e);
            throw e;
        } finally {
            stopWatch.stop();
            printResponseLog(requestWrapper, responseWrapper, stopWatch);
            MDC.remove(SESSION_KEY);
        }
    }

    private void printRequestLog(ReadableHttpRequestWrapper requestWrapper) {
        // Get query string
        final String queryString = requestWrapper.getQueryString();

        final String header = getRequestHeader(requestWrapper);

        // Get request body string
        String requestBody = requestWrapper.getBody().length()> BODY_LIMIT_LENGTH ? BODY_LIMIT_LENGTH_STR : requestWrapper.getBody();

        final HttpMethod.HTTP_METHOD httpMethod = HttpMethod.HTTP_METHOD.valueOf("HTTP_" + requestWrapper.getMethod());
        final String clientIdAddress = HttpUtils.getRealIpAddress(requestWrapper);
        final String url = requestWrapper.getRequestURL().toString();

        final String requestLog = HttpLogHelper.logRequestOnClient(header, httpMethod, url, clientIdAddress, queryString, requestBody);

        log.info(requestLog);
    }

    private void printResponseLog(ReadableHttpRequestWrapper requestWrapper, ReadableHttpResponseWrapper responseWrapper, StopWatch stopWatch) {
        final HttpMethod.HTTP_METHOD httpMethod = HttpMethod.HTTP_METHOD.valueOf("HTTP_" + requestWrapper.getMethod());
        final String requestHeader = getRequestHeader(requestWrapper);
        final String responseHeader = getResponseHeader(responseWrapper);
        final String queryString = requestWrapper.getQueryString();
        final String url = requestWrapper.getRequestURL().toString();
        final String clientIpAddress = HttpUtils.getRealIpAddress(requestWrapper);
        final String requestBody = requestWrapper.getBody().length()> BODY_LIMIT_LENGTH ? BODY_LIMIT_LENGTH_STR : requestWrapper.getBody();
        final String responseCode = String.valueOf(responseWrapper.getStatus());
        String responseBody = null;
        try {
            responseBody = new String(responseWrapper.getCopy(), responseWrapper.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException", e);
        }

        final String responseLog = HttpLogHelper.logResponseOnClient(responseHeader, requestHeader, httpMethod, url, clientIpAddress, queryString, requestBody, responseCode, responseBody == null ? 0L : requestBody.length(), stopWatch.getTime());

        if (responseBody != null && responseBody.length() >= BODY_LIMIT_LENGTH) {
            log.info(String.format("[HTTP RESPONSE CONTENT]: %s",BODY_LIMIT_LENGTH ));
        } else {
            log.info(String.format("[HTTP RESPONSE CONTENT]: %s", responseBody));
        }

        log.info(responseLog);
    }

    private String getRequestHeader(ReadableHttpRequestWrapper requestWrapper) {
        StringBuilder header = new StringBuilder();
        Enumeration<String> names = requestWrapper.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            header.append(String.format("%s : %s; ", name, requestWrapper.getHeader(name)));
        }
        return header.toString();
    }

    private String getResponseHeader(ReadableHttpResponseWrapper responseWrapper) {
        StringBuilder header = new StringBuilder();
        responseWrapper.getHeaderNames().stream().forEach(key -> header.append(String.format("%s = %s; ", key, responseWrapper.getHeader(key))));
        return header.toString();
    }
}