package org.agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Day> days = new ArrayList<>();

    public void insertDay() {
        //TODO check if day doesn't already exist (for loop)
        var day = new Day(date);
        days.add(day);
    }

    public Appointment getAppointment() {
        //TODO move to Appointment class
        var appointment = new Appointment();

        return appointment;
    }

    public void displayAppointment() {
        var appointment = getAppointment();
        System.out.println(appointment.getAppointmentId());
        System.out.println(appointment.getDateTime());
    }

    public void displayAppointmentsFromOneDay(LocalDate date) {
        for(Day day : days) {
            if(date.equals(day.getDate())) {
                day.displayAppointments();
            }
        }
    }

    public void start() {
        displayAppointment();
        displayAppointmentsFromOneDay(LocalDate.now());
    }
}
