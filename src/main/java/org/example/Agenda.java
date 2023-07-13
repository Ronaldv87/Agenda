package org.example;

public class Agenda {
    public Appointment getAppointment() {
        var appointment = new Appointment();

        return appointment;
    }

    public void displayAppointment() {
        var appointment = getAppointment();
        System.out.println(appointment.getAppointmentId());
        System.out.println(appointment.getDateTime());
    }

    public void start() {
        displayAppointment();
    }
}
