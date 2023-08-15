package org.agenda.core.service;

import org.agenda.model.Appointment;
import org.agenda.repo.AppointmentCacheRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    private AppointmentService appointmentService;

    private AppointmentCacheRepository appointmentCacheRepositoryMock;

    @BeforeEach
    void setupTest() {
        this.appointmentCacheRepositoryMock = mock(AppointmentCacheRepository.class);
        this.appointmentService = new AppointmentService(this.appointmentCacheRepositoryMock);
    }

    @AfterEach
    void tearDownTest() {

    }

    @Test
    void createAppointment_happyFlow() {
        // Given
        final var unpersistedAppointment = new Appointment(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        final var persistedAppointment = new Appointment(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        // Check org.mockito.ArgumentMatchers
        when(appointmentCacheRepositoryMock.create(eq(unpersistedAppointment))).thenReturn(persistedAppointment);

        // When
        final Appointment createdAppointment = this.appointmentService.create(unpersistedAppointment);

        // Then
        assertNotNull(createdAppointment);
        verify(appointmentCacheRepositoryMock).create(eq(unpersistedAppointment));
        verifyNoMoreInteractions(appointmentCacheRepositoryMock);
    }

    @Test
    void readAppointment_happyFlow() {
        //Given
        final var appointmentId = 1;
        final var persistedAppointment = new Appointment(appointmentId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        when(appointmentCacheRepositoryMock.read(eq(appointmentId))).thenReturn(persistedAppointment);

        //When
        final Appointment readAppointment = this.appointmentService.read(String.valueOf(appointmentId));

        //Then
        assertNotNull(readAppointment);
        verify(appointmentCacheRepositoryMock).read(eq(appointmentId));
        verifyNoMoreInteractions(appointmentCacheRepositoryMock);
    }

    @Test
    void updateAppointment_happyFlow() {
        //Given
        final var unpersistedAppointment = new Appointment(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        final var persistedAppointment = new Appointment(1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        when(appointmentCacheRepositoryMock.update(eq(unpersistedAppointment))).thenReturn(persistedAppointment);

        //When
        final Appointment updatedAppointment = this.appointmentService.update(unpersistedAppointment);

        //Then
        assertNotNull(updatedAppointment);
        verify(appointmentCacheRepositoryMock).update(unpersistedAppointment);
        verifyNoMoreInteractions(appointmentCacheRepositoryMock);
    }

    @Test
    void deleteAppointment_happyFlow() {
        //Given
        final var unpersistedAppointment = new Appointment(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        final var persistedAppointment = true;
        when(appointmentCacheRepositoryMock.delete(eq(unpersistedAppointment))).thenReturn(persistedAppointment);

        //When
        final boolean deletedAppointment = this.appointmentService.delete(unpersistedAppointment);

        //Then
        assertTrue(deletedAppointment);
        verifyNoMoreInteractions(appointmentCacheRepositoryMock);
    }
}