package org.agenda.core;

import org.agenda.model.Appointment;
import org.agenda.repo.AppointmentCacheRepository;

public class AppointmentService {

    private AppointmentCacheRepository appointmentRepo;

    public AppointmentService() {
        this.appointmentRepo = new AppointmentCacheRepository();
    }

    public Appointment read(final String id) {
        return appointmentRepo.read(id);
    }

    public boolean delete(final String id) {
        return appointmentRepo.delete(id);
    }

    public Appointment update(final Appointment appointment) {
        return appointmentRepo.update(appointment);
    }

    public Appointment create(final String randomData) {
        return appointmentRepo.create(randomData);
    }
}
