package com.locationsapi.interfaces.adapter.http.handler;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.locationsapi.interfaces.adapter.http.handler.error.Error;
import com.locationsapi.usecases.exceptions.EstablishmentsNotFoundException;
import com.locationsapi.usecases.exceptions.LocationsNotFoundException;
import java.util.List;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(EstablishmentsNotFoundException.class)
  Error handleEstablishmentsNotFoundException(final EstablishmentsNotFoundException establishmentsNotFoundException) {
    return Error.builder()
        .message(establishmentsNotFoundException.getMessage())
        .build();
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  List<Error> handle(final MethodArgumentNotValidException exception) {
    return exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .map(Error::new)
        .collect(toList());
  }

}
