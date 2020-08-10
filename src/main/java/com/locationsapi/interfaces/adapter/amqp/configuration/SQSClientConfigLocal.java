package com.locationsapi.interfaces.adapter.amqp.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"test", "local"})
@Configuration
class SQSClientConfigLocal {

    @Value("${aws.sqs.endpoint}")
    private String endpoint;

    @Value("${aws.sqs.accessKey}")
    private String accessKey;

    @Value("${aws.sqs.secretKey}")
    private String secretKey;

    @Value("${aws.sqs.region}")
    private String region;

    @Bean
    AmazonSQSAsync amazonSqsAsync() {
        final AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        final AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(awsCredentialsProvider)
                .build();
    }
}
