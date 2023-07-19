package org.agenda.repo;

import org.agenda.model.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentCacheRepository {

    private List<Appointment> appointmentList = new ArrayList<>();

    public AppointmentCacheRepository() {
//        appointmentList.add(new Appointment());
    }

    // create
    // appointmentList.create(string[] inputData);
    public Appointment create(final int appointmentId,
                              final LocalDateTime startDateTime,
                              final LocalDateTime endDateTime) {
        final Appointment appointment = new Appointment(appointmentId, startDateTime, endDateTime);
        this.appointmentList.add(appointment);

        return appointment;
    }

    // read
    // appointmentList.read(id);
    public Appointment read(final int appointmentId) {
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }

    // update
    // appointmentList.update(appointment);

    // delete
    // appointmentList.delete(appointment / id);

    // list
    // return appointmentList;

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
