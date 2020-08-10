package com.locationsapi.interfaces.adapter.repository.dynamodb.configuration;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@Profile({"test", "local"})
class DynamoDbClientConfigLocal {

  @Value("${aws.dynamodb.endpoint}")
  private String url;

  @Bean
  DynamoDbClient dynamoDbClientLocal() {
    return DynamoDbClient.builder()
        .endpointOverride(URI.create(url))
        .build();
  }
}
