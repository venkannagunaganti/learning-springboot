package com.example.learningspringboot.clientproxy;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientProxyConfig {
    @Value("user.api.url.v1")
    private String targetUri;
@Bean
    public UserResourceV1 getUserResource(){
        ResteasyClient client=new ResteasyClientBuilder().build();
        ResteasyWebTarget target=client.target(targetUri);
        UserResourceV1 userResourceV1=target.proxy(UserResourceV1.class);
        return  userResourceV1;
    }

}
