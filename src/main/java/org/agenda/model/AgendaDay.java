package org.agenda.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaDay {
    private final LocalDate date;
    private List<Appointment> appointments = new ArrayList();

    public AgendaDay(LocalDate date) {
        this.date = date;
    }

    public List<Appointment> getAppointments() {
        List<Appointment> copy = new ArrayList<>(appointments);

        return copy;
    }

//    public void displayAppointments() {
//        for(Appointment appointment : appointments) {
//            System.out.println(appointment.getAppointmentId());
//            System.out.println(appointment.getStartTime());
//        }
//    }

    public LocalDate getDate() {
        return date;
    }
}
