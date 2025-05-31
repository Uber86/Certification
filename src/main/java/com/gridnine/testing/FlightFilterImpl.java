package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link FlightFilter} с конкретными
 * правилами фильтрации перелётов
 */
public class FlightFilterImpl implements FlightFilter{

    @Override
    public List<Flight> filterDepartureBeforeNow(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream().filter(it -> it.getSegments()
                .stream()
                .allMatch(segment -> segment.getDepartureDate()
                        .isAfter(now)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterArrivalBeforeDeparture(List<Flight> flights) {
        return flights.stream().filter(it->it.getSegments().stream()
                .allMatch(segment -> !segment.getArrivalDate()
                        .isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterGroundTimeExceedsTwoHours(List<Flight> flights) {
        final int maxGroundMinutes = 2*60;
        return flights.stream().filter(it -> {
            List<Segment> segments = it.getSegments();
            if (segments.size() < 2) {
                return true;
            }
            int totalGroundMinutes = 0;
            for (int i = 1; i < segments.size(); i++) {
                LocalDateTime prevArrival = segments.get(i - 1).getArrivalDate();
                LocalDateTime nextDeparture = segments.get(i).getArrivalDate();
                totalGroundMinutes += (int) Duration.between(prevArrival, nextDeparture).toMinutes();
            }
            return totalGroundMinutes <= maxGroundMinutes;
        }).collect(Collectors.toList());
    }
}
