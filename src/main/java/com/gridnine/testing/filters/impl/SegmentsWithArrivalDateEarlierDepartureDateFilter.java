package com.gridnine.testing.filters.impl;

import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to filter by rule: total time spent on earth exceeds two hours
 */
public class SegmentsWithArrivalDateEarlierDepartureDateFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

}