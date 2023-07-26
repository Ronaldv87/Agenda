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

    public Appointment create(final int appointmentId,
                              final LocalDateTime startDateTime,
                              final LocalDateTime endDateTime) throws CreationException{
        final Appointment appointment = new Appointment(appointmentId, startDateTime, endDateTime);
        boolean appointmentIsAlreadyPresent = false;
        for (Appointment existingEntry : appointmentList) {
            if(existingEntry.getAppointmentId() == appointment.getAppointmentId()) {
                appointmentIsAlreadyPresent = true;
                break;
            }
        }
        if(!appointmentIsAlreadyPresent) {
            this.appointmentList.add(appointment);
        } else {
            throw new CreationException("This appointment with " + appointment.getAppointmentId() + " already exists");
        }
        return appointment;
    }

    public Appointment read(final int appointmentId) throws ReadException{
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        throw new ReadException("Appointment with id " + appointmentId + " not found");
    }

    public Appointment update(Appointment appointment) throws UpdateExeption {
        for(Appointment appointmentToUpdate : appointmentList) {
            if(appointmentToUpdate.getAppointmentId() == appointment.getAppointmentId()) {
                appointmentToUpdate.setStartDateTime(appointment.getStartDateTime());
                appointmentToUpdate.setEndDateTime(appointment.getEndDateTime());
                return appointmentToUpdate;
            }
        }
        throw new UpdateExeption("Appointment with id " + appointment.getAppointmentId() + " not found");
    }

    public boolean delete(Appointment appointment) throws DeleteException {
        for(Appointment appointmentToDelete : appointmentList) {
            if(appointmentToDelete.getAppointmentId() == appointment.getAppointmentId()) {
                appointmentList.remove(appointmentToDelete);
                return true;
            }
        }
        throw new DeleteException("Appointment with id " + appointment.getAppointmentId() + " not found");
    }

    public List<Appointment> getList() {
        if(appointmentList == null) {
            return new ArrayList<>();
        } else {
            return appointmentList;
        }
    }

    public void setList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
