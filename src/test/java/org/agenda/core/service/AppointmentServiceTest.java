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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
    void generateAppointmentId_happyFlow() {
        //Given

        //When

        //Then
    }

    @Test
    void generateAppointmentId_returnsOne_ifAppointmentListIsEmpty() {
        //Given

        //When

        //Then
    }

}