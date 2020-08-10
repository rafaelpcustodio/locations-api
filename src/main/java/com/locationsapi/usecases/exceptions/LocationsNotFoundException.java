package com.locationsapi.usecases.exceptions;

import java.io.IOException;
import java.io.UncheckedIOException;

public class LocationsNotFoundException extends UncheckedIOException {
  public LocationsNotFoundException() {
    super("Locations not found", new IOException());
  }
}
