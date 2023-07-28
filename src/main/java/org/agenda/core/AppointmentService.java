package org.agenda.core;

import org.agenda.model.Appointment;
import org.agenda.repo.AppointmentCacheRepository;

import java.util.List;

public class AppointmentService {

    private final AppointmentCacheRepository appointmentRepo;

    public AppointmentService() {
        this.appointmentRepo = new AppointmentCacheRepository();
    }

    public Appointment create(final Appointment appointment) {
        return appointmentRepo.create(appointment);
    }

    public Appointment read(final String id) {
        return appointmentRepo.read(Integer.parseInt(id));
    }

    public Appointment update(final Appointment appointment) {
        return appointmentRepo.update(appointment);
    }

    public boolean delete(final Appointment appointment) {
        return appointmentRepo.delete(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.getList();
    }

    Integer generateAppointmentId() {
        List<Appointment> appointmentList = getAllAppointments();
        if(appointmentList.size() < 1) {
            return 1;
        }
        final int lastRecordInList = appointmentList.size() - 1;
        final Appointment appointment = appointmentList.get(lastRecordInList);
        return appointment.getAppointmentId() + 1;
    }
}
