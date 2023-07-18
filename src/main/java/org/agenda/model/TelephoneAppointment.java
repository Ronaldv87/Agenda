package org.agenda.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TelephoneAppointment extends Appointment{
    public TelephoneAppointment(int appointmentId, LocalDateTime startDateTime, LocalDateTime endDateime) {
        super(appointmentId, startDateTime, endDateime);
    }
}
