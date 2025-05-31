package com.gridnine.testing;

import java.util.List;

/**
 *Интерфейс FlightFilter для фильтрации списка перелётов по различным правилам.
 **/
public interface FlightFilter {

    /**
     * Фильтрует перелёты, исключая те, у которых есть сегменты
     * с вылетом до текущего момента времени
     * @param flights список перелётов для фильтрации
     * @return отфильтрованный список перелётов, где все сегменты вылетают в будущем
     */
    List<Flight> filterDepartureBeforeNow(List<Flight> flights);

    /**
     * Фильтрует перелёты, исключая те, у которых есть
     * сегменты с датой прилёта раньше даты вылета
     * @param flights список перелётов для фильтрации
     * @return отфильтрованный список перелётов без сегментов с некорректными датами
     */
    List<Flight> filterArrivalBeforeDeparture(List<Flight> flights);

    /**
     * Фильтрует перелёты, исключая те, где суммарное время на земле
     * между сегментами превышает 2 часа
     * @param flights список перелётов для фильтрации
     * @return отфильтрованный список перелётов с допустимым временем на земле
     */
    List<Flight> filterGroundTimeExceedsTwoHours(List<Flight> flights);
}
