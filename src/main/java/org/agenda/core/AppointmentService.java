package org.agenda.core;

import org.agenda.model.Appointment;
import org.agenda.repo.AppointmentCacheRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentService {

    private final AppointmentCacheRepository appointmentRepo;

    public AppointmentService() {
        this.appointmentRepo = new AppointmentCacheRepository();
    }

    public Appointment create(final String randomData) {
        final String[] parts = randomData.split("/");
        final LocalDateTime startDateTime = dateTimeConverter(parts[0]);
        final LocalDateTime endDateTime = dateTimeConverter(parts[1]);
        final int appointmentId = generateAppointmentId();
        return appointmentRepo.create(appointmentId, startDateTime, endDateTime);
    }

//    public Appointment update(final Appointment appointment) {
//        return appointmentRepo.update(appointment);
//    }
//
//    public Appointment read(final String id) {
//        return appointmentRepo.read(id);
//    }
//
//    public boolean delete(final String id) {
//        return appointmentRepo.delete(id);
//    }

    public LocalDateTime dateTimeConverter(String dateTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse( dateTime, f);
    }

    public Integer generateAppointmentId() {
        final List<Appointment> appointmentList = appointmentRepo.getList();
        final int lastRecordInList = appointmentList.size() - 1;
        final Appointment appointment = appointmentList.get(lastRecordInList);
        return appointment.getAppointmentId() + 1;
    }
}
