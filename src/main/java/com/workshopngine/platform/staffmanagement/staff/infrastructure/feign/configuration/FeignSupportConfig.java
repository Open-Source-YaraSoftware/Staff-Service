package com.workshopngine.platform.staffmanagement.staff.infrastructure.feign.configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

public class FeignSupportConfig {
    @Bean
    public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
