package org.agenda.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {

    @Test
    void dateTimeConverter_happyFlow() {
        //Given
        final String dateTime = "2023-01-01 12:00";
        final LocalDateTime expectedDate = LocalDateTime.parse("2023-01-01T12:00");
        final AppointmentService appointmentService = new AppointmentService();

        //When
        final LocalDateTime actualDate = appointmentService.dateTimeConverter(dateTime);

        //Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void generateAppointmentId() {
        //Given

        //When

        //Then
    }


}