package org.agenda.model;

import org.agenda.repo.AppointmentCacheRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenIdIsNegative() {
        //Given
        final int appointmentId = -1;

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Appointment(appointmentId, null, null));

        //Then
        assertEquals("id cannot be negative", thrown.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenStartDateTimeIsNull() {
        //Given
        final int appointmentId = 1;

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Appointment(appointmentId, null, null));

        //Then
        assertEquals("startDateTime cannot be null", thrown.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenEndDateTimeNull() {
        //Given
        final int appointmentId = 1;
        final LocalDateTime startDateTime = LocalDateTime.now();

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Appointment(appointmentId, startDateTime, null));

        //Then
        assertEquals("endDateTime cannot be null", thrown.getMessage());
    }
}