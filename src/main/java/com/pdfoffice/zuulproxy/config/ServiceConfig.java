package com.pdfoffice.zuulproxy.config;

import com.google.api.client.util.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
    @Value("${firebase.databseurl}")
    private String firebaseDatabaseUrl;

    public String getFirebaseDatabaseUrl() {
        return firebaseDatabaseUrl;
    }
}
