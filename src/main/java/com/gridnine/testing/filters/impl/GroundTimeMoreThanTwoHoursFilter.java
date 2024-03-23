package com.gridnine.testing.filters.impl;

import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for filtering according to the rule: there are segments with an arrival date earlier than the departure date
 */
public class GroundTimeMoreThanTwoHoursFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> calculateGroundTime(flight.getSegments()) <= 2)
                .collect(Collectors.toList());
    }

    private int calculateGroundTime(List<Segment> segments) {
        int groundTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime arrivalDate = segments.get(i - 1).getArrivalDate();
            LocalDateTime departureDate = segments.get(i).getDepartureDate();
            groundTime += (int) Duration.between(arrivalDate, departureDate).toHours();
        }
        return groundTime;
    }

}