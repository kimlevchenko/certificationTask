package com.gridnine.testing;

import com.gridnine.testing.filters.Filter;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.impl.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.filters.impl.GroundTimeMoreThanTwoHoursFilter;
import com.gridnine.testing.filters.impl.SegmentsWithArrivalDateEarlierDepartureDateFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.util.FlightBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepartureBeforeCurrentTimeFilter departureBeforeCurrentTimeFilter =
                new DepartureBeforeCurrentTimeFilter();
        SegmentsWithArrivalDateEarlierDepartureDateFilter segmentsWithArrivalDateEarlierDepartureDate =
                new SegmentsWithArrivalDateEarlierDepartureDateFilter();
        GroundTimeMoreThanTwoHoursFilter groundTimeMoreThanTwoHoursFilter =
                new GroundTimeMoreThanTwoHoursFilter();

        List<FlightFilter> filters = new ArrayList<>();
        filters.add(departureBeforeCurrentTimeFilter);
        filters.add(segmentsWithArrivalDateEarlierDepartureDate);
        filters.add(groundTimeMoreThanTwoHoursFilter);

        List<Flight> flights = FlightBuilder.createFlights();

        List<Flight> filteredFlights = Filter.flightsFilter(flights, filters);
        System.out.println("filteredFlights: " + filteredFlights);
    }
}