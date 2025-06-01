package com.gridnine.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterImplTest {

    private static List<Flight> testFlights;
    private static FlightFilter filter;

    @BeforeAll
    static void setup() {
        testFlights = FlightBuilder.createFlights();
        filter = new FlightFilterImpl();
    }

    @Test
    void testFilterDepartureBeforeNow() {
        List<Flight> filtered = filter.filterDepartureBeforeNow(testFlights);
        assertTrue(filtered.stream()
                .allMatch(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(java.time.LocalDateTime.now()))));
    }


    @Test
    void testFilterArrivalBeforeDeparture() {
        List<Flight> filtered = filter.filterArrivalBeforeDeparture(testFlights);
        assertTrue(filtered.stream()
                .allMatch(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate()
                                .isBefore(segment.getDepartureDate()))));
    }


    @Test
    void testFilterGroundTimeExceedsTwoHours() {
        List<Flight> filtered = filter.filterGroundTimeExceedsTwoHours(testFlights);
        // Проверяем, что время на земле не превышает 2 часа
        assertTrue(filtered.stream().allMatch(flight -> {
            List<Segment> segments = flight.getSegments();
            if (segments.size() < 2) return true;
            long totalGroundMinutes = 0;
            for (int i = 1; i < segments.size(); i++) {
                long minutes = java.time.Duration.between(
                        segments.get(i - 1).getArrivalDate(),
                        segments.get(i).getDepartureDate()
                ).toMinutes();
                totalGroundMinutes += minutes;
            }
            return totalGroundMinutes <= 120;
        }));
    }
}