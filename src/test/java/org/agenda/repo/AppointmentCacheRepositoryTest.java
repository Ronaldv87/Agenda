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
    void read_shouldThrowReadException_appointmentDoesNotExists() {
        //Given
        final int appointmentId = 1;
        this.appointmentCacheRepository = new AppointmentCacheRepository();

        //When
        final ReadException thrown = assertThrows(ReadException.class, () ->
                appointmentCacheRepository.read(appointmentId));

        //Then
        assertEquals("Appointment with id " + appointmentId + " not found", thrown.getMessage());
    }

    @Test
    void update_happyFlow() {
        //Given
        final LocalDateTime updateStartDateTime = LocalDateTime.now().plusMinutes(10);
        final LocalDateTime updateEndDateTime = updateStartDateTime.plusMinutes(30);
        Appointment appointment = new Appointment( appointmentId, updateStartDateTime,updateEndDateTime);
        assertEquals(1, appointmentCacheRepository.getList().size());

        //When
        final Appointment updatedAppointment = appointmentCacheRepository.update(appointment);

        //Then
        assertNotNull(updatedAppointment);
        assertEquals(appointmentId, updatedAppointment.getAppointmentId());
        assertEquals(updateStartDateTime, updatedAppointment.getStartDateTime());
        assertEquals(updateEndDateTime, updatedAppointment.getEndDateTime());
        assertEquals(1, appointmentCacheRepository.getList().size());
    }

    @Test
    void update_shouldThrowUpdateException_appointmentDoesNotExists() {
        //Given
        Appointment appointment = new Appointment( appointmentId,
                startDateTime.plusMinutes(10),
                startDateTime.plusMinutes(30));
        this.appointmentCacheRepository = new AppointmentCacheRepository();
        assertEquals(0, appointmentCacheRepository.getList().size());

        //When
        final UpdateExeption thrown = assertThrows(UpdateExeption.class, () ->
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
        final boolean appointment = appointmentCacheRepository.delete(appointmentToDelete);

        //Then
        assertTrue(appointment);
        assertEquals(0, appointmentCacheRepository.getList().size());
    }

    @Test
    void delete_shouldThrowDeleteException_appointmentDoesNotExists() {
        //Given
        final int appointmentId = 9999;
        Appointment appointmentToDelete = new Appointment(appointmentId,
                startDateTime.plusHours(1),
                endDateTime.plusHours(1));
        assertEquals(1, appointmentCacheRepository.getList().size());

        //When
        final DeleteException thrown = assertThrows(DeleteException.class, () ->
                appointmentCacheRepository.delete(appointmentToDelete));

        //Then
        assertEquals("Appointment with id " + appointmentId + " not found", thrown.getMessage());
        assertEquals(1, appointmentCacheRepository.getList().size());
    }

    @Test
    void getLIst_happyFlow() {
        //Given

        //When
        final List<Appointment> appointmentList = appointmentCacheRepository.getList();

        //Then
        assertNotNull(appointmentList);
        assertEquals(1, appointmentList.size());

    }

    @Test
    void getLIst_returnsNewListWhenNull() {
        //Given
        appointmentCacheRepository.setList(null);

        //When
        final List<Appointment> appointmentList = appointmentCacheRepository.getList();

        //Then
        assertNotNull(appointmentList);
        assert(appointmentList.isEmpty());

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