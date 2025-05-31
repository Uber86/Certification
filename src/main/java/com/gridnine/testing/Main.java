package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = new FlightFilterImpl();

        List<Flight> afterDepartureFilter = filter.filterDepartureBeforeNow(flights);
        System.out.println("Вылеты до текущего момента: ");
        System.out.println(afterDepartureFilter);

        List<Flight> afterArrivalFilter = filter.filterArrivalBeforeDeparture(flights);
        System.out.println("Сегменты с прилётом раньше вылета: ");
        System.out.println(afterArrivalFilter);

        List<Flight> afterGroundTimeFilter = filter.filterGroundTimeExceedsTwoHours(flights);
        System.out.println("Перелёты с временем на земле более 2 часов: " + afterGroundTimeFilter);
        System.out.println(afterGroundTimeFilter);

    }
}