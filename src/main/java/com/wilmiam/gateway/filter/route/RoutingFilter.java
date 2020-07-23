package com.wilmiam.gateway.filter.route;

import com.wilmiam.gateway.filter.EatuulFilter;
import com.wilmiam.gateway.http.RequestContext;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RoutingFilter extends EatuulFilter {

    @Override
    public String filterType() {
        // TODO Auto-generated method stub
        return "route";
    }

    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        RequestEntity<byte[]> requestEntity = ctx.getRequestEntity();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);
        ctx.setResponseEntity(responseEntity);
    }


}
