package com.locationsapi.interfaces.adapter.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.LocationRequestDTO;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.LocationsQueryParamRequestDTO;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceLocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceRequestDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.LocationsResponseDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.MinimumDistanceResponseDTO;
import com.locationsapi.usecases.CreateLocation;
import com.locationsapi.usecases.FindMinimumDistance;
import com.locationsapi.usecases.GetLocations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "LOCATIONS", tags = {"LOCATIONS"})
public class LocationsController {

  private final GetLocations getLocations;
  private final CreateLocation createLocation;
  private final FindMinimumDistance findMinimumDistance;

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

  @ApiOperation(
      value = "Location creation",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ApiResponses(value = {
      @ApiResponse(code = 202, message = "Location accepted"),
      @ApiResponse(code = 422, message = "Could not create location"),
      @ApiResponse(code = 400, message = "Invalid request info"),
  })
  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping
  void create(
      @Valid @RequestBody final LocationRequestDTO locationRequestDTO)
      throws JsonProcessingException {
    createLocation.execute(locationRequestDTO.toMessage());
  }


  @ApiOperation(
      value = "Get minimum distance between one locations and a list of locations",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Minimum distance information retrieved"),
      @ApiResponse(code = 400, message = "Invalid request info"),
  })
  @PostMapping("/minimum-distance")
  MinimumDistanceResponseDTO getMinimumDistance(
      @RequestBody @Valid final MinimumDistanceRequestDTO minimumDistanceRequestDTO
  ) {
    final MinimumDistanceLocationDTO mostRecentLocation = minimumDistanceRequestDTO.getCurrentLocation();
    final List<MinimumDistanceLocationDTO> locations = minimumDistanceRequestDTO.getLocationsPlaces();
    return findMinimumDistance.execute(mostRecentLocation, locations);
  }

}
