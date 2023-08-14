package org.agenda.core.mapper;

import org.agenda.core.service.AppointmentService;
import org.agenda.model.Appointment;
import org.agenda.repo.AppointmentCacheRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentMapperTest {

    private AppointmentMapper appointmentMapper;

    private AppointmentCacheRepository appointmentCacheRepository;

    @BeforeEach
    void setupTest() {
        final AppointmentService appointmentService = new AppointmentService(this.appointmentCacheRepository);
        this.appointmentMapper = new AppointmentMapper(appointmentService);
    }

    @AfterEach
    void teardownTest() {
        this.appointmentMapper = null;
    }

//    //Test requires either mockito or generate appointmentId needs to be moved to appointmentRepo
//    @Test
//    void convertStringToAppointment_HappyFlow() {
//        //Given
//        final String randomData = "2023-01-02 11:00/2023-01-02 12:00";
//        final int expectedAppointmentId = 1;
//        final LocalDateTime expectedStartDateTime = LocalDateTime.parse("2023-01-02T11:00");
//        final LocalDateTime expectedEndDateTime = LocalDateTime.parse("2023-01-02T12:00");
//
//        //When
//        final Appointment appointment = appointmentMapper.convertStringToAppointment(randomData);
//
//        //Then
//        assertNotNull(appointment);
//        assertEquals(expectedAppointmentId, appointment.getAppointmentId());
//        assertEquals(expectedStartDateTime, appointment.getStartDateTime());
//        assertEquals(expectedEndDateTime, appointment.getEndDateTime());
//    }

    @Test
    void convertAppointmentToString_happyFlow() {
        //Given
        final String expectedStringOutput = "1/2023-01-02 11:00/2023-01-02 12:00";
        final Appointment appointment = new Appointment(1,
                LocalDateTime.parse("2023-01-02T11:00"),
                LocalDateTime.parse("2023-01-02T12:00"));

        //When
        final String actualStringOutput = appointmentMapper.convertAppointmentToString(appointment);

        //Then
        assert(expectedStringOutput.equals(actualStringOutput));

    }

    @Test
    void convertListOfAppointmentsToString_happyFlow() {
        //Given

        //When

        //Then
    }

    @Test
    void dateTimeConverter_happyFlow_StringInput() {
        //Given
        final String dateTime = "2023-01-01 12:00";
        final LocalDateTime expectedDate = LocalDateTime.parse("2023-01-01T12:00");

        //When
        final LocalDateTime actualDate = appointmentMapper.dateTimeConverter(dateTime);

        //Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void dateTimeConverter_happyFlow_LocalDateTimeInput() {
        //Given
        final String expectedDate = "2023-01-01 12:00";
        final LocalDateTime dateTime= LocalDateTime.parse("2023-01-01T12:00");

        //When
        final String actualDate = appointmentMapper.dateTimeConverter(dateTime);

        //Then
        assert(expectedDate.equals(actualDate));
    }
}