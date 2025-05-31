package com.gridnine.testing;

import java.util.List;

/*
    Интерфейс для фильтрации списка перелётов по различным правилам.
 */
public interface FlightFilter {

    List<Flight> filterDepartureBeforeNow(List<Flight> flights);
    List<Flight> filterArrivalBeforeDeparture(List<Flight> flights);
    List<Flight> filterGroundTimeExceedsTwoHours(List<Flight> flights);
}
