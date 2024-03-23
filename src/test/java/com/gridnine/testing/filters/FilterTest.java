package com.gridnine.testing.filters;

import com.gridnine.testing.filters.impl.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.filters.impl.GroundTimeMoreThanTwoHoursFilter;
import com.gridnine.testing.filters.impl.SegmentsWithArrivalDateEarlierDepartureDateFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    private final Filter out = new Filter();

    private Segment segment1;
    private Segment segment2;
    private Segment segment3;
    private Segment segment4;
    private Segment segment5;

    private Flight flight1;
    private Flight flight2;
    private Flight flight3;
    private Flight flight4;

    private DepartureBeforeCurrentTimeFilter departureBeforeCurrentTimeFilter;
    private SegmentsWithArrivalDateEarlierDepartureDateFilter segmentsWithArrivalDateEarlierDepartureDateFilter;
    private GroundTimeMoreThanTwoHoursFilter groundTimeMoreThanTwoHoursFilter;

    List<FlightFilter> filters = new ArrayList<>();

    List<Flight> flights = new ArrayList<>();

    List<Flight> expected = new ArrayList<>();

    @BeforeEach
    public void init() {
        segment1 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
        segment2 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));
        segment3 = new Segment(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1));
        segment4 = new Segment(LocalDateTime.now(), LocalDateTime.now().minusDays(3));
        segment5 = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(9));

        // does not pass the filter groundTimeMoreThanTwoHoursFilter
        flight1 = new Flight(List.of(segment1, segment5));
        flight2 = new Flight(List.of(segment2));
        // does not pass the filter departureBeforeCurrentTimeFilter
        flight3 = new Flight(List.of(segment3));
        // does not pass the filter segmentsWithArrivalDateEarlierDepartureDateFilter
        flight4 = new Flight(List.of(segment4));

        departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilter();
        segmentsWithArrivalDateEarlierDepartureDateFilter = new SegmentsWithArrivalDateEarlierDepartureDateFilter();
        groundTimeMoreThanTwoHoursFilter = new GroundTimeMoreThanTwoHoursFilter();

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);

    }

    @Test
    public void flightsWithThreeFiltersTest() {

        filters.add(departureBeforeCurrentTimeFilter);
        filters.add(segmentsWithArrivalDateEarlierDepartureDateFilter);
        filters.add(groundTimeMoreThanTwoHoursFilter);

        expected.add(flight2);

        List<Flight> result = out.flightsFilter(flights, filters);

        assertEquals(1, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void flightsWithTwoFiltersTest() {

        filters.add(departureBeforeCurrentTimeFilter);
        filters.add(segmentsWithArrivalDateEarlierDepartureDateFilter);

        expected.add(flight1);
        expected.add(flight2);

        List<Flight> result = out.flightsFilter(flights, filters);

        assertEquals(2, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void flightsWithOneFiltersTest() {

        filters.add(segmentsWithArrivalDateEarlierDepartureDateFilter);

        expected.add(flight1);
        expected.add(flight2);
        expected.add(flight3);

        List<Flight> result = out.flightsFilter(flights, filters);

        assertEquals(3, result.size());
        assertEquals(expected, result);
    }

}