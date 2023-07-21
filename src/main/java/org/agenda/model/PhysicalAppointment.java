package org.agenda.model;

import java.time.LocalDateTime;


public class PhysicalAppointment extends Appointment{
    public PhysicalAppointment(int appointmentId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(appointmentId, startDateTime, endDateTime);
    }
}
