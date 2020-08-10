package com.locationsapi.interfaces.adapter.repository.dynamodb.converter;

import java.util.Map;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public interface RepositoryResponseConverter<T> {
  T fromEntry(Map<String, AttributeValue> entry);
}
