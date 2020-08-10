package com.locationsservice.interfaces.adapter.amqp.configuration;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"default"})
class SQSClientConfig {

    @Bean
    AmazonSQSAsync amazonSqsAsync() {
        return AmazonSQSAsyncClientBuilder.standard().build();
    }
}
