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
                              final LocalDateTime endDateTime) throws IllegalArgumentException{
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
            throw new IllegalArgumentException("This appointment with " + appointment.getAppointmentId() + " already exists");
        }
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
    public Appointment update(Appointment appointment) throws IllegalArgumentException {
        for(Appointment appointmentToFind : appointmentList) {
            if(appointmentToFind.getAppointmentId() == appointment.getAppointmentId()) {
                int index = appointmentList.indexOf(appointmentToFind);
                appointmentList.get(index).setStartDateTime(appointment.getStartDateTime());
                appointmentList.get(index).setEndDateTime(appointment.getEndDateTime());
                return appointmentList.get(index);
            }
        }
        throw new IllegalArgumentException("Appointment with id " + appointment.getAppointmentId() + " not found");
    }

    // delete
    // appointmentList.delete(appointment / id);
    public Appointment delete(Appointment appointment) throws IllegalArgumentException {
        for(Appointment appointmentToDelete : appointmentList) {
            if(appointmentToDelete.getAppointmentId() == appointment.getAppointmentId()) {
                appointmentList.remove(appointmentToDelete);
                //Should I even return an appointment? Perhaps we want to know which appointment was deleted.
                //Otherwise, might as well make this method void.
                return appointmentToDelete;
            }
        }
        throw new IllegalArgumentException("Appointment with id " + appointment.getAppointmentId() + " not found");
    }

    // list
    // return appointmentList;
    public List<Appointment> getList() {
        return appointmentList;
    }

    public void setList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
