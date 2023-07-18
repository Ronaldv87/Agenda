package org.agenda.repo;

import org.agenda.model.Appointment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentCacheRepositoryTest {
    //TODO add @BeforeEach and @AfterEach for repository instantiation and teardown
    //TODO move unhappy flow test cases to appointment test class

    @Test
    void create_happyFlow() {
        //Given
        final int appointmentId = 1;
        final LocalDateTime startDateTime = LocalDateTime.now();
        final LocalDateTime endDateTime = startDateTime.plusHours(1);
        final AppointmentCacheRepository appointmentCacheRepository = new AppointmentCacheRepository();
        assertEquals(0, appointmentCacheRepository.appointmentList.size());

        //When

        final Appointment appointment = appointmentCacheRepository.create(appointmentId, startDateTime, endDateTime);

        //Then
        assertNotNull(appointment);
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals(startDateTime, appointment.getStartDateTime());
        assertEquals(endDateTime, appointment.getEndDateTime());
        assertEquals(1, appointmentCacheRepository.appointmentList.size());
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenIdIsNegative() {
        //Given
        final int appointmentId = -1;
        final AppointmentCacheRepository appointmentCacheRepository = new AppointmentCacheRepository();

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                appointmentCacheRepository.create(appointmentId, null, null));

        //Then
        assertEquals("id cannot be negative", thrown.getMessage());
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenStartDateTimeIsNull() {
        //Given
        final int appointmentId = 1;
        final AppointmentCacheRepository appointmentCacheRepository = new AppointmentCacheRepository();

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                appointmentCacheRepository.create(appointmentId, null, null));

        //Then
        assertEquals("startDateTime cannot be null", thrown.getMessage());
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenEndDateTimeNull() {
        //Given
        final int appointmentId = 1;
        final LocalDateTime startDateTime = LocalDateTime.now();
        final LocalDateTime endDateTime = null;
        final AppointmentCacheRepository appointmentCacheRepository = new AppointmentCacheRepository();

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                appointmentCacheRepository.create(appointmentId, startDateTime, endDateTime));

        //Then
        assertEquals("endDateTime cannot be null", thrown.getMessage());
    }
}