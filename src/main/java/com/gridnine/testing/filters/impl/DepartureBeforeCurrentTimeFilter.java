package com.gridnine.testing.filters.impl;

import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for filtering by rule: departure to the current point in time
 */
public class DepartureBeforeCurrentTimeFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .anyMatch(segment -> segment.getDepartureDate().isAfter(currentTime)))
                .collect(Collectors.toList());
    }
}