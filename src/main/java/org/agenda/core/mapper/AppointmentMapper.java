package org.agenda.core.mapper;

import org.agenda.core.service.AppointmentService;
import org.agenda.model.Appointment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {
    private final AppointmentService appointmentService;

    public AppointmentMapper(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public Appointment convertStringToAppointment(final String randomData) {
        final String[] parts = randomData.split("/");
        final int appointmentId = appointmentService.generateAppointmentId();
        final LocalDateTime startDateTime = dateTimeConverter(parts[0]);
        final LocalDateTime endDateTime = dateTimeConverter(parts[1]);
        return new Appointment(appointmentId, startDateTime, endDateTime);
    }

    public Appointment convertStringToAppointmentWithId(final String randomData) {
        final String[] parts = randomData.split("/");
        final int appointmentId = Integer.parseInt(parts[0]);
        final LocalDateTime startDateTime = dateTimeConverter(parts[1]);
        final LocalDateTime endDateTime = dateTimeConverter(parts[2]);
        return new Appointment(appointmentId, startDateTime, endDateTime);
    }

    public String convertAppointmentToString(final Appointment appointment) {
        final int appointmentId = appointment.getAppointmentId();
        final String startDateTime = dateTimeConverter(appointment.getStartDateTime());
        final String endDateTime = dateTimeConverter(appointment.getEndDateTime());
        return appointmentId + "/" + startDateTime + "/" + endDateTime;
    }

    public List<String> convertListOfAppointmentsToString(final List<Appointment> appointments) {
        final List<String> appointmentsAsString = new ArrayList<>();
        for(Appointment appointmentToConvert : appointments) {
            String convertedAppointment = convertAppointmentToString(appointmentToConvert);
            appointmentsAsString.add(convertedAppointment);
        }
        return appointmentsAsString;
    }

    LocalDateTime dateTimeConverter(final String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    String dateTimeConverter(final LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
