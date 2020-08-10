package com.locationsapi.interfaces.adapter.http.handler.error;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@ToString
@JsonInclude(NON_NULL)
public class Error {
  private final String message;
}
