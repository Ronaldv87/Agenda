package org.agenda.repo;

import org.agenda.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentCacheRepository {

    final List<Appointment> appointmentList = new ArrayList<>();

    public AppointmentCacheRepository() {
        appointmentList.add(new Appointment());
    }

    // create
    // appointmentList.create(string[] inputData);

    // read
    // appointmentList.read(id);

    // update
    // appointmentList.update(appointment);

    // delete
    // appointmentList.delete(appointment / id);

    // list
    // return appointmentList;
}
