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

    @Test
    void update_happyFlow() {
        //Given
        final LocalDateTime startDateTime = LocalDateTime.now().plusMinutes(10);
        final LocalDateTime endDateTime = startDateTime.plusMinutes(30);
        Appointment updatedAppointment = new Appointment( appointmentId, startDateTime,endDateTime);
        assertEquals(1, appointmentCacheRepository.getList().size());

        //When
        final Appointment appointment = appointmentCacheRepository.update(updatedAppointment);

        //Then
        assertNotNull(appointment);
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals(startDateTime, appointment.getStartDateTime());
        assertEquals(endDateTime, appointment.getEndDateTime());
        assertEquals(1, appointmentCacheRepository.getList().size());
    }

    @Test
    void update_shouldThrowIllegalArgumentException_appointmentDoesNotExists() {
        //Given
        Appointment appointment = new Appointment( appointmentId,
                LocalDateTime.now().plusMinutes(10),
                startDateTime.plusMinutes(30));
        this.appointmentCacheRepository = new AppointmentCacheRepository();
        assertEquals(0, appointmentCacheRepository.getList().size());

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                appointmentCacheRepository.update(appointment));

        //Then
        assertEquals("Appointment with id " + appointmentId + " not found", thrown.getMessage());
    }

    @Test
    void delete_happyFlow() {
        //Given
        Appointment appointmentToDelete = new Appointment(appointmentId, startDateTime, endDateTime);
        assertEquals(1, appointmentCacheRepository.getList().size());

        //When
        final Appointment appointment = appointmentCacheRepository.delete(appointmentToDelete);

        //Then
        assertNotNull(appointment);
        assertEquals(appointmentId, appointment.getAppointmentId());
        assertEquals(startDateTime, appointment.getStartDateTime());
        assertEquals(endDateTime, appointment.getEndDateTime());
        assertEquals(0, appointmentCacheRepository.getList().size());
    }

    @Test
    void delete_shouldThrowIllegalArgumentException_appointmentDoesNotExists() {
        //Given
        final int appointmentId = 9999;
        Appointment appointmentToDelete = new Appointment(appointmentId,
                startDateTime.plusHours(1),
                endDateTime.plusHours(1));
        assertEquals(1, appointmentCacheRepository.getList().size());

        //When
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                appointmentCacheRepository.delete(appointmentToDelete));

        //Then
        assertEquals("Appointment with id " + appointmentId + " not found", thrown.getMessage());
        assertEquals(1, appointmentCacheRepository.getList().size());
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