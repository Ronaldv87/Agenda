package org.agenda.repo;

import org.agenda.model.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentCacheRepository {

    private List<Appointment> appointmentList = new ArrayList<>();

    public AppointmentCacheRepository() {
    }

    public Appointment create(final Appointment appointment) {
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

    public Appointment read(final int appointmentId) {
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        throw new ReadException("Appointment with id " + appointmentId + " not found");
    }

    public Appointment update(Appointment appointment) {
        for(Appointment appointmentToUpdate : appointmentList) {
            if(appointmentToUpdate.getAppointmentId() == appointment.getAppointmentId()) {
                appointmentToUpdate.setStartDateTime(appointment.getStartDateTime());
                appointmentToUpdate.setEndDateTime(appointment.getEndDateTime());
                return appointmentToUpdate;
            }
        }
        throw new UpdateExeption("Appointment with id " + appointment.getAppointmentId() + " not found");
    }

    public boolean delete(Appointment appointment) {
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
