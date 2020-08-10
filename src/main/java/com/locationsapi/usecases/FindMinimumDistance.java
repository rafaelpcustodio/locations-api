package com.locationsapi.usecases;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static java.util.stream.Collectors.toList;

import com.locationsapi.interfaces.adapter.http.dto.request.locations.minimumdistance.MinimumDistanceLocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.ClosestLocationDTO;
import com.locationsapi.interfaces.adapter.http.dto.response.locations.MinimumDistanceResponseDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FindMinimumDistance {

    private static final double EARTH_RADIUS = 6371000;

    public MinimumDistanceResponseDTO execute(
            final MinimumDistanceLocationDTO mostRecentLocation,
            final List<MinimumDistanceLocationDTO> locations) {

        final List<Double> distances = locations.stream()
                .map(location -> calculateDistanceBetweenTwoLocationsInMeters(mostRecentLocation, location))
                .collect(toList());

        final double minimumDistance = distances.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .getAsDouble();
        final int index = distances.indexOf(minimumDistance);
        return MinimumDistanceResponseDTO.builder()
                .location(ClosestLocationDTO.builder()
                        .latitude(locations.get(index).getLatitude())
                        .longitude(locations.get(index).getLongitude())
                        .build())
                .minimumDistance(minimumDistance)
                .build();
    }

    private double calculateDistanceBetweenTwoLocationsInMeters(final MinimumDistanceLocationDTO mostRecentLocation,
                                                                final MinimumDistanceLocationDTO location) {
        final double mostRecentLatitude = mostRecentLocation.getLatitude();
        final double mostRecentLongitude = mostRecentLocation.getLongitude();
        final double actualLatitude = location.getLatitude();
        final double actualLongitude = location.getLongitude();

        double distanceBetweenLatitudes = toRadians(mostRecentLatitude - actualLatitude);
        double distanceBetweenLongitudes = toRadians(mostRecentLongitude - actualLongitude);
        double mainResultFromFormula = sin(distanceBetweenLatitudes / 2) * sin(distanceBetweenLatitudes / 2) +
                cos(toRadians(mostRecentLocation.getLongitude())) * cos(toRadians(location.getLatitude())) *
                        sin(distanceBetweenLongitudes / 2) * sin(distanceBetweenLongitudes / 2);

        double response = 2 * Math.atan2(Math.sqrt(mainResultFromFormula), Math.sqrt(1 - mainResultFromFormula));
        return (float) (EARTH_RADIUS * response);
    }
}
