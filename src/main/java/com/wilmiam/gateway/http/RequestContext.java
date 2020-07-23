package com.wilmiam.gateway.http;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

public class RequestContext extends ConcurrentHashMap<String, Object> {
    protected static Class<? extends RequestContext> contextClass = RequestContext.class;
    protected static final ThreadLocal<? extends RequestContext> threadLocal = new ThreadLocal<RequestContext>() {
        @Override
        protected RequestContext initialValue() {
            try {
                return contextClass.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    };

    public static RequestContext getCurrentContext() {
        RequestContext context = threadLocal.get();
        return context;
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) get("request");
    }

    public void setRequest(HttpServletRequest request) {
        put("request", request);
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) get("response");
    }

    public void setResponse(HttpServletResponse response) {
        set("response", response);
    }


    public void setRequestEntity(RequestEntity<byte[]> requestEntity) {
        set("requestEntity", requestEntity);
    }

    public RequestEntity<byte[]> getRequestEntity() {
        return (RequestEntity<byte[]>) get("requestEntity");
    }

    public void setResponseEntity(ResponseEntity<byte[]> responseEntity) {
        set("responseEntity", responseEntity);
    }

    public ResponseEntity<byte[]> getResponseEntity() {
        return (ResponseEntity) get("responseEntity");
    }

    public void set(String key, Object value) {
        if (value != null)
            put(key, value);
        else
            remove(key);
    }

    public void unset() {
        threadLocal.remove();
    }

}
