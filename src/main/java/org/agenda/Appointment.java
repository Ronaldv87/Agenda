package org.agenda;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private LocalDateTime dateTime = LocalDateTime.now();
    //TODO LocalTime startTime
    //TODO LocalTime endTime

    public int getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
