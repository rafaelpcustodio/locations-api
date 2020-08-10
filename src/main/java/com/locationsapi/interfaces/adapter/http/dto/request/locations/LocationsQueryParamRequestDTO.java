package com.locationsapi.interfaces.adapter.http.dto.request.locations;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Represents the query parameters that mighht be used to filter locations.")
public class LocationsQueryParamRequestDTO {

  @ApiModelProperty(value = "Date time used to filter from this date time until now.", example = "2020-01-01T00:05:44.810Z")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dateTime;
}
