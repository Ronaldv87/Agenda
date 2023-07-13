package org.example;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private LocalDateTime dateTime = LocalDateTime.now();

    public int getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
