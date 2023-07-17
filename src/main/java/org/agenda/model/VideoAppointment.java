package org.agenda.model;

import java.time.LocalTime;

public class VideoAppointment extends Appointment{
    public VideoAppointment(int appointmentId, LocalTime startTime, LocalTime endTime) {
        super(appointmentId, startTime, endTime);
    }
}
