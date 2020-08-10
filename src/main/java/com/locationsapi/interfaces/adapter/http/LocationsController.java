package com.locationsapi.interfaces.adapter.http.handler.error;

import com.locationsapi.interfaces.adapter.http.dto.request.locations.LocationsQueryParamRequestDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationsResponseDTO;
import com.locationsapi.usecases.GetLocations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "LOCATIONS", tags = {"LOCATIONS"})
public class LocationsController {

  private final GetLocations getLocations;

  @ApiOperation(
      value = "Get locations info by license plate path variable. Date time query parameter is used to filter locations from this date time until now",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Locations information retrieved"),
  })
  @GetMapping("/{licensePlate:[A-Z]{3}[0-9]{4}}")
  LocationsResponseDTO getLocationsByLicensePlateAndDateTimeUntilNow(
      @PathVariable final String licensePlate,
      @Valid final LocationsQueryParamRequestDTO locationsQueryParamRequestDTO
  ) {
    return LocationsResponseDTO.builder().
        licensePlate(licensePlate)
        .vehicleLocationEntities(getLocations.execute(
            licensePlate, locationsQueryParamRequestDTO.getDateTime()
        )).build();
  }

}
