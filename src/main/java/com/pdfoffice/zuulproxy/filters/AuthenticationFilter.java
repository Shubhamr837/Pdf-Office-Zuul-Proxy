package com.pdfoffice.zuulproxy.filters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pdfoffice.zuulproxy.config.FirebaseAppHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;
@Component
public class AuthenticationFilter extends ZuulFilter {
    boolean SHOULD_FILTER = true;
   @Autowired
    FirebaseAppHolder firebaseAppHolder;
    public boolean isAuthorized(String apiKey){
        try {
            FirebaseAuth.getInstance(firebaseAppHolder.getFirebaseApp()).verifyIdToken(apiKey);
        }
        catch (FirebaseAuthException f){
            return false;
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1; // run before PreDecoration
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER; // a filter has already determined serviceId
    }
    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String apiKey = request.getHeader("token");

        if ((!isAuthorized(apiKey))||apiKey==null){
            // blocks the request
            ctx.setSendZuulResponse(false);

            // response to client
            ctx.setResponseBody("Token not authorized");
            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

        }

        return null;
    }

    }
