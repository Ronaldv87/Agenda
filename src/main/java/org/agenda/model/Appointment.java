package org.agenda.model;

import java.time.LocalTime;

public class Appointment {
    private final int appointmentId;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Appointment(int appointmentId, LocalTime startTime, LocalTime endTime) {
        this.appointmentId = appointmentId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Appointment getAppointment() {
        return new Appointment(appointmentId, startTime, endTime);
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
