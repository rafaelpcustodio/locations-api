package com.locationsapi.interfaces.adapter.repository.dynamodb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@Profile({"default", "prod"})
class DynamoDbClientConfig {

  @Bean
  DynamoDbClient dynamoDbClient() {
    return DynamoDbClient.create();
  }
}
