package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for filtering flights
 */
public class Filter {

    public static List<Flight> flightsFilter(List<Flight> flights, List<FlightFilter> flightFilters) {
        List<Flight> filteredFlights  = new ArrayList<>(flights);
        for (FlightFilter flightFilter : flightFilters) {
            filteredFlights = flightFilter.filter(filteredFlights);
        }
        return filteredFlights ;
    }

}