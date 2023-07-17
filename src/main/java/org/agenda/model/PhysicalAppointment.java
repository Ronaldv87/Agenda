package org.agenda.model;

import java.time.LocalTime;

public class PhysicalAppointment extends Appointment{
    public PhysicalAppointment(int appointmentId, LocalTime startTime, LocalTime endTime) {
        super(appointmentId, startTime, endTime);
    }
}
