package com.example.scheduledtaskmaker.clients;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer " +
                "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiYXVkIjoibWVzc2FnaW5nIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsInNjb3BlIjpbImdldE9wZXJhdGlvbiJdLCJpYXQiOjE1MTYyMzkwMjJ9.r5atG2VsnxtMmrjoNMhB9-37_lpwoUMlM9q_bnePFHK-V4F1FtHQIcHK3luS18Fhu4VinWu2SLg5LEbIurZo3wJN9wW8LRzZqUTi1ldJMzHCXxs5b3FoP4_I_hxN2SHMJEPzkp-WGjev6AHTFNLubVnx4-_Ts12IzNnPq2b96ONyws5N364QPpXT1AQLm9jroAUg10M-Uc0yU9s0yj4RaVgguhirpnuG0853gmZZyEAgSoxe0ttf0uMV2S8rJPWBkzxYWOmKUCpIHo5X1Z2qk8uRJynaFrffJ3bSTj6QWRHxNPtUnvbKsuCdrnwdgjc6YHSsUPS65MWAileiCA6n1A");
    }
}
