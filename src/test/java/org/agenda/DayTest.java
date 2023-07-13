package org.agenda;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    @Test
    void getAppointments() {
        Day day = new Day(date);
        List list = day.getAppointments();

        assertInstanceOf(List.class, list);
        assertNotNull(list);
    }

    @Test
    void insertTheSameDayTwiceFails() {
        //TODO write a test that it is not possible to insert the same day with the same date.
    }
}