package org.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Day {
    private final LocalDate date;
    private List<Appointment> appointments = new ArrayList();

    public void createAppointment(AppointmentType type, LocalTime startTime, LocalTime endTime, LocalDate day) {

    }
    public Day(LocalDate date) {
        this.date = date;
    }

    public List<Appointment> getAppointments() {
        List<Appointment> copy = new ArrayList<>(appointments);

        return copy;
    }

    public void displayAppointments() {
        for(Appointment appointment : appointments) {
            System.out.println(appointment.getAppointmentId());
            System.out.println(appointment.getDateTime());
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
