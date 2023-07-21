package org.agenda.model;

import java.time.LocalDateTime;

public class Appointment {
    private final int appointmentId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public Appointment(final int appointmentId,
                       final LocalDateTime startDateTime,
                       final LocalDateTime endDateTime) {
        if (appointmentId < 1) {
            throw new IllegalArgumentException("id cannot be negative");
        }
        if (startDateTime == null) {
            throw new IllegalArgumentException("startDateTime cannot be null");
        }
        if (endDateTime == null) {
            throw new IllegalArgumentException("endDateTime cannot be null");
        }

        this.appointmentId = appointmentId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
