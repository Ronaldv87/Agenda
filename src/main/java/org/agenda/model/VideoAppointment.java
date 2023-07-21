package org.agenda.model;

import java.time.LocalDateTime;


public class VideoAppointment extends Appointment{
    public VideoAppointment(int appointmentId, LocalDateTime startDateTime, LocalDateTime endDateime) {
        super(appointmentId, startDateTime, endDateime);
    }
}
