package com.gridnine.testing.impl;

import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.impl.GroundTimeMoreThanTwoHoursFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroundTimeMoreThanTwoHoursFilterTest {

    private final FlightFilter out = new GroundTimeMoreThanTwoHoursFilter();

    private Segment segment1;
    private Segment segment2;
    private Segment segment3;

    private Flight flight1;
    private Flight flight2;

    List<Flight> flights = new ArrayList<>();

    List<Flight> expected = new ArrayList<>();

    @BeforeEach
    public void init() {
        segment1 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));
        segment2 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
        segment3 = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(9));


        flight1 = new Flight(List.of(segment1));
        // does not pass the filter GroundTimeMoreThanTwoHoursFilter
        flight2 = new Flight(List.of(segment2, segment3));

        flights.add(flight1);
        flights.add(flight2);

    }

    @Test
    public void filterTest() {

        expected.add(flight1);

        List<Flight> result = out.filter(flights);

        assertEquals(1, result.size());
        assertEquals(expected, result);
    }
}