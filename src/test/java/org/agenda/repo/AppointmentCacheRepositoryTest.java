package org.agenda.repo;

import org.agenda.model.Appointment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentCacheRepositoryTest {
    private AppointmentCacheRepository appointmentCacheRepository;
    private final int appointmentId = 1;
    private final LocalDateTime startDateTime = LocalDateTime.now();
    private final LocalDateTime endDateTime = startDateTime.plusHours(1);

    @BeforeEach
    void setupTest() {
        this.appointmentCacheRepository = new AppointmentCacheRepository();
        this.appointmentCacheRepository.setList(createAppointmentList(1));
    }

    @AfterEach
    void teardown() {
        this.appointmentCacheRepository = null;
    }

    @Test
    void create_happyFlow() {
        //Given
        final int appointmentId = 1;
        final LocalDateTime startDateTime = LocalDateTime.now();
        final LocalDateTime endDateTime = startDateTime.plusHours(1);
        this.appointmentCacheRepository = new AppointmentCacheRepository();
        assertEquals(0, appointmentCacheRepository.getList().size());

        //When

        final Appointment appointment = appointmentCacheRepository.create(appointmentId, startDateTime, endDateTime);

        //Then
        assertNotNull(appointment);
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals(startDateTime, appointment.getStartDateTime());
        assertEquals(endDateTime, appointment.getEndDateTime());
        assertEquals(1, appointmentCacheRepository.getList().size());
    }

    @Test
    void create_shouldThrowCreationException_appointmentAlreadyExists() {
        //Given
        assertEquals(1, appointmentCacheRepository.getList().size());

        //When
        final CreationException thrown = assertThrows(CreationException.class, () ->
                appointmentCacheRepository.create(appointmentId, startDateTime, endDateTime));

        //Then
        assertEquals("This appointment with " + appointmentId + " already exists", thrown.getMessage());
    }

    @Test
    void read_happyFlow() {
        //Given
        final int appointmentId = 1;

        //When
        final Appointment appointment = appointmentCacheRepository.read(appointmentId);

        //Then
        assertNotNull(appointment);
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals(startDateTime, appointment.getStartDateTime());
        assertEquals(endDateTime, appointment.getEndDateTime());

    }

    @Test
    void read_returnsNull_whenAppointmentDoesNotExist() {
        //Given
        final int appointmentId = 1;
        this.appointmentCacheRepository = new AppointmentCacheRepository();

        //When
        final Appointment appointment = appointmentCacheRepository.read(appointmentId);

        //Then
        assertNull(appointment);
    }

    private List<Appointment> createAppointmentList(final int appointmentNumber) {
        final List<Appointment> appointmentList = new ArrayList<>();
        for (int n = 0; n < appointmentNumber; n++) {
            appointmentList.add(createAppointment(appointmentId + n));
        }
        return appointmentList;
    }

    private Appointment createAppointment(final int appointmentId) {
        return new Appointment(appointmentId, startDateTime, endDateTime);
    }
}