package com.locationsapi.usecases.exceptions;

import java.io.IOException;
import java.io.UncheckedIOException;

public class EstablishmentsNotFoundException extends UncheckedIOException {
  public EstablishmentsNotFoundException() {
    super("Establishments not found", new IOException());
  }
}