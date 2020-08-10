package com.locationsapi.interfaces.adapter.http.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.locationsapi.interfaces.adapter.http.handler.error.Error;
import com.locationsapi.usecases.exceptions.LocationsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(RuntimeException.class)
  Error handle(final RuntimeException exception) {
    return Error.builder()
        .message(exception.getMessage())
        .build();
  }

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(LocationsNotFoundException.class)
  Error handleLocationsNotFoundException(final LocationsNotFoundException locationsNotFoundException) {
    return Error.builder()
        .message(locationsNotFoundException.getMessage())
        .build();
  }

}
