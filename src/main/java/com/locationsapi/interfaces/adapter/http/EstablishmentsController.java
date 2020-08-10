package com.locationsapi.interfaces.adapter.http;

import com.locationsapi.interfaces.adapter.http.dto.response.establishments.EstablishmentsResponseDTO;
import com.locationsapi.usecases.GetEstablishments;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/establishments")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "ESTABLISHMENTS", tags = {"ESTABLISHMENTS"})
class EstablishmentsController {

    private final GetEstablishments getEstablishments;

    @ApiOperation(
            value = "Get establishments info by license plate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Establishments information retrieved"),
    })
    @GetMapping
    EstablishmentsResponseDTO getEstablishments() {
        return EstablishmentsResponseDTO
                .builder()
                .establishments(getEstablishments.execute())
                .build();
    }
}
