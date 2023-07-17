package org.agenda.model;

import java.time.LocalTime;

public class TelephoneAppointment extends Appointment{
    public TelephoneAppointment(int appointmentId, LocalTime startTime, LocalTime endTime) {
        super(appointmentId, startTime, endTime);
    }
}
