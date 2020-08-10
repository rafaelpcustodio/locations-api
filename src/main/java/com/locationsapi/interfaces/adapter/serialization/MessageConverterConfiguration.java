package com.locationsapi.interfaces.adapter.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
class MessageConverterConfiguration {

  @Bean
  MessageConverter mappingJackson2MessageConverter(final ObjectMapper objectMapper) {
    final MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
    mappingJackson2MessageConverter.setSerializedPayloadClass(String.class);
    mappingJackson2MessageConverter.setObjectMapper(objectMapper);
    return mappingJackson2MessageConverter;
  }
}
